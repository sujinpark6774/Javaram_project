package com.test.example.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.test.example.dto.NoticeDTO;

@Service
public interface NoticeService {
	
	List<NoticeDTO> selectNoticeList() throws Exception;

	NoticeDTO selectNoticeDetail() throws Exception;
	NoticeDTO selectNoticeDetail(int id) throws Exception;
	
	void noticeAddCont(String user_id, String notice_title, String date, String notice_content) throws Exception;
	
	void noticeDeleteCont(int notice_id) throws Exception;
	
	void noticeUpdateCont(int notice_id, String notice_title, String notice_content) throws Exception;
}