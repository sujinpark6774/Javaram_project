package com.test.example.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.example.Mapper.UserMapper;
import com.test.example.dto.UserDTO;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	SqlSessionTemplate session;
	
	@Override
	public List<UserDTO> selectUserList() throws Exception {
		return userMapper.selectUserList();
	}
	
	@Override
	public UserDTO selectOneUserList(String user_id) throws Exception {
		return userMapper.selectOneUserList(user_id);
	}

	@Override
	public UserDTO selectOneUserList() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void InsertUserInfo(String user_name, String user_id, String user_pw, String user_email) throws Exception {
		userMapper.InsertUserInfo(user_name, user_id, user_pw, user_email);
	}

	public String FindID(String user_name, String user_email) {
		return userMapper.FindID(user_name, user_email);
	}
	
	public String FindPW(String user_id, String user_name, String user_email) {
		return userMapper.FindPW(user_id, user_name, user_email);
	}
	
	public void ChangePW(String user_id, String user_pw) {
		userMapper.ChangePW(user_id, user_pw);
	}
	
	public void ChangeEMAIL(String user_id, String user_email) {
		userMapper.ChangeEMAIL(user_id, user_email);
	}
	
}