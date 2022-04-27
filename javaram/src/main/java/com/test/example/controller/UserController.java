package com.test.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.example.dto.UserDTO;
import com.test.example.service.UserService;


@RestController
public class UserController {
	
	@Autowired
	private UserService service;

	@RequestMapping("/user")
	public List<UserDTO> userList() throws Exception {
		
		List<UserDTO> userList = service.selectUserList();
		return userList;
	}

}