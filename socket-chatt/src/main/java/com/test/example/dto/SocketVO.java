package com.test.example.dto;

public class SocketVO {
    
    private String userName;
    private String content;
    
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public SocketVO(String userName, String content) {
		this.userName = userName;
		this.content = content;
	}    
} 
