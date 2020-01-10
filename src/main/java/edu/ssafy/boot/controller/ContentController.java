package edu.ssafy.boot.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

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

import edu.ssafy.boot.dto.ContentVo;
import edu.ssafy.boot.service.IContentService;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins="*")
@RestController()
@RequestMapping("/api/content")
public class ContentController {
	
	@Autowired
	@Qualifier("ContentService")
	IContentService ser;
	
	@GetMapping("/contentMyList/{user_id}")
	@ApiOperation(value = "타임라인 출력", response = List.class)
	private @ResponseBody ResponseEntity<Map<String, Object>> contentMyList(@PathVariable("user_id") String user_id) throws ServletException, IOException {
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> msg = new HashMap<String, Object>();
		List<ContentVo> list = ser.contentMyList(user_id);
		msg.put("resmsg", "타임라인 출력 성공");
		msg.put("resvalue", list);
		resEntity = new ResponseEntity<Map<String,Object>>(msg, HttpStatus.OK);
		return resEntity;
	}
	
	@GetMapping("/detail/{content_id}")
	@ApiOperation(value = "게시물 출력", response = ContentVo.class)
	private @ResponseBody ResponseEntity<Map<String, Object>> detail(@PathVariable("content_id") int content_id) throws ServletException, IOException {
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> msg = new HashMap<String, Object>();
		ContentVo content = ser.detail(content_id);
		List<String> urls = ser.detailUrls(content_id);
		msg.put("resmsg", "게시물 출력 성공");
		msg.put("content", content);
		msg.put("urls", urls);
		resEntity = new ResponseEntity<Map<String,Object>>(msg, HttpStatus.OK);
		return resEntity;
	}
}
