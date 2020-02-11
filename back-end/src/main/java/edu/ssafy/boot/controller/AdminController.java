package edu.ssafy.boot.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.ssafy.boot.dto.BlockVo;
import edu.ssafy.boot.service.IBlockchainService;
import edu.ssafy.boot.service.IUserService;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController()
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	@Qualifier("BlockchainService")
	IBlockchainService serbc;
	
	@Autowired
	@Qualifier("UserService")
	IUserService ser;

	@GetMapping("/info")
	@ApiOperation(value = "로그전체조회", response = BlockVo.class)
	private @ResponseBody ResponseEntity<Map<String, Object>> infoLog() {
		ResponseEntity<Map<String, Object>> resEntity = null;
		List<BlockVo> chain = null;
		List<BlockVo> chain2 = new ArrayList<BlockVo>();
		try {
			chain = serbc.getChain();
			Map<String, Object> map = new HashMap<String, Object>();
			if (chain != null) {
				for (int i = 1; i < (int) chain.size(); i++) {
					chain2.add(chain.get(i));
				}
				map.put("resmsg", "조회성공");
				map.put("resvalue", chain2);
			} else {
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

	@GetMapping("/trust")
	@ApiOperation(value = "신뢰성검사")
	private  @ResponseBody ResponseEntity<Map<String, Object>> trust() {
		boolean trust = false;
		ResponseEntity<Map<String, Object>> resEntity = null;
		trust = serbc.isValid();
		Map<String, Object> map = new HashMap<String, Object>();
		if (trust) {
			map.put("resmsg", "신뢰");
		} else {
			map.put("resmsg", "비신뢰");
		}
		resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		return resEntity;
	}
	
	@GetMapping("/todayvisit")
	@ApiOperation(value = "하루방문자수")
	private  @ResponseBody ResponseEntity<Map<String, Object>> today() {
		
		ResponseEntity<Map<String, Object>> resEntity = null;
		String result = null;
		result = ser.usertoday();
		Map<String, Object> map = new HashMap<String, Object>();
		if (result.equals(null)) {
			map.put("resmsg", "조회실패");
		} else {
			map.put("resmsg", "조회성공");
			map.put("resvalue", result);
		}
		resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		return resEntity;
	}
	
	@GetMapping("/totalcontents")
	@ApiOperation(value = "월별게시글수")
	private  @ResponseBody ResponseEntity<Map<String, Object>> totalcontents() {
		
		ResponseEntity<Map<String, Object>> resEntity = null;
		int result [] = new int [13];
		for (int i = 1; i < 13; i++) {
			result[i-1]=ser.totalcontents(i);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if (result.equals(null)) {
			map.put("resmsg", "조회실패");
		} else {
			map.put("resmsg", "조회성공");
			map.put("resvalue", result);
		}
		resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		return resEntity;
	}
	
	@GetMapping("/moncontents")
	@ApiOperation(value = "주별게시글수")
	private  @ResponseBody ResponseEntity<Map<String, Object>> moncontents() {
		int currentdate = ser.currentdate();
		
		ResponseEntity<Map<String, Object>> resEntity = null;
		int result [] = new int [5];
		for (int i = 1; i < 6; i++) {
			result[5-i]= ser.moncontents(currentdate+1-i);
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		if (result.equals(null)) {
			map.put("resmsg", "조회실패");
		} else {
			map.put("resmsg", "조회성공");
			map.put("resvalue", result);
		}
		resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		return resEntity;
	}
	
	@GetMapping("/totaluser")
	@ApiOperation(value = "월별회원가입자수")
	private  @ResponseBody ResponseEntity<Map<String, Object>> totlauser() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy.MM.dd", Locale.KOREA );
		Date currentTime = new Date();
		String dTime = formatter.format ( currentTime );
		List<BlockVo> chain = serbc.getChain();
		List<BlockVo> result1 = new ArrayList<BlockVo>();
		for (int i = 1; i < chain.size(); i++) {
			if(chain.get(i).getData().getUser_status().equals("회원가입"))
				result1.add(chain.get(i));
		}
		ResponseEntity<Map<String, Object>> resEntity = null;
		int result [] = new int [13];
		for (int i = 1; i < 13; i++) {
			for (int j = 1; j < chain.size(); j++) {
				Date blocktime = formatter.parse(chain.get(j).getTimestamp());
				if(blocktime.getMonth()==i-1  && chain.get(j).getData().getUser_status().equals("회원가입")) {
					result[i-1]++;
				}	
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if (result.equals(null)) {
			map.put("resmsg", "조회실패");
		} else {
			map.put("resmsg", "조회성공");
			map.put("resvalue", result);
		}
		resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		return resEntity;
	}
	
	@GetMapping("/monuser")
	@ApiOperation(value = "최근한달회원가입수자")
	private  @ResponseBody ResponseEntity<Map<String, Object>> monuser() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy.MM.dd", Locale.KOREA );
		List<BlockVo> chain = serbc.getChain();
		List<BlockVo> result1 = new ArrayList<BlockVo>();
		for (int i = 1; i < chain.size(); i++) {
			if(chain.get(i).getData().getUser_status().equals("회원가입"))
				result1.add(chain.get(i));
		}
		ResponseEntity<Map<String, Object>> resEntity = null;
		int result [] = new int [13];
		for (int i = 1; i < 13; i++) {
			for (int j = 1; j < result1.size(); j++) {
				Date blocktime = formatter.parse(result1.get(j).getTimestamp());
				if(blocktime.getMonth()==i-1) {
					result[i-1]++;
				}	
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if (result.equals(null)) {
			map.put("resmsg", "조회실패");
		} else {
			map.put("resmsg", "조회성공");
			map.put("resvalue", result);
		}
		resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		return resEntity;
	}
	
	@GetMapping("/dayuser")
	@ApiOperation(value = "최근한달회원가입수자")
	private  @ResponseBody ResponseEntity<Map<String, Object>> dayuser() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy.MM.dd", Locale.KOREA );
		Date currentTime = new Date();
		String dTime = formatter.format ( currentTime );
		List<BlockVo> chain = serbc.getChain();
		List<BlockVo> result1 = new ArrayList<BlockVo>();
		for (int i = 1; i < chain.size(); i++) {
			if(chain.get(i).getData().getUser_status().equals("회원가입"))
				result1.add(chain.get(i));
		}
		ResponseEntity<Map<String, Object>> resEntity = null;
		int result [] = new int [30];
		for (int i = 29; i > -1; i--) {
			for (int j = 0; j < result1.size(); j++) {
				Date blocktime = formatter.parse(result1.get(j).getTimestamp());
				if(blocktime.getDate()==currentTime.getDate()-i) {
					result[29-i]++;
				}	
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if (result.equals(null)) {
			map.put("resmsg", "조회실패");
		} else {
			map.put("resmsg", "조회성공");
			map.put("resvalue", result);
		}
		resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		return resEntity;
	}
	
}
