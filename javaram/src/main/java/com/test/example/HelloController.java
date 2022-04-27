package com.test.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notice")
public class HelloController {
	
	@GetMapping("/write")
	public String hello() {
		return "hello";
	}
}
