package com.test.example.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.example.dto.NoticeDTO;
import com.test.example.dto.TodoDTO;
import com.test.example.service.TodoService;


@RestController
public class TodoController {
	
	@Autowired
	private TodoService service;

	@RequestMapping("/todo")
	public List<TodoDTO> todoList(@RequestParam String user) throws Exception {
		List<TodoDTO> todoList = service.selectTodoList(user);
		return todoList;
	}
	
	//	개인별 Todo data
	@RequestMapping("/todo/detail")
	public List<TodoDTO> todoDetail(@RequestParam String user, @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date tododate) throws Exception {
		List<TodoDTO> todoDetail = service.selectTodoDetail(user, tododate);
		return todoDetail;
	}
	
	//	선택시 Todo detail
	@RequestMapping("/todo/detail/show")
	public List<TodoDTO> todoDetail(@RequestParam String user, @RequestParam int todono) throws Exception {
		List<TodoDTO> todoDetail = service.selectTodoDetailshow(user, todono);
		return todoDetail;
	}
	
	//	Todo 신규 등록
	@PostMapping("/todoadd")
	public void todoAddCont(@RequestBody TodoDTO body) throws Exception{
		service.todoAddCont(body.getUser_id(), body.getTitle(), body.getStart_date(), body.getEnd_date(), body.getMemo());
	}
	
	//	Todo 삭제
	@DeleteMapping("/tododelete/{todo_no}")
	public void todoDeleteCont(@PathVariable int todo_no) throws Exception{
		service.todoDeleteCont(todo_no);
	}
	
	//	Todo 수정
	@PutMapping("/todoupdate/{todo_no}")
	public void todoUpdateCont(@RequestBody TodoDTO body, @PathVariable int todo_no) throws Exception{
		service.todoUpdateCont(body.getTodo_no(), body.getTitle(), body.getStart_date(), body.getEnd_date(), body.getMemo());
	}

}
