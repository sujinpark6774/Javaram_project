package com.test.example.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.test.example.dto.NoticeDTO;

@Mapper
public interface NoticeMapper {
	
	public List<NoticeDTO> selectNoticeList() throws Exception;

	public NoticeDTO selectNoticeDetail(@Param("id") int id);
	
	void noticeAddCont(@Param("user_id") String user_id, @Param("notice_title") String notice_title, @Param("notice_date") String notice_date, @Param("notice_content") String notice_content);
	
	void noticeDeleteCont(@Param("notice_id") int notice_id);

	void noticeUpdateCont(@Param("notice_id") int notice_id, @Param("notice_title") String notice_title, @Param("notice_content") String notice_content);

} 