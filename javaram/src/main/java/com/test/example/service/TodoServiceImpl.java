package com.test.example.service;

import java.util.Date;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.example.Mapper.TodoMapper;
import com.test.example.dto.TodoDTO;

@Service
public class TodoServiceImpl implements TodoService {

	@Autowired
	private TodoMapper todoMapper;
	
	@Autowired
	SqlSessionTemplate session;

	// 개인별 Todo 출력
	@Override
	public List<TodoDTO> selectTodoList() throws Exception { return null; }
	
	@Override
	public List<TodoDTO> selectTodoList(String user) throws Exception {
		return todoMapper.selectTodoList(user);
	}
	
	// 개인별, 날짜별 Todo 출력
	@Override
	public List<TodoDTO> selectTodoDetail() throws Exception { return null; }
	
	@Override
	public List<TodoDTO> selectTodoDetail(String user, Date tododate) throws Exception {
		return todoMapper.selectTodoDetail(user, tododate);
	}
	
	// 개인별, 날짜별, 선택별 Todo 출력
	@Override
	public List<TodoDTO> selectTodoDetailshow() throws Exception { return null; }
	
	@Override
	public List<TodoDTO> selectTodoDetailshow(String user, int todono) throws Exception {
		return todoMapper.selectTodoDetailshow(user, todono);
	}

    // todo 신규 등록
	public void todoAddCont(String user_id, String title, String start_date, String end_date, String memo) throws Exception {
		todoMapper.todoAddCont(user_id, title, start_date, end_date, memo);
	}

    //	Todo 삭제
	public void todoDeleteCont(int todo_no) throws Exception {
		todoMapper.todoDeleteCont(todo_no);
	}

    //	Todo 수정
	public void todoUpdateCont(int todo_no, String title, String start_date, String end_date, String memo) throws Exception {
		todoMapper.todoUpdateCont(todo_no, title, start_date, end_date, memo);
	}




}