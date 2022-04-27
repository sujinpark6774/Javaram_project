package com.test.example.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.example.Mapper.NoticeMapper;
import com.test.example.dto.NoticeDTO;

@Service
public class NoticeServiceImpl implements NoticeService {		// private 추가시 Error

	@Autowired
	private NoticeMapper noticeMapper;
	
	@Autowired
	SqlSessionTemplate session;
	
	//  게시판 전체 조회
	@Override
	public List<NoticeDTO> selectNoticeList() throws Exception {
		return noticeMapper.selectNoticeList();
	}

    //	게시판 상세 조회
	@Override
	public NoticeDTO selectNoticeDetail() throws Exception { return null; }
	
	@Override
	public NoticeDTO selectNoticeDetail(int id) throws Exception {
		return noticeMapper.selectNoticeDetail(id);
	}

    //	게시판 게시물 등록
	public void noticeAddCont(String user_id, String notice_title, String notice_date, String notice_content) throws Exception {
		noticeMapper.noticeAddCont(user_id, notice_title, notice_date, notice_content);
	}

    //	게시판 게시물 삭제
	public void noticeDeleteCont(int notice_id) throws Exception {
		noticeMapper.noticeDeleteCont(notice_id);
	}

    //	게시판 게시물 수정
	public void noticeUpdateCont(int notice_id, String notice_title, String notice_content) throws Exception {
		noticeMapper.noticeUpdateCont(notice_id, notice_title, notice_content);
	}

}