package com.test.example.service;


import com.google.gson.JsonObject;
import com.test.example.dto.MailDto;

/*
 * MailService
 */
public interface MailService {
  public abstract String countMails(String user, String boxName);
  public abstract JsonObject readMail(String user, String boxName, int messageNumber) throws Exception;
  public abstract JsonObject readMails(String user, String boxName,  String pageNumber) throws Exception;
  public abstract void sendMail(MailDto mailDto);
}
