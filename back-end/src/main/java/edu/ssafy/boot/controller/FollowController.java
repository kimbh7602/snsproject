package edu.ssafy.boot.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.ssafy.boot.dto.FollowVo;
import edu.ssafy.boot.dto.UserVo;
import edu.ssafy.boot.service.IFollowService;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins="*")
@RestController()
@RequestMapping("/api/follow")
public class FollowController {

	@Autowired
	@Qualifier("FollowService")
	IFollowService ser;

	@PostMapping("/insertFollow")
	@ApiOperation(value = "팔로우")
	private @ResponseBody ResponseEntity<Map<String, Object>> follow(@RequestBody FollowVo follow) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			boolean followres = ser.follow(follow);
			Map<String, Object> map = new HashMap<String, Object>();
			if (followres)
				map.put("resmsg", "팔로우성공");
			else
				map.put("resmsg", "1팔로우실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "팔로우실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;
	}

	@PostMapping("/deleteFollow")
	@ApiOperation(value = "팔로우취소")
	private @ResponseBody ResponseEntity<Map<String, Object>> unfollow(@RequestBody FollowVo follow) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			boolean followres = ser.unfollow(follow);
			Map<String, Object> map = new HashMap<String, Object>();
			if (followres)
				map.put("resmsg", "팔로우취소성공");
			else
				map.put("resmsg", "1팔로우취소실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "팔로우취소실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;
	}
}
