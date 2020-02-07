package edu.ssafy.boot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.ssafy.boot.dto.NotificationVo;
import edu.ssafy.boot.service.INotificationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@CrossOrigin(origins = "*")
@RestController()
@RequestMapping("/api/notification")
public class NotificationController {
    
    @Autowired
    @Qualifier("NotificationService")
    INotificationService ser;

	@GetMapping("/selectList/{user_id}")
	@ApiOperation(value = "알림 목록", response = List.class)
	private @ResponseBody ResponseEntity<Map<String, Object>> selectList(@PathVariable("user_id") String user_id) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			List<NotificationVo> notificationList = ser.selectList(user_id);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "알림목록성공");
			map.put("resvalue", notificationList);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "알림목록실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;
	}

	@GetMapping("/uncheckedList/{user_id}")
	@ApiOperation(value = "확인 전 알림 목록", response = List.class)
	private @ResponseBody ResponseEntity<Map<String, Object>> uncheckedList(@PathVariable("user_id") String user_id) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			List<NotificationVo> notificationList = ser.selectUncheckedList(user_id);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "확인 전 알림목록성공");
			map.put("resvalue", notificationList);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "확인 전 알림목록실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;
	}

	@GetMapping("/categoryList/{user_id}/{category}")
	@ApiOperation(value = "카테고리별 알림 목록", response = List.class)
	private @ResponseBody ResponseEntity<Map<String, Object>> categoryList(@PathVariable("user_id") String user_id, @PathVariable("category") String category) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			List<NotificationVo> notificationList = ser.selectCategory(user_id, category);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "카테고리별 알림목록성공");
			map.put("resvalue", notificationList);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "카테고리별 알림목록실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;
	}

	@GetMapping("/categoryUncheckedList/{user_id}/{category}")
	@ApiOperation(value = "확인 전 카테고리별 알림 목록", response = List.class)
	private @ResponseBody ResponseEntity<Map<String, Object>> categoryUncheckedList(@PathVariable("user_id") String user_id, @PathVariable("category") String category) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			List<NotificationVo> notificationList = ser.selectUncheckedCategory(user_id, category);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "확인 전 카테고리별 알림목록성공");
			map.put("resvalue", notificationList);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "확인 전 카테고리별 알림목록실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;
	}

	@PutMapping("/updateCheck/{notification_id}")
	@ApiOperation(value = "알림 확인 체크", response = List.class)
	private @ResponseBody ResponseEntity<Map<String, Object>> updateCheck(@PathVariable("notification_id") int notification_id) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			boolean update = ser.updateCheck(notification_id);
			Map<String, Object> map = new HashMap<String, Object>();
			if(update){
				map.put("resmsg", "체크 성공");
				resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
			}else{
				map.put("resmsg", "체크 실패");
				resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
			}
		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "체크 실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;
	}

	@GetMapping("/countUnchecked/{user_id}")
	@ApiOperation(value = "확인 전 알림 숫자", response = List.class)
	private @ResponseBody ResponseEntity<Map<String, Object>> countUnchecked(@PathVariable("user_id") String user_id) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			int count = ser.countUnchecked(user_id);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "확인 전 알림숫자성공");
			map.put("resvalue", count);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "확인 전 알림숫자실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;
	}
}