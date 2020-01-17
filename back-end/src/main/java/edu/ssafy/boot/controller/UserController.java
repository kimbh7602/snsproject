package edu.ssafy.boot.controller;

import java.util.HashMap;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.ssafy.boot.dto.BlockVo;
import edu.ssafy.boot.dto.LogVo;
import edu.ssafy.boot.dto.UserVo;
import edu.ssafy.boot.service.IBlockchainService;
import edu.ssafy.boot.service.ISecurityService;
import edu.ssafy.boot.service.IUserService;
import io.swagger.annotations.ApiOperation;


@CrossOrigin(origins = "*")
@RestController()
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	@Qualifier("UserService")
	IUserService ser;
	
	@Autowired
	@Qualifier("BlockchainService")
	IBlockchainService serbc;
	
	@Autowired
	@Qualifier("SecurityService")
	ISecurityService sersc;
	

	@PostMapping("/login")
	@ApiOperation(value = "로그인서비스")
	private @ResponseBody ResponseEntity<Map<String, Object>> login(@RequestBody UserVo user, HttpServletRequest request) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			user.setPassword(sersc.computePw(user.getPassword()));
			boolean res = ser.login(user);
			Map<String, Object> map = new HashMap<String, Object>();
			if (res) {
				//run configurations 에서 arguments-> vm arguments ->"-Djava.net.preferIPv4Stack=true" 입력
				LogVo log = new LogVo(user.getUser_id(), request.getRemoteAddr(), "로그인");
				BlockVo block = new BlockVo(log, new java.util.Date());
				serbc.addBlock(block);
				serbc.displayChain();
				map.put("resmsg", "로그인");
			} else {
				map.put("resmsg", "아이디와 비밀번호가 일치하지 않음");
			}
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "로그인 실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;
	}

	@PutMapping("/update")
	@ApiOperation(value = "회원정보 수정")
	private ResponseEntity<Map<String, Object>> update(@RequestBody UserVo user) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			boolean res = ser.updateUserInfo(user);
			Map<String, Object> map = new HashMap<String, Object>();
			if(res){
				map.put("resmsg", "수정성공");
				map.put("resvalue", res);
			}else{
				map.put("resmsg", "수정실패");
			}
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "수정실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

		}
		return resEntity;
	}
	
	@DeleteMapping("/delete/{user_id}")
	@ApiOperation(value = "회원정보 삭제")
	private ResponseEntity<Map<String, Object>> delete(@PathVariable("user_id") String user_id) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			boolean res = ser.deleteUserInfo(user_id);
			Map<String, Object> map = new HashMap<String, Object>();
			if(res){
				map.put("resmsg", "삭제성공");
				map.put("resvalue", res);
			}else{
				map.put("resmsg", "삭제실패");
			}
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "삭제실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

		}
		return resEntity;
	}

	@GetMapping("/info/{user_id}")
	@ApiOperation(value = "회원정보조회", response = UserVo.class)
	private @ResponseBody ResponseEntity<Map<String, Object>> infoMem(@PathVariable("user_id") String user_id) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		UserVo user = null;
		try {
			user = ser.info(user_id);
			Map<String, Object> map = new HashMap<String, Object>();
			if(user != null){
				map.put("resmsg", "조회성공");
				map.put("resvalue", user);
			}else{
				map.put("resmsg", "조회실패");
			}
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "조회실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;
	}

	@PostMapping("/signup")
	@ApiOperation(value = "회원가입")
	private @ResponseBody ResponseEntity<Map<String, Object>> signUpMem(@RequestBody UserVo user) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			user.setPassword(sersc.computePw(user.getPassword()));
			boolean signup = ser.signup(user);
			Map<String, Object> map = new HashMap<String, Object>();
			if (signup) 
				map.put("resmsg", "등록성공");
			else
				map.put("resmsg", "1등록실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "등록실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;
	}

	@GetMapping("/emailCheck/{email}")
	@ApiOperation(value = "이메일 중복체크")
	private @ResponseBody ResponseEntity<Map<String, Object>> emailCheck(@PathVariable("email") String email) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		// System.out.println("123");
		try {
			System.out.println(email);
			boolean res = ser.emailDuplicateCheck(email);
			Map<String, Object> map = new HashMap<String, Object>();
			System.out.println(res);
			if (!res) 
				map.put("resmsg", "사용가능");
			else
				map.put("resmsg", "이메일 중복");
			map.put("resValue", !res);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "이메일 확인 실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;
	}
}
