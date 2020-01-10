package edu.ssafy.boot.service;

import javax.mail.MessagingException;

public interface EmailService {
	public void findPassword(String email) throws MessagingException;
	public String checkEmail(String email) throws MessagingException;
}
