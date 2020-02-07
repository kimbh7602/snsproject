package edu.ssafy.boot.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
}
