package edu.ssafy.boot.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;
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
import edu.ssafy.boot.dto.ImageVo;
import edu.ssafy.boot.dto.LogVo;
import edu.ssafy.boot.dto.UserVo;
import edu.ssafy.boot.dto.WordCloudVo;
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
	private @ResponseBody ResponseEntity<Map<String, Object>> login(@RequestBody UserVo user,
			HttpServletRequest request) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			user.setPassword(sersc.computePw(user.getPassword()));
			boolean res = false;
			UserVo result = ser.login(user);
			if (result != null) {
				res = true;
			}
			Map<String, Object> map = new HashMap<String, Object>();
			if (res) {
				// run configurations 에서 arguments-> vm arguments
				// ->"-Djava.net.preferIPv4Stack=true" 입력
				LogVo log = new LogVo(user.getUser_id(), request.getRemoteAddr(), "로그인");
				BlockVo block = new BlockVo(log, new java.util.Date().toString());
				serbc.addBlock(block);
				map.put("resmsg", "로그인");
				map.put("resValue", result.getUser_id());
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
	private ResponseEntity<Map<String, Object>> update(@RequestBody UserVo user, HttpServletResponse response,
			HttpServletRequest request) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		user.setPassword(sersc.computePw(user.getPassword()));
		imageDelete(user.getUser_id(), response, request);
		user = imageUpload(user, response, request);
		try {
			boolean res = ser.updateUserInfo(user);
			Map<String, Object> map = new HashMap<String, Object>();
			if (res) {
				// run configurations 에서 arguments-> vm arguments
				// ->"-Djava.net.preferIPv4Stack=true" 입력
				LogVo log = new LogVo(user.getUser_id(), request.getRemoteAddr(), "회원정보수정");
				BlockVo block = new BlockVo(log, new java.util.Date().toString());
				serbc.addBlock(block);
				map.put("resmsg", "수정성공");
				map.put("resvalue", res);
			} else {
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
	private ResponseEntity<Map<String, Object>> delete(@PathVariable("user_id") String user_id,
			HttpServletRequest request) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			boolean res = ser.deleteUserInfo(user_id);
			Map<String, Object> map = new HashMap<String, Object>();
			if (res) {
				LogVo log = new LogVo(user_id, request.getRemoteAddr(), "회원삭제");
				BlockVo block = new BlockVo(log, new java.util.Date().toString());
				serbc.addBlock(block);
				map.put("resmsg", "삭제성공");
				map.put("resvalue", res);
			} else {
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

	@GetMapping("/pwconfirm/{upw},{uid}")
	@ApiOperation(value = "비밀번호확인")
	private @ResponseBody ResponseEntity<Map<String, Object>> pwConfirm(@PathVariable("upw") String upw,
			@PathVariable("uid") String uid, HttpServletRequest request) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		UserVo user = null;
		try {
			user = ser.info(uid); // 스토리지 아이디를 통해서 유저 정보 가져오기
			Map<String, Object> map = new HashMap<String, Object>();
			if (user.getPassword().equals(sersc.computePw(upw))) {
				map.put("resmsg", "비밀번호확인 성공");
				map.put("resvalue", user);

			} else {
				map.put("resmsg", "비밀번호확인 실패");
			}
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "조회실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;
	}

	@GetMapping("/info/{user_id}")
	@ApiOperation(value = "회원정보조회", response = UserVo.class)
	private @ResponseBody ResponseEntity<Map<String, Object>> infoMem(@PathVariable("user_id") String user_id,
			HttpServletResponse response, HttpServletRequest request) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		UserVo user = null;
		String path = "/profile";
		String realPath = request.getServletContext().getRealPath(path);
		FileInputStream fis = null;
		ByteArrayOutputStream bos = null;

		try {
			user = ser.info(user_id);
			String url = user.getProfile_url();
			String imageName = url.substring(url.lastIndexOf("/")+1);
			String ext = imageName.substring(imageName.lastIndexOf(".")+1);
			Map<String, Object> map = new HashMap<String, Object>();
			if (user != null) {
				File file = new File(realPath + File.separator + imageName);
				if (file.exists()) {

					fis = new FileInputStream(file);
					bos = new ByteArrayOutputStream();
	
					int len = 0;
					byte[] buf = new byte[1024];
					while((len = fis.read(buf)) != -1){
						bos.write(buf, 0, len);
					}
					byte[] fileArray = bos.toByteArray();
					String imageString = new String(Base64.encodeBase64(fileArray));
					String changeString = "data:image/"+ext+";base64, "+imageString;
					user.setProfileImage(new ImageVo());
					user.getProfileImage().setBase64(changeString);
					user.getProfileImage().setFilter(user.getProfile_filter());
				}else{
					file = new File(realPath + File.separator + "default.png");
					if (file.exists()) {
						fis = new FileInputStream(file);
						bos = new ByteArrayOutputStream();
		
						int len = 0;
						byte[] buf = new byte[1024];
						while((len = fis.read(buf)) != -1){
							bos.write(buf, 0, len);
						}
						byte[] fileArray = bos.toByteArray();
						String imageString = new String(Base64.encodeBase64(fileArray));
						String changeString = "data:image/png;base64, "+imageString;
						user.setProfileImage(new ImageVo());
						user.getProfileImage().setBase64(changeString);
						user.getProfileImage().setFilter(user.getProfile_filter());
					}
				}
				LogVo log = new LogVo(user_id, request.getRemoteAddr(), "회원정보조회");
				BlockVo block = new BlockVo(log, new java.util.Date().toString());
				serbc.addBlock(block);
				map.put("resmsg", "조회성공");
				map.put("resvalue", user);

			} else {
				map.put("resmsg", "조회실패");
			}
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (RuntimeException | IOException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "조회실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}finally {
			try {
				if(fis!=null)fis.close();
				if(bos!=null)bos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resEntity;
	}

	@PostMapping("/signup")
	@ApiOperation(value = "회원가입")
	private @ResponseBody ResponseEntity<Map<String, Object>> signUpMem(@RequestBody UserVo user, HttpServletResponse res,  HttpServletRequest req) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			user.setPassword(sersc.computePw(user.getPassword()));
			user = imageUpload(user, res, req);
			boolean signup = ser.signup(user);
			Map<String, Object> map = new HashMap<String, Object>();
			if (signup) {
				LogVo log = new LogVo(user.getUser_id(), req.getRemoteAddr(), "회원정보조회");
				BlockVo block = new BlockVo(log, new java.util.Date().toString());
				serbc.addBlock(block);
				map.put("resmsg", "등록성공");
			}
			else {
				map.put("resmsg", "등록실패");
			}
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
		
		try {
			boolean res = ser.emailDuplicateCheck(email);
			Map<String, Object> map = new HashMap<String, Object>();
			
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

	@PostMapping("/searchByInterest")
	@ApiOperation(value = "관심사 검색")
	private @ResponseBody ResponseEntity<Map<String, Object>> searchByInterest(@RequestBody List<String> list) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			List<UserVo> userList = ser.searchByInterest(list);
			Map<String, Object> map = new HashMap<String, Object>();
			if(userList != null && userList.size() > 0){
				map.put("resmsg", "관심사 검색 성공");
				map.put("resValue", userList);
			}else{
				map.put("resmsg", "관심사 검색 실패");
			}
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "관심사 검색 실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;
	}

	@GetMapping("/searchByUserId/{keyword}")
	@ApiOperation(value = "아이디 검색")
	private @ResponseBody ResponseEntity<Map<String, Object>> searchByUserId(@PathVariable("keyword") String keyword) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			List<UserVo> userList = ser.searchByUserId(keyword);
			Map<String, Object> map = new HashMap<String, Object>();
			if(userList != null && userList.size() > 0){
				map.put("resmsg", "아이디 검색 성공");
				map.put("resValue", userList);
			}else{
				map.put("resmsg", "아이디 검색 실패");
			}
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "아이디 검색 실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;
	}

	@GetMapping("/userList")
	@ApiOperation(value = "전체회원정보조회", response = List.class)
	private @ResponseBody ResponseEntity<Map<String, Object>> userList() {
		ResponseEntity<Map<String, Object>> resEntity = null;
		List<UserVo> userList = null;
		try {
			userList = ser.userList();
			Map<String, Object> map = new HashMap<String, Object>();
			if(userList != null && userList.size() > 0){
				map.put("resmsg", "조회성공");
				map.put("resValue", userList);
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

	@GetMapping("/wordCloud")
	@ApiOperation(value = "관심사 빈도 출력", response = List.class)
	private @ResponseBody ResponseEntity<Map<String, Object>> wordCloud() {
		ResponseEntity<Map<String, Object>> resEntity = null;
		List<WordCloudVo> wordList = null;
		try {
			wordList = ser.wordList();
			Map<String, Object> map = new HashMap<String, Object>();
			if(wordList != null && wordList.size() > 0){
				map.put("resmsg", "조회성공");
				map.put("resValue", wordList);
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

	private UserVo imageUpload(UserVo user, HttpServletResponse res, HttpServletRequest req){
		FileOutputStream fos;
    	String path = "/profile";
    	String realPath = req.getServletContext().getRealPath(path);
		
		boolean isDone = true;
		if(user.getProfileImage() != null && user.getProfileImage().getBase64() != ""){

			ImageVo image = user.getProfileImage();
			String ext = image.getBase64().substring(image.getBase64().indexOf("/")+1, image.getBase64().indexOf(";"));
			System.out.println(ext);
				byte[] decode = Base64.decodeBase64(image.getBase64().substring(image.getBase64().lastIndexOf(",")));
				String image_name = user.getUser_id() + "."+ext;
				String savePath = realPath+File.separator+image_name;
				System.out.println(savePath);
				String image_url = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + path + "/" +image_name;
				
				File f = new File(savePath);
				
				try {
					f.createNewFile();
					fos = new FileOutputStream(f);
					fos.write(decode);
					fos.close();
					user.setProfile_url(image_url);
					user.setProfile_filter(image.getFilter());
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					isDone = false;
				}
			}else{
				user.setProfile_url(req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + path + "/"+"default.png");
				user.setProfile_filter("normal");
			}
		
			return user;
	}
	
	private boolean imageDelete(String user_id, HttpServletResponse res, HttpServletRequest req){
    	String path = "/profile";
		String realPath = req.getServletContext().getRealPath(path);
		
		boolean isDelete = true;
		UserVo user = ser.info(user_id);
			String url = user.getProfile_url();
			String imageName = url.substring(url.lastIndexOf("/")+1);
			if(imageName.equals("default.png")){
				return isDelete;
			}
			String savePath = realPath+File.separator+imageName;
			File file = new File(savePath);
			if(file.exists()){
				if(file.delete()){
					System.out.println(user_id + " 삭제 성공");
				}else{
					System.out.println(user_id + " 삭제 실패");
					isDelete = false;
				}
			}

		return isDelete;
	}
	
	
	
}
