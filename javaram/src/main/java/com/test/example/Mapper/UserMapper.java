package com.test.example.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.test.example.dto.UserDTO;

@Mapper
public interface UserMapper {
	List<UserDTO> selectUserList() throws Exception;
	
	UserDTO selectOneUserList(@Param("user_id") String user_id);
	
	void InsertUserInfo(@Param("user_id") String user_id, @Param("user_name") String user_name, 
			@Param("user_pw") String user_pw, @Param("user_email") String user_email) throws Exception;

	String FindID(@Param("user_name") String user_name, @Param("user_email") String user_email);

	String FindPW(@Param("user_id") String user_id, @Param("user_name") String user_name, @Param("user_email") String user_email);

	void ChangePW(@Param("user_id") String user_id, @Param("user_pw") String user_pw);

	void ChangeEMAIL(@Param("user_id") String user_id, @Param("user_email") String user_email);
}