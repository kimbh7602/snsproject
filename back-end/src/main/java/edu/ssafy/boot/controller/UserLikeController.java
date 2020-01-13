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

import edu.ssafy.boot.dto.UserLikeVo;
import edu.ssafy.boot.service.IUserLikeService;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController()
@RequestMapping("/api/userLike")
public class UserLikeController {

    @Autowired
	@Qualifier("UserLikeService")
    IUserLikeService ser;

    @PostMapping("/like")
    @ApiOperation(value = "좋아요")
    private @ResponseBody ResponseEntity<Map<String, Object>> userLike(@RequestBody UserLikeVo userLike) {
        ResponseEntity<Map<String, Object>> resEntity = null;
        try {
            boolean like = ser.userLike(userLike);
            Map<String, Object> map = new HashMap<String, Object>();
            if (like)
                map.put("resmsg", "좋아요성공");
            else
                map.put("resmsg", "1좋아요실패");
            resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

        } catch (RuntimeException e) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("resmsg", "좋아요실패");
            resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        }
        return resEntity;
    }

    @PostMapping("/dislike")
    @ApiOperation(value = "좋아요취소")
    private @ResponseBody ResponseEntity<Map<String, Object>> userDislike(@RequestBody UserLikeVo userLike) {
        ResponseEntity<Map<String, Object>> resEntity = null;
        try {
            boolean dislike = ser.userDislike(userLike);
            Map<String, Object> map = new HashMap<String, Object>();
            if (dislike)
                map.put("resmsg", "좋아요취소성공");
            else
                map.put("resmsg", "1좋아요취소실패");
            resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

        } catch (RuntimeException e) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("resmsg", "좋아요취소실패");
            resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        }
        return resEntity;
    }
}
    
