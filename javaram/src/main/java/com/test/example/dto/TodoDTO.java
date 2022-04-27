package com.test.example.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Alias("todo")
@Getter
@Setter
@ToString
public class TodoDTO {
	
	private String user_id;
	private int todo_no;
	private String title;
	private String start_date;
	private String end_date;
	private String memo;
	private boolean check_yn;
	
//	public TodoDTO() {}
//
//	public TodoDTO(String user_id, String title, String s_date, String e_date, String memo, boolean check) throws ParseException {
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		
////		super();
//		this.user_id = user_id;
//		this.title = title;
//
//		this.start_date = simpleDateFormat.parse(s_date);
//		this.end_date = simpleDateFormat.parse(e_date);
//		
//		System.out.println(start_date);
//		System.out.println(end_date);
//
//		this.memo = memo;
//		this.check = check;
//	}
//


}