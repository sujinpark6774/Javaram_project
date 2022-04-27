package com.test.example.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.example.dto.UserDTO;
import com.test.example.service.UserService;

@RestController
public class LoginController {

	private List<String> tmp = new ArrayList<String>();
	
//	private boolean result = false;

	@Autowired
	UserService service;
	
	@PostMapping("/loginCheck")
	public ResponseEntity<UserDTO> LoginCheck(@RequestBody UserDTO body, HttpServletRequest req) throws Exception {
		this.tmp.clear();
		this.tmp.add(body.getUser_id());
		this.tmp.add(body.getUser_pw());
		
		String User_ID_Data = null;
		
		List<UserDTO> userList = service.selectUserList();
		UserDTO OneuserList = null;
		
		for(UserDTO tmp: userList) {
			if(tmp.printvalue(tmp).equals(this.tmp)) {
				System.out.println("로그인 성공");
				User_ID_Data = tmp.getUser_id();
				OneuserList = service.selectOneUserList(User_ID_Data);
			}else{
				continue;
			};
		};
		
		return new ResponseEntity<UserDTO>(OneuserList, HttpStatus.ACCEPTED); 
	}
	
	@PostMapping("/insertUser")
	public void InsertUser(@RequestBody UserDTO body) throws Exception {
		service.InsertUserInfo(body.getUser_id(), body.getUser_name(), body.getUser_pw(), body.getUser_email());
		Runtime.getRuntime().exec("curl -v http://14.63.197.43:5000/createUser?user=" + body.getUser_id());
	}
	
	@PostMapping("/FindUserID")
	public String FindID(@RequestBody UserDTO body) throws Exception {
		return service.FindID(body.getUser_name(), body.getUser_email());
	}
	
	@PostMapping("/FindUserPW")
	public String FindPW(@RequestBody UserDTO body) throws Exception {
		return service.FindPW(body.getUser_id(), body.getUser_name(), body.getUser_email());
	}
	
	@PostMapping("/changepw")
	public void changepw(@RequestBody UserDTO body) {
		service.ChangePW(body.getUser_id(), body.getUser_pw());
	}
	
	@PostMapping("/changeemail")
	public void changeemail(@RequestBody UserDTO body) {
		service.ChangeEMAIL(body.getUser_id(), body.getUser_email());
	}
	
	
//	@PostMapping("/loginC")
//	public void LoginCheck(HttpServletRequest req) throws Exception {
//		tmp.clear();
//		tmp.add(req.getParameter("user_id"));
//		tmp.add(req.getParameter("user_pw"));
//
//		List<UserDTO> userList = service.selectUserList();
//		
//		for(UserDTO tmp: userList) {
//			if(tmp.printvalue(tmp).equals(this.tmp)) {
//				System.out.println("로그인 성공");
//				this.result = true;
//			}else{
//				continue;
//			};
//		};
//	}
//	
//	@GetMapping("/loginC")
//	public boolean SendResult() {
//		return this.result;
//	}
	
//	@GetMapping("/loginC")
//	public List<UserDTO> SendUserInfo() throws Exception {
//		List<UserDTO> userList = service.selectUserList();
//		return userList;
//	}
	
	
	
}
