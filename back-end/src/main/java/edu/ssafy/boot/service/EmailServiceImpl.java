package edu.ssafy.boot.service;

import java.util.Date;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import edu.ssafy.boot.repository.IUserDAO;

@Service("EmailServiceImpl")
public class EmailServiceImpl implements EmailService {
	
	@Autowired
	JavaMailSender javaMailSender;
	
	@Autowired
	@Qualifier("UserDAOImpl")
	IUserDAO dao;
	
	@Override
	public void findPassword(String email) throws MessagingException {
		Random rnd =new Random();

		StringBuffer buf =new StringBuffer();

		for(int i=0;i<10;i++){
		    if(rnd.nextBoolean()){
		        buf.append((char)((int)(rnd.nextInt(26))+97));
		    }else{
		        buf.append((rnd.nextInt(10)));
		    }
		}
		
		String text = "<table style=\"width:50%; border-style:none; text-align: center; margin: auto;\">\r\n" + 
				"    <tr><td style=\"font-size: 25px; padding:15px;\">Project SNS</td></tr>\r\n" + 
				"    <tr><td>요청하신 임시비밀번호입니다</td></tr>\r\n" + 
				"    <tr><td style=\"padding-bottom:20px;\">로그인 후 반드시 비밀번호를 변경해 주십시오</td>\r\n" + 
				"    </tr>\r\n" + 
				"    <tr><td style=\"color:white; background-color: black; font-size:17px; padding:5px;\">임시 비밀번호</td></tr>\r\n" + 
				"    <tr><td style=\"padding:10px;\">"+buf.toString()+"</td style=\"padding:10px;\"></td></tr>\r\n" + 
				"  </table>";

		MimeMessage message = javaMailSender.createMimeMessage();
		message.setSubject("SNS 임시 비밀번호 발송 안내 메일");
		message.setSender(new InternetAddress("kimbh7602@gmail.com"));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
        message.setContent(text, "text/html;charset=utf-8");
        message.setSentDate(new Date());
		
        dao.updateTempPassword(email, buf.toString());
        javaMailSender.send(message);
	}
	
	@Override
	public String checkEmail(String email) throws MessagingException {
		Random rnd =new Random();

		StringBuffer buf =new StringBuffer();

		for(int i=0;i<10;i++){
		    if(rnd.nextBoolean()){
		        buf.append((char)((int)(rnd.nextInt(26))+97));
		    }else{
		        buf.append((rnd.nextInt(10)));
		    }
		}
		
		String text = "<table style=\"width:50%; border-style:none; text-align: center; margin: auto;\">\r\n" + 
				"    <tr><td style=\"font-size: 25px; padding:15px;\">Project SNS</td></tr>\r\n" + 
				"    <tr><td>이메일 인증확인 메일입니다</td></tr>\r\n" + 
				"    <tr><td style=\"padding-bottom:20px;\">인증번호를 입력란에 정확히 입력해주세요</td>\r\n" + 
				"    </tr>\r\n" + 
				"    <tr><td style=\"color:white; background-color: black; font-size:17px; padding:5px;\">인증번호</td></tr>\r\n" + 
				"    <tr><td style=\"padding:10px;\">"+buf.toString()+"</td style=\"padding:10px;\"></td></tr>\r\n" + 
				"  </table>";

		MimeMessage message = javaMailSender.createMimeMessage();
		message.setSubject("SNS email 확인 인증 메일");
		message.setSender(new InternetAddress("kimbh7602@gmail.com"));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
        message.setContent(text, "text/html;charset=utf-8");
        message.setSentDate(new Date());
		
        javaMailSender.send(message);
        
        return buf.toString();
	}
}
