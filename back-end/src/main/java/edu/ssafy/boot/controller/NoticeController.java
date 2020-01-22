package edu.ssafy.boot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import edu.ssafy.boot.dto.NoticeVo;
import edu.ssafy.boot.service.INoticeService;
import io.swagger.annotations.ApiOperation;


@CrossOrigin(origins = "*")
@RestController()
@RequestMapping("/api/notice")
public class NoticeController {
    
    @Autowired
    @Qualifier("NoticeService")
    INoticeService ser;

    @PostMapping("/insertNotice")
	@ApiOperation(value = "공지사항 추가")
	private @ResponseBody ResponseEntity<Map<String, Object>> insertNotice(@RequestBody NoticeVo notice) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			boolean res = ser.insertNotice(notice);
			Map<String, Object> map = new HashMap<String, Object>();
			if (res) {
				map.put("resmsg", "공지사항 추가 성공");
			} else {
				map.put("resmsg", "공지사항 추가 실패1");
			}
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "공지사항 추가 실패2");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;
	}

	@PutMapping("/updateNotice")
	@ApiOperation(value = "공지사항 수정")
	private ResponseEntity<Map<String, Object>> updateNotice(@RequestBody NoticeVo notice) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			boolean res = ser.updateNotice(notice);
			Map<String, Object> map = new HashMap<String, Object>();
			if(res){
				map.put("resmsg", "수정 성공");
				map.put("resvalue", res);
			}else{
				map.put("resmsg", "수정 실패");
			}
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "수정 실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

		}
		return resEntity;
	}
	
	@DeleteMapping("/deleteNotice/{id}")
	@ApiOperation(value = "공지사항 삭제")
	private ResponseEntity<Map<String, Object>> deleteNotice(@PathVariable("id") String id) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			boolean res = ser.deleteNotice(id);
			Map<String, Object> map = new HashMap<String, Object>();
			if(res){
				map.put("resmsg", "삭제 성공");
				map.put("resvalue", res);
			}else{
				map.put("resmsg", "삭제 실패1");
			}
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "삭제 실패2");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

		}
		return resEntity;
	}

	@GetMapping("/noticeList")
	@ApiOperation(value = "공지사항조최", response = List.class)
	private @ResponseBody ResponseEntity<Map<String, Object>> noticeList() {
		ResponseEntity<Map<String, Object>> resEntity = null;
		List<NoticeVo> noticeList = null;
		try {
			noticeList = ser.selectNoticeList();
			Map<String, Object> map = new HashMap<String, Object>();
			if(noticeList != null && noticeList.size() > 0){
				map.put("resmsg", "조회성공");
				map.put("resValue", noticeList);
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

	@GetMapping("/noticeNow")
	@ApiOperation(value = "최근 공지사항 조회", response = List.class)
	private @ResponseBody ResponseEntity<Map<String, Object>> noticeNow() {
		ResponseEntity<Map<String, Object>> resEntity = null;
		List<NoticeVo> noticeNow = null;
		try {
			noticeNow = ser.selectNoticeNow();
			Map<String, Object> map = new HashMap<String, Object>();
			if(noticeNow != null && noticeNow.size() > 0){
				map.put("resmsg", "조회성공");
				map.put("resValue", noticeNow);
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
}