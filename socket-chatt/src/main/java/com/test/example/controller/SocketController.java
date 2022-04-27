package com.test.example.controller;



import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.test.example.dto.SocketVO;

import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.test.example.dto.SocketVO;

@Controller
public class SocketController {
	
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
	public void makeDir() {
        
        // 폴더를 만들 디렉토리 경로(Window 기반)
        String folderPath = "C:/javaram_chat";
        
        File makeFolder = new File(folderPath);
 
        // folderPath의 디렉토리가 존재하지 않을경우 디렉토리 생성.
        if(!makeFolder.exists()) {
            
            // 폴더를 생성합니다.
            makeFolder.mkdir(); 
            System.out.println("폴더 생성");
            
        } 
    }
	@MessageMapping("/receive")
    @SendTo("/send")
    public SocketVO SocketHandler(SocketVO socketVO) throws IOException {       
    	
    	makeDir();
    	
    	String userName = socketVO.getUserName();      
        String content = socketVO.getContent();       
        SocketVO result = new SocketVO(userName, content);
        
        String textFileName = "C:/javaram_chat/result.txt"; //추후에 생성되는 링크를 넣으면 해결
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(textFileName, true));
        bufferedWriter.write("[" + userName +"]"+ "\n");
        bufferedWriter.write(content + "\n");
        bufferedWriter.close();
			
	
        return result;
    }   
    
    @MessageMapping("/chat_receive/{keyvalue}")
    public SocketVO SocketHandle(@DestinationVariable("keyvalue") String keyvalue, SocketVO socketVO) throws IOException {       
    		
    	makeDir();
    	
    	String userName = socketVO.getUserName();      
        String content = socketVO.getContent();       
        SocketVO result = new SocketVO(userName, content);
        
        simpMessagingTemplate.convertAndSend("/chat_send/"+ keyvalue, result);
        
        String textFileName = "C:/javaram_chat/"+keyvalue+".txt"; //추후에 생성되는 링크를 넣으면 해결
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(textFileName, true));
        bufferedWriter.write("[" + userName +"]"+ "\n");
        bufferedWriter.write(content + "\n");
        bufferedWriter.close();
			
	
        return result;
    }
}