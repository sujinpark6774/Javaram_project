package com.test.example.Mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.test.example.dto.TodoDTO;

@Mapper
public interface TodoMapper {
	
	public List<TodoDTO> selectTodoList(@Param("user") String user) throws Exception;
	
	public List<TodoDTO> selectTodoDetail(@Param("user") String user, @Param("tododate") Date tododate) throws Exception;

	public List<TodoDTO> selectTodoDetailshow(@Param("user") String user, @Param("todono") int todono);
	
	void todoAddCont(@Param("user_id") String user_id, @Param("title") String title, @Param("start_date") String start_date, 
					 @Param("end_date") String end_date, @Param("memo") String memo);

	void todoDeleteCont(@Param("todo_no") int todo_no);

	void todoUpdateCont(@Param("todo_no") int todo_no, @Param("title") String title, @Param("start_date") String start_date, @Param("end_date") String end_date, @Param("memo") String memo);
}