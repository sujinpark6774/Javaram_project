package com.test.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.example.dto.NoticeDTO;
import com.test.example.service.NoticeService;


@RestController
public class NoticeController {
	
	@Autowired
	private NoticeService service;

	//	게시판 전체 목록 조회
	@RequestMapping("/notice")
	public List<NoticeDTO> noticeList() throws Exception {
		List<NoticeDTO> noticeList = service.selectNoticeList();
		return noticeList;
	}
	
	//	게시판 상세 내용 조회
	@RequestMapping("/notice/detail")
	public NoticeDTO noticeDetail(@RequestParam int id) throws Exception {
		NoticeDTO noticeDetail = service.selectNoticeDetail(id);
		return noticeDetail;
	}
	
	//	게시판 게시물 신규 등록
	@PostMapping("/noticeadd")
	public void noticeAddCont(@RequestBody NoticeDTO body) throws Exception{
		service.noticeAddCont(body.getUser_id(), body.getNotice_title(), body.getNotice_date(), body.getNotice_content());
	}
	
	//	게시판 게시물 삭제
	@DeleteMapping("/noticedelete/{notice_id}")
	public void noticeDeleteCont(@PathVariable int notice_id) throws Exception{
		service.noticeDeleteCont(notice_id);
	}
	
	//	게시판 게시물 수정
	@PutMapping("/noticeupdate/{notice_id}")
	public void noticeUpdateCont(@RequestBody NoticeDTO body, @PathVariable int notice_id) throws Exception{
		service.noticeUpdateCont(body.getNotice_id(), body.getNotice_title(), body.getNotice_content());
	}
}

















