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
import edu.ssafy.boot.dto.UserLikeVo;
import edu.ssafy.boot.service.IContentService;
import edu.ssafy.boot.service.INotificationService;
import edu.ssafy.boot.service.IUserLikeService;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController()
@RequestMapping("/api/userLike")
public class UserLikeController {

    @Autowired
	@Qualifier("UserLikeService")
    IUserLikeService ser;

    @Autowired
    @Qualifier("NotificationService")
    INotificationService nSer;

    @Autowired
    @Qualifier("ContentService")
    IContentService cSer;

    @PostMapping("/like")
    @ApiOperation(value = "좋아요")
    private @ResponseBody ResponseEntity<Map<String, Object>> userLike(@RequestBody UserLikeVo userLike) {
        ResponseEntity<Map<String, Object>> resEntity = null;
        try {
            boolean like = ser.userLike(userLike);
            NotificationVo notification = new NotificationVo();
            notification.setUser_id(userLike.getUser_id());
            notification.setTarget_event_id(userLike.getContent_id());
            notification.setCategory("like");
            ContentVo content = cSer.detail(userLike.getContent_id());
            notification.setTarget_user_id(content.getUser_id());
            boolean insert = nSer.insertNotification(notification);
            Map<String, Object> map = new HashMap<String, Object>();
            if (like && insert){
                map.put("resmsg", "좋아요성공");
                map.put("resValue", notification);
            }
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

    @DeleteMapping("/dislike")
    @ApiOperation(value = "좋아요취소")
    private @ResponseBody ResponseEntity<Map<String, Object>> userDislike(@RequestBody UserLikeVo userLike) {
        ResponseEntity<Map<String, Object>> resEntity = null;
        try {
            boolean dislike = ser.userDislike(userLike);
            boolean delete = nSer.deleteLike(userLike.getUser_id(), userLike.getContent_id());
            Map<String, Object> map = new HashMap<String, Object>();
            if (dislike && delete)
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

    @GetMapping("/userLikeList/{user_id}")
    @ApiOperation(value = "좋아요목록", response = List.class)
    public @ResponseBody ResponseEntity<Map<String, Object>> userLikeList(@PathVariable("user_id") String user_id) {
        ResponseEntity<Map<String, Object>> resEntity = null;
        try {
            List<ContentVo> userLikeList = ser.userLikeList(user_id);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("resmsg", "좋아요목록성공");
            map.put("resvalue", userLikeList);
            resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
            
        } catch (RuntimeException e) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("resmsg", "좋아요목록실패");
            resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        }
        return resEntity;
    }
    
}
    
