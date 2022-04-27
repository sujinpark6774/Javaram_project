package com.test.example.dto;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Alias("user")
@Getter
@Setter
@ToString
public class UserDTO {
	private String user_id;
	private String user_name;
	private String user_pw;
	private String user_email;
	private boolean access;
	private String user_img;
	
//	public UserDTO() {}
//
//	public UserDTO(String user_id, String user_name, String user_pw, String user_email, boolean access, String user_img) {
//		super();
//		this.user_id = user_id;
//		this.user_name = user_name;
//		this.user_pw = user_pw;
//		this.user_email = user_email;
//		this.access = access;
//		this.user_img = user_img;
//	}
//
//	public UserDTO(String user_id, String user_name, String user_pw, String user_email, boolean access) {
//		super();
//		this.user_id = user_id;
//		this.user_name = user_name;
//		this.user_pw = user_pw;
//		this.user_email = user_email;
//		this.access = access;
//	}
	
	public List<String> printvalue(UserDTO tmp) {
		List<String> inputdata = new ArrayList<String>();
		
		inputdata.add(tmp.user_id);
		inputdata.add(tmp.user_pw);
		
		return inputdata;
	}


}
