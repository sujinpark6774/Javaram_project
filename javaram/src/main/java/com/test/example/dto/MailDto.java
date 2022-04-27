package com.test.example.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MailDto {
  private String from;
  private String to;
  private String cc;
  private String subject;
  private String message;
  private MultipartFile file;
}
