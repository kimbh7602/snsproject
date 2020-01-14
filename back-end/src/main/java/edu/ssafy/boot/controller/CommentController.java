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

import edu.ssafy.boot.dto.CommentVo;
import edu.ssafy.boot.dto.ContentVo;
import edu.ssafy.boot.dto.UserVo;
import edu.ssafy.boot.service.ICommentService;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController()
@RequestMapping("/api/comment")
public class CommentController {

	@Autowired
	@Qualifier("CommentService")
	ICommentService ser;

	@GetMapping("/commentsList/{content_id}")
	@ApiOperation(value = "댓글 출력", response = List.class)
	private @ResponseBody ResponseEntity<Map<String, Object>> commentList(
			@PathVariable("content_id") String content_id) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> msg = new HashMap<String, Object>();
		List<CommentVo> list = ser.commentsList(content_id);
		msg.put("resmsg", "댓글 출력성공");
		msg.put("resvalue", list);
		resEntity = new ResponseEntity<Map<String, Object>>(msg, HttpStatus.OK);
		return resEntity;
	}

	@PostMapping("/insertComment")
	@ApiOperation(value = "댓글 작성", response = List.class)
	private @ResponseBody ResponseEntity<Map<String, Object>> insertComment(@RequestBody CommentVo comment) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> msg = new HashMap<String, Object>();
		boolean resComment = ser.insertContent(comment);
		if (resComment) {
			msg.put("resmsg", "댓글 추가 성공");
		} else {
			msg.put("resmsg", "댓글 추가 실패");
		}
		resEntity = new ResponseEntity<Map<String, Object>>(msg, HttpStatus.OK);
		return resEntity;
	}
	
	@PostMapping("/insertReComment")
	@ApiOperation(value = "대댓글 작성", response = List.class)
	private @ResponseBody ResponseEntity<Map<String, Object>> insertReComment(@RequestBody CommentVo comment) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> msg = new HashMap<String, Object>();
		boolean resComment = ser.insertReComment(comment);
		if (resComment) {
			msg.put("resmsg", "대댓글 추가 성공");
		} else {
			msg.put("resmsg", "대댓글 추가 실패");
		}
		resEntity = new ResponseEntity<Map<String, Object>>(msg, HttpStatus.OK);
		return resEntity;
	}

	@PutMapping("/updateComment")
	@ApiOperation(value = "댓글 수정")
	private ResponseEntity<Map<String, Object>> update(@RequestBody CommentVo comment) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			boolean res = ser.updateComments(comment);
			Map<String, Object> map = new HashMap<String, Object>();
			if (res) {
				map.put("resmsg", "수정성공");
				map.put("resvalue", res);
			} else {
				map.put("resmsg", "수정실패");
			}
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "수정실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

		}
		return resEntity;
	}

	@DeleteMapping("/deleteComment")
	@ApiOperation(value = "댓글 삭제")
	private ResponseEntity<Map<String, Object>> delete(@RequestBody CommentVo comment) {
		ResponseEntity<Map<String, Object>> resEntity = null;
		try {
			boolean res = ser.deleteComments(comment);
			Map<String, Object> map = new HashMap<String, Object>();
			if (res) {
				map.put("resmsg", "삭제성공");
				map.put("resvalue", res);
			} else {
				map.put("resmsg", "삭제실패");
			}
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (RuntimeException e) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("resmsg", "삭제실패");
			resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

		}
		return resEntity;
	}
}
