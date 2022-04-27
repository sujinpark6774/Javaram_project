//controller

package com.test.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.test.example.dto.MailDto;
import com.test.example.service.MailService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
public class MailController {
  
  private final MailService mailService;
  
  @Autowired
  JavaMailSender javaMailSender;

  JsonObject obj;


  //다시 깔끔하게 선언
  @GetMapping("/countMails")
  public String countMails(
    @RequestParam String user,
    @RequestParam @Nullable String boxName
  ){
    String messageCount;
    messageCount = mailService.countMails(user, boxName);
    return messageCount;
  }

  @GetMapping("/readMail")
  public JsonObject readMail(
    @RequestParam String user,
    @RequestParam String boxName,
    @RequestParam String messageNumber
  ){
    try {
      obj = mailService.readMail(user,boxName, Integer.parseInt(messageNumber));
    } catch (NumberFormatException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return obj;
  }

  @GetMapping("/readMails")
  public JsonObject readMails(
    @RequestParam String user,
    @RequestParam String boxName,
    @RequestParam String pageNumber
  ){
    try {
      obj = mailService.readMails(user,boxName, pageNumber);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return obj;
  }

  @PostMapping("/sendMail")
  public void sendMail(@RequestBody MailDto mailDto){
    
    try{
    mailService.sendMail(mailDto);
    }
    catch (Exception e){
      System.out.println("Error 발생");
      e.printStackTrace();
    }
  }

}
