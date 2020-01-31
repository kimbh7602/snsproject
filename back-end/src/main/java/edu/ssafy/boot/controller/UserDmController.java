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

import edu.ssafy.boot.dto.DirectMessageVo;
import edu.ssafy.boot.dto.UserDmVo;
import edu.ssafy.boot.service.IUserDmService;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController()
@RequestMapping("/api/userDm")
public class UserDmController {

    @Autowired
	@Qualifier("UserDmService")
    IUserDmService ser;

    @PostMapping("/insertUserDm")
    @ApiOperation(value = "DM목록추가")
    private @ResponseBody ResponseEntity<Map<String, Object>> insertUserDm(@RequestBody UserDmVo userDm) {
        ResponseEntity<Map<String, Object>> resEntity = null;
        try {
            boolean insert = ser.insertUserDm(userDm);
            Map<String, Object> map = new HashMap<String, Object>();
            if (insert)
                map.put("resmsg", "DM목록추가성공");
            else
                map.put("resmsg", "1DM목록추가실패");
            resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

        } catch (RuntimeException e) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("resmsg", "DM목록추가실패");
            resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        }
        return resEntity;
    }

    @DeleteMapping("/deleteUserDm")
    @ApiOperation(value = "DM목록삭제")
    private @ResponseBody ResponseEntity<Map<String, Object>> deleteUserDm(@RequestBody int dm_id) {
        ResponseEntity<Map<String, Object>> resEntity = null;
        try {
            boolean delete = ser.deleteUserDm(dm_id);
            Map<String, Object> map = new HashMap<String, Object>();
            if (delete)
                map.put("resmsg", "DM목록삭제성공");
            else
                map.put("resmsg", "1DM목록삭제실패");
            resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

        } catch (RuntimeException e) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("resmsg", "DM목록삭제실패");
            resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        }
        return resEntity;
    }

    @PutMapping("/updateUserDm")
    @ApiOperation(value = "DM목록수정")
    private @ResponseBody ResponseEntity<Map<String, Object>> updateUserDm(@RequestBody DirectMessageVo message) {
        ResponseEntity<Map<String, Object>> resEntity = null;
        try {
            boolean update = ser.updateUserDm(message);
            Map<String, Object> map = new HashMap<String, Object>();
            if (update)
                map.put("resmsg", "DM목록수정성공");
            else
                map.put("resmsg", "1DM목록수정실패");
            resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

        } catch (RuntimeException e) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("resmsg", "DM목록수정실패");
            resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        }
        return resEntity;
    }

    @GetMapping("/userDmList/{user_id}")
    @ApiOperation(value = "DM목록", response = List.class)
    public @ResponseBody ResponseEntity<Map<String, Object>> userDmList(@PathVariable("user_id") String user_id) {
        ResponseEntity<Map<String, Object>> resEntity = null;
        try {
            List<UserDmVo> userDmList = ser.userDmList(user_id);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("resmsg", "DM목록성공");
            map.put("resvalue", userDmList);
            resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
            
        } catch (RuntimeException e) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("resmsg", "DM목록실패");
            resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        }
        return resEntity;
    }

    @GetMapping("/allDmList")
    @ApiOperation(value = "DM목록", response = List.class)
    public @ResponseBody ResponseEntity<Map<String, Object>> allDmList() {
        ResponseEntity<Map<String, Object>> resEntity = null;
        try {
            List<UserDmVo> userDmList = ser.allDmList();
            System.out.println(userDmList);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("resmsg", "DM목록성공");
            map.put("resvalue", userDmList);
            resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
            
        } catch (RuntimeException e) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("resmsg", "DM목록실패");
            resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        }
        return resEntity;
    }
}
    
