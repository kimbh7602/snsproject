package edu.ssafy.boot.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

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

import edu.ssafy.boot.dto.BlockVo;
import edu.ssafy.boot.dto.ContentVo;
import edu.ssafy.boot.dto.LogVo;
import edu.ssafy.boot.dto.NotificationVo;
import edu.ssafy.boot.dto.UserReportVo;
import edu.ssafy.boot.service.IBlockchainService;
import edu.ssafy.boot.service.IContentService;
import edu.ssafy.boot.service.INotificationService;
import edu.ssafy.boot.service.IUserReportService;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RestController()
@RequestMapping("/api/userReport")
public class UserReportController {

    @Autowired
	@Qualifier("UserReportService")
    IUserReportService ser;

    @Autowired
    @Qualifier("NotificationService")
    INotificationService nSer;

	@Autowired
	@Qualifier("BlockchainService")
	IBlockchainService serbc;
	
    @Autowired
    @Qualifier("ContentService")
    IContentService cSer;

    @PostMapping("/report")
    @ApiOperation(value = "신고")
    private @ResponseBody ResponseEntity<Map<String, Object>> userReport(@RequestBody UserReportVo report, HttpServletRequest req) {
        ResponseEntity<Map<String, Object>> resEntity = null;
        try {
            boolean insertReport = ser.insertReport(report);
            NotificationVo notification = new NotificationVo();
            notification.setUser_id(report.getUser_id());
            notification.setTarget_event_id(report.getContent_id());
            notification.setCategory("report");
            ContentVo content = cSer.detail(report.getContent_id());
            notification.setTarget_user_id(content.getUser_id());
            boolean insert = nSer.insertNotification(notification);
            Map<String, Object> map = new HashMap<String, Object>();
            if (insertReport && insert){
                map.put("resmsg", "신고 성공");
                map.put("resValue", notification);
                LogVo log = new LogVo(report.getUser_id(), req.getRemoteAddr(), "게시물신고");
    			BlockVo block = new BlockVo(log);
    			serbc.addBlock(block);
            }
            else
                map.put("resmsg", "1신고실패");
            resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

        } catch (RuntimeException e) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("resmsg", "신고실패");
            resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        }
        return resEntity;
    }

    @DeleteMapping("/deleteReport")
    @ApiOperation(value = "신고취소")
    private @ResponseBody ResponseEntity<Map<String, Object>> deleteReport(@RequestBody UserReportVo report, HttpServletRequest req) {
        ResponseEntity<Map<String, Object>> resEntity = null;
        try {
            boolean dislike = ser.deleteReport(report);
            NotificationVo notification = new NotificationVo();
            notification.setUser_id(report.getUser_id());
            notification.setTarget_event_id(report.getContent_id());
            notification.setCategory("report");
            ContentVo content = cSer.detail(report.getContent_id());
            notification.setTarget_user_id(content.getUser_id());
            boolean delete = nSer.deleteLike(report.getUser_id(), report.getContent_id());
            Map<String, Object> map = new HashMap<String, Object>();
            if (dislike && delete){
                map.put("resmsg", "신고취소성공");
                map.put("resValue", notification);
                LogVo log = new LogVo(report.getUser_id(), req.getRemoteAddr(), "게시물신고취소");
    			BlockVo block = new BlockVo(log);
    			serbc.addBlock(block);
            }
            else
                map.put("resmsg", "1신고취소실패");
            resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

        } catch (RuntimeException e) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("resmsg", "신고취소실패");
            resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        }
        return resEntity;
    }

    @GetMapping("/myReportList/{user_id}")
    @ApiOperation(value = "신고목록", response = List.class)
    public @ResponseBody ResponseEntity<Map<String, Object>> myReportList(@PathVariable("user_id") String user_id) {
        ResponseEntity<Map<String, Object>> resEntity = null;
        try {
            List<UserReportVo> reportList = ser.selectMyReport(user_id);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("resmsg", "신고목록성공");
            map.put("resvalue", reportList);
            resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
            
        } catch (RuntimeException e) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("resmsg", "신고목록실패");
            resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        }
        return resEntity;
    }

    @GetMapping("/reportContentList/{user_id}")
    @ApiOperation(value = "신고게시물목록", response = List.class)
    public @ResponseBody ResponseEntity<Map<String, Object>> reportContentList(@PathVariable("user_id") String user_id) {
        ResponseEntity<Map<String, Object>> resEntity = null;
        try {
            List<ContentVo> reportContentList = ser.reportContent(user_id);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("resmsg", "신고게시물목록성공");
            map.put("resvalue", reportContentList);
            resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
            
        } catch (RuntimeException e) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("resmsg", "신고게시물목록실패");
            resEntity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        }
        return resEntity;
    }
    @GetMapping("/adminreportContentList")
	@ApiOperation(value = "신고리스트출력", response = List.class)
	private @ResponseBody ResponseEntity<Map<String, Object>> adminreportContentList() throws ServletException, IOException {
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> msg = new HashMap<String, Object>();
		List<ContentVo> list = ser.adminreportContentList();
		msg.put("resmsg", "타임라인 출력 성공");
		msg.put("resValue", list);
		
		resEntity = new ResponseEntity<Map<String,Object>>(msg, HttpStatus.OK);
		return resEntity;
	}
    
    @GetMapping("/getreportcategory/{content_id}")
	@ApiOperation(value ="신고카테고리출력", response = List.class)
	private @ResponseBody ResponseEntity<Map<String, Object>> getreportcategory(@PathVariable("content_id") String content_id) {
    	ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> msg = new HashMap<String, Object>();
		List<String> list = ser.getreportcategory(content_id);
		List<Integer> last = new ArrayList<Integer>();
		for (int i = 0; i < list.size(); i++) {
			last.add(ser.getreportvalue(content_id, list.get(i)));
		}
		List<String> exval = ser.getreportexval(content_id);
		msg.put("resmsg", "타임라인 출력 성공");
		msg.put("resvalue", list);
		msg.put("resvalue1", last);
		msg.put("resvalue2", exval);
		
		
		
		resEntity = new ResponseEntity<Map<String,Object>>(msg, HttpStatus.OK);
		return resEntity;
	}
}
    
