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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.ssafy.boot.dto.DirectMessageVo;
import edu.ssafy.boot.dto.UserDmVo;
import edu.ssafy.boot.service.IDirectMessageService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@CrossOrigin(origins = "*")
@RestController()
@RequestMapping("/api/directMessage")
public class DirectMessageController {

    @Autowired
	@Qualifier("DirectMessageService")
    IDirectMessageService ser;


    @PostMapping("/insertDirectMessage")
    @ApiOperation(value = "메시지추가")
    private @ResponseBody ResponseEntity<Map<String, Object>> insertDirectMessage(@RequestBody DirectMessageVo directMessage) {
        ResponseEntity<Map<String, Object>> resEntity = null;
        try {
            boolean insert = ser.insertDirectMessage(directMessage);
            Map<String, Object> map = new HashMap<String, Object>();
            if (insert)
                map.put("resmsg", "메시지추가성공");
            else
                map.put("resmsg", "1메시지추가실패");
            resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

        } catch (RuntimeException e) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("resmsg", "메시지추가실패");
            resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        }
        return resEntity;
    }

    @DeleteMapping("/deleteDirectMessage")
	@ApiOperation(value = "메시지삭제")
	private @ResponseBody ResponseEntity<Map<String, Object>> deleteDirectMessage(@RequestBody DirectMessageVo directMessage) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			boolean delete = ser.deleteDirectMessage(directMessage);
			Map<String, Object> map = new HashMap<String, Object>();
			if (delete)
				map.put("resmsg", "메시지삭제성공");
			else
				map.put("resmsg", "1메시지삭제실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "메시지삭제실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;
	}

    @PostMapping("/directMessageList")
	@ApiOperation(value = "메시지목록", response = List.class)
	private @ResponseBody ResponseEntity<Map<String, Object>> directMessageList(@RequestBody UserDmVo userDm) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			List<DirectMessageVo> directMessageList = ser.directMessageList(userDm);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "메시지목록성공");
			map.put("resvalue", directMessageList);
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "메시지목록실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;
	}
	
	@PutMapping("/readCheck")
	@ApiOperation(value = "메시지읽음")
	private @ResponseBody ResponseEntity<Map<String, Object>> directMessageReadCheck(@RequestBody UserDmVo userDm) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			boolean readCheck = ser.directMessageReadCheck(userDm);
			Map<String, Object> map = new HashMap<String, Object>();
			if (readCheck)
				map.put("resmsg", "메시지읽음성공");
			else
				map.put("resmsg", "1메시지읽음실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "메시지읽음실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		}
		return resEntity;
	}

	@GetMapping("/unReadCnt/{dm_id}")
	@ApiOperation(value = "안읽은메시지")
	private @ResponseBody ResponseEntity<Map<String, Object>> directMessageUnReadCnt(@PathVariable("dm_id") int dm_id) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
            int unreadCnt = ser.directMessageUnReadCnt(dm_id);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("resmsg", "안읽은메시지성공");
            map.put("resvalue", unreadCnt);
            resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
            
        } catch (RuntimeException e) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("resmsg", "안읽은메시지실패");
            resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        }
        return resEntity;
	}
}
    
