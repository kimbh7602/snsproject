package edu.ssafy.boot.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.ssafy.boot.service.EmailService;

@CrossOrigin(origins="*")
@RestController()
@RequestMapping("/api/email")
public class EmailController {
	
	@Autowired
	@Qualifier("EmailServiceImpl")
	EmailService ser;
	
	@PostMapping("/findpassword/{email}")
	private @ResponseBody ResponseEntity<Map<String, Object>> findPassword(@PathVariable("email") String email) throws ServletException, IOException {
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> msg = new HashMap<String, Object>();
		try {
			ser.findPassword(email);
			msg.put("resmsg", "메일 보내기 성공");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msg.put("resmsg", "메일 보내기 실패");
		}
		resEntity = new ResponseEntity<Map<String,Object>>(msg, HttpStatus.OK);
		return resEntity;
	}
	
	@PostMapping("/checkEmail/{email}")
	private @ResponseBody ResponseEntity<Map<String, Object>> checkEmail(@PathVariable("email") String email) throws ServletException, IOException {
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> msg = new HashMap<String, Object>();
		System.out.println(email);
		try {
			String check = ser.checkEmail(email);
			msg.put("resmsg", "메일 보내기 성공");
			msg.put("resValue", check);
		} catch (MessagingException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			msg.put("resmsg", "메일 보내기 실패");
		}
		resEntity = new ResponseEntity<Map<String,Object>>(msg, HttpStatus.OK);
		return resEntity;
	}
}
