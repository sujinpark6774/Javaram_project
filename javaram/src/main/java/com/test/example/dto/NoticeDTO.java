package com.test.example.dto;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Alias("notice")
@Getter
@Setter
@ToString
public class NoticeDTO {
	
	private int notice_id;
	private String user_id;
	private String notice_title;
	private String notice_date;
	private String notice_content;
	
//	public NoticeDTO() {}
//
//	public NoticeDTO(String user_id, String notice_title, String notice_date, String notice_content) {
//		this.user_id = user_id;
//		this.notice_title = notice_title;
//		this.notice_date = notice_date;
//		this.notice_content = notice_content;
//	}
//	
	
}