package com.test.example.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.test.example.dto.TodoDTO;

@Service
public interface TodoService {

	List<TodoDTO> selectTodoList() throws Exception;
	List<TodoDTO> selectTodoList(String user) throws Exception;
	
	List<TodoDTO> selectTodoDetail() throws Exception;
	List<TodoDTO> selectTodoDetail(String user, Date tododate) throws Exception;

	List<TodoDTO> selectTodoDetailshow() throws Exception;
	List<TodoDTO> selectTodoDetailshow(String user, int todono) throws Exception;
	
	void todoAddCont(String user_id, String title, String start_date, String end_date, String memo) throws Exception;
	
	void todoDeleteCont(int todo_no) throws Exception;
	
	void todoUpdateCont(int todo_no, String title, String start_date, String end_date, String memo) throws Exception;
	
}