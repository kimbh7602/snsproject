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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.ssafy.boot.dto.ContentVo;
import edu.ssafy.boot.dto.NotificationVo;
import edu.ssafy.boot.dto.ScrapVo;
import edu.ssafy.boot.service.INotificationService;
import edu.ssafy.boot.service.IScrapService;
import io.swagger.annotations.ApiOperation;



@CrossOrigin(origins="*")
@RestController()
@RequestMapping("/api/scrap")
public class ScrapController {

	@Autowired
	@Qualifier("ScrapService")
	IScrapService ser;

	@Autowired
    @Qualifier("NotificationService")
    INotificationService nSer;

	@PostMapping("/insertScrap")
	@ApiOperation(value = "스크랩")
	private @ResponseBody ResponseEntity<Map<String, Object>> insertScrap(@RequestBody ScrapVo scrap) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			boolean insert = ser.insertScrap(scrap);
			NotificationVo notification = new NotificationVo();
			notification.setUser_id(scrap.getUser_id());
			notification.setTarget_event_id(scrap.getContent_id());
			notification.setCategory("scrap");
			boolean insertNotification = nSer.insertNotification(notification);
			Map<String, Object> map = new HashMap<String, Object>();
			if (insert && insertNotification)
				map.put("resmsg", "스크랩성공");
			else
				map.put("resmsg", "1스크랩실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "스크랩실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;
	}
	
	@DeleteMapping("/deleteScrap")
	@ApiOperation(value = "스크랩취소")
	private @ResponseBody ResponseEntity<Map<String, Object>> deleteScrap(@RequestBody ScrapVo scrap) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			boolean delete = ser.deleteScrap(scrap);
			boolean deleteNotification = nSer.deleteScrap(scrap.getUser_id(), scrap.getContent_id());
			Map<String, Object> map = new HashMap<String, Object>();
			if (delete && deleteNotification)
				map.put("resmsg", "스크랩취소성공");
			else
				map.put("resmsg", "1스크랩취소실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "스크랩취소실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;
	}

	@GetMapping("/scrapList/{user_id}")
	@ApiOperation(value = "스크랩목록", response = List.class)
	private @ResponseBody ResponseEntity<Map<String, Object>> scrapList(@PathVariable("user_id") String user_id) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			List<ContentVo> scrapList = ser.scrapList(user_id);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "스크랩목록성공");
			map.put("resvalue", scrapList);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "스크랩목록실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;
	}
}
