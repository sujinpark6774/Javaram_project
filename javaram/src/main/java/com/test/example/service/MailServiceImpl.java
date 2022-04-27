package com.test.example.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.test.example.dto.MailDto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FlagTerm;
import java.text.SimpleDateFormat;  
import java.util.Date;  
/**
 * 필요한 메소드 생성
 */

 
class IMAPMailService {

    //reciever param
    private Session session;
    private Store store;
    private Folder folder;
    private String read_protocol = "imap";

    public String getAddress(String email){
      Pattern pattern = Pattern.compile("\\w+@\\w+\\W\\w+");

      Matcher matcher = pattern.matcher(email);
      matcher.find();
      return matcher.group();
    }

    private String getTextFromMessage(Message message) throws MessagingException, IOException {
      String result = "";
      if (message.isMimeType("text/plain")) {
          System.out.println("text/plain");
          result = message.getContent().toString();
      } else if (message.isMimeType("multipart/*")) {
          MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
          result = getTextFromMimeMultipart(mimeMultipart);
      }
      return result;
  }
  
  public String getTextFromMimeMultipart(
          MimeMultipart mimeMultipart)  throws MessagingException, IOException{
      String result = "";
      int count = mimeMultipart.getCount();
      for (int i = 0; i < count; i++) {
          BodyPart bodyPart = mimeMultipart.getBodyPart(i);
          if (bodyPart.isMimeType("text/plain")) {
              result = result + "n" + bodyPart.getContent();
              break; // without break same text appears twice in my tests
          } else if (bodyPart.isMimeType("text/html")) {
              String html = (String) bodyPart.getContent();
              result = result + "n" + org.jsoup.Jsoup.parse(html).text();
          } else if (bodyPart.getContent() instanceof MimeMultipart){
              result = result + getTextFromMimeMultipart((MimeMultipart)bodyPart.getContent());
          }
      }
      return result;
  }

    public String getEmalText(Object content) throws Exception {
//컨텐츠 타입에 따라서 text body 또는 멀티파트 처리 기능 구현이 필요"
      if (content instanceof Multipart) {
        System.out.println("멀티파트임");
          return getTextFromMimeMultipart( (MimeMultipart) content);
      }
      else if(content instanceof String){
          return (String) content;
      }
      else {
           System.out.println(content);
           System.out.println("멀티파트 아님");
           return getTextFromMessage((Message)content);
      }
    }

  public boolean isLoggedIn() {
       return store.isConnected();
  }
    
  public void login(String host, String user, String password, String boxName) throws Exception {
    URLName url = new URLName(read_protocol, host, 143, boxName, user+"@ktds.ml", password);
    if (session == null) {
         Properties props = null;
         try {
              props = System.getProperties();
         } catch (SecurityException sex) {
              props = new Properties();
         }
         session = Session.getInstance(props, null);
    }
    store = session.getStore(url);
    store.connect();
    folder = store.getFolder(boxName); //inbox는 받은 메일함을 의미
    //folder.open(Folder.READ_WRITE);
    folder.open(Folder.READ_ONLY); //읽기 전용
  }
  /**
  * to logout from the mail host server
  */
  public void logout() throws MessagingException {
      folder.close(false);
      store.close();
      store = null;
      session = null;
  }
  public int getMessageCount() {

      //메일 갯수 확인하기
      int messageCount = 0;
      try {
          messageCount = folder.getMessageCount();
      } catch (MessagingException me) {
          me.printStackTrace();
      }
      return messageCount;

  }

  public Message[] getMessages(boolean onlyNotRead) throws MessagingException {
    if (onlyNotRead) {
         return folder.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));
    } else {
      System.out.println("before getMessage");
         return folder.getMessages();              
    }
  }

  //페이지 번호적용시 아래와 같이 해줘야함
  public Message[] getMessages(int start, int end) throws MessagingException {
        return folder.getMessages(start, end);
  }

}


/**
 * MailServiceImpl
 */
@Service
public class MailServiceImpl implements MailService {

  //sender param
  @Autowired
  JavaMailSender javaMailSender;


  //IMAP 서버 설정
  String host = "mail.ktds.ml"; //imap 호스트 주소. ex) imap.gmail.com
  String password = "new1234!"; //유저 암호
  IMAPMailService mailService = new IMAPMailService();
  
  @Override
  public void sendMail(MailDto mailDto) {
    
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom(mailDto.getFrom());
    message.setTo(mailDto.getTo());
    
    message.setSubject(mailDto.getSubject());
    
    message.setText(mailDto.getMessage());
    System.out.println(message);
    javaMailSender.send(message);

    System.out.println("Sent");
  }

  @Override
  public String countMails(String user, String boxName) {

    try {
      mailService.login(host, user, password, boxName);
      int messageCount = mailService.getMessageCount();
      mailService.logout();
      System.out.println("카운트 끝");
      return Integer.toString(messageCount);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return "Error";
    }
  }

  @Override
  public JsonObject readMail(String user, String boxName, int messageNumber) throws Exception{
    
    JsonObject mail = new JsonObject();

    mailService.login(host, user, password, boxName);
    Message[] messages = mailService.getMessages(messageNumber, messageNumber);
    mail.addProperty("messageNumber", messages[0].getMessageNumber());
    mail.addProperty("from", mailService.getAddress(messages[0].getFrom()[0].toString()));
    mail.addProperty("to", messages[0].getAllRecipients()[0].toString());
    mail.addProperty("title", messages[0].getSubject());
    mail.addProperty("message", mailService.getEmalText( messages[0].getContent() ) );
    mailService.logout();
    return mail;
  }


  @Override
  public JsonObject readMails(String user, String boxName,  String pageNumber) throws Exception{
    //페이지번호에 해당하는 내용으로
    int page = Integer.parseInt(pageNumber);

    mailService.login(host, user, password,boxName);

    int messageCount = mailService.getMessageCount();
    int perpage = 10;
    int start = 1;
    int end = messageCount;
    int messageLenth;
    if ( messageCount > perpage)
    {
      start = end - perpage*page +1;
      if ( start < 1) start =1;
      end =   end - perpage*(page-1);
    }

    System.out.printf("%d %d %d ",messageCount,start,end);
    JsonObject mailbox = new JsonObject();

    Message[] msgArray = mailService.getMessages(start,end);
    messageLenth = msgArray.length;
   
    JsonArray mails = new JsonArray();
    // 메일을 가져와서 gson obj에 담는다
    for (int i = 0; i < messageLenth ; i++) {

     Message msg = msgArray[i];
     JsonObject mail = new JsonObject();
     mail.addProperty("messageNumber", msg.getMessageNumber());
     mail.addProperty("from", mailService.getAddress(msg.getFrom()[0].toString()));
     mail.addProperty("title", msg.getSubject());
     
     SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd"); //원하는 데이터 포맷 지정 
     String strNowDate = simpleDateFormat.format(msg.getReceivedDate());
     
     mail.addProperty("time", strNowDate.toString());
     // 1개 메일을 받아와서 mails json 배열에 넣는다
     mails.add(mail);
    }
    
    //메일들을 mailbox에 다 때려 넣기
    mailbox.add("mails", mails);
    mailbox.addProperty("messageCount", messageCount);
    mailService.logout();
    return mailbox;
  }

}
