package com.test.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.test.example.dto.UserDTO;

@Service
public interface UserService {
	List<UserDTO> selectUserList() throws Exception;
	
	UserDTO selectOneUserList() throws Exception;
	UserDTO selectOneUserList(String user_id) throws Exception;
	void InsertUserInfo(String user_name, String user_id, String user_pw, String user_email) throws Exception;

	String FindID(String user_name, String user_email);

	String FindPW(String user_id, String user_name, String user_email);

	void ChangePW(String user_id, String user_pw);

	void ChangeEMAIL(String user_id, String user_email);

}