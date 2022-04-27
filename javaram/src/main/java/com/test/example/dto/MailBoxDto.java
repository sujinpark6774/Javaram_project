package com.test.example.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// 받은메일을 확인하기 위하여 아래 DTO를 사용한다


@Getter
@Setter
@NoArgsConstructor
@ToString
public class MailBoxDto {
  private int messageNum;
  private int startNum;
  private int endNum;
  private String user;
  private String boxName;

//  public MailBoxDto(int messageNum, int startNum, int endNum, String user, String boxName) {
//    super();
//		this.messageNum = messageNum;
//		this.startNum = startNum;
//		this.endNum = endNum;
//		this.user = user;
//    this.boxName = boxName;
//	}
}
