package edu.ssafy.boot.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;
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

import edu.ssafy.boot.dto.ContentVo;
import edu.ssafy.boot.dto.ImageVo;
import edu.ssafy.boot.dto.LocationVo;
import edu.ssafy.boot.service.IContentService;
import edu.ssafy.boot.service.IImageService;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins="*")
@RestController()
@RequestMapping("/api/content")
public class ContentController {
	
	@Autowired
	@Qualifier("ContentService")
	IContentService ser;

	@Autowired
	@Qualifier("ImageService")
	IImageService iSer;
	
	@GetMapping("/contentMyList/{user_id}")
	@ApiOperation(value = "타임라인 출력", response = List.class)
	private @ResponseBody ResponseEntity<Map<String, Object>> contentMyList(@PathVariable("user_id") String user_id) throws ServletException, IOException {
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> msg = new HashMap<String, Object>();
		List<ContentVo> list = ser.contentMyList(user_id);
		msg.put("resmsg", "타임라인 출력 성공");
		msg.put("resValue", list);
		resEntity = new ResponseEntity<Map<String,Object>>(msg, HttpStatus.OK);
		return resEntity;
	}

	@GetMapping("/contentUserList/{user_id}")
	@ApiOperation(value = "개인 게시물 리스트", response = List.class)
	private @ResponseBody ResponseEntity<Map<String, Object>> contentUserList(@PathVariable("user_id") String user_id) throws ServletException, IOException {
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> msg = new HashMap<String, Object>();
		List<ContentVo> list = ser.contentUserList(user_id);
		msg.put("resmsg", "개인 게시물 리스트 출력 성공");
		msg.put("resValue", list);
		resEntity = new ResponseEntity<Map<String,Object>>(msg, HttpStatus.OK);
		return resEntity;
	}
	
	@GetMapping("/detail/{content_id}")
	@ApiOperation(value = "게시물 출력", response = ContentVo.class)
	private @ResponseBody ResponseEntity<Map<String, Object>> detail(@PathVariable("content_id") int content_id) throws ServletException, IOException {
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> msg = new HashMap<String, Object>();
		ContentVo content = ser.detail(content_id);
		msg.put("resmsg", "게시물 출력 성공");
		msg.put("resValue", content);
		resEntity = new ResponseEntity<Map<String,Object>>(msg, HttpStatus.OK);
		return resEntity;
	}
	
	@GetMapping("/urls/{content_id}")
	@ApiOperation(value = "게시물 이미지 url", response = List.class)
	private @ResponseBody ResponseEntity<Map<String, Object>> urls(@PathVariable("content_id") int content_id) throws ServletException, IOException {
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> msg = new HashMap<String, Object>();
		List<String> urls = ser.detailUrls(content_id);
		msg.put("resmsg", "게시물 출력 성공");
		msg.put("urls", urls);
		resEntity = new ResponseEntity<Map<String,Object>>(msg, HttpStatus.OK);
		return resEntity;
	}
	
	@PostMapping("/insertContent")
	@ApiOperation(value = "게시물 추가")
	private @ResponseBody ResponseEntity<Map<String, Object>> insertContent(@RequestBody ContentVo content,  HttpServletResponse res, HttpServletRequest req){
		System.out.println("asdfasdfasdfasfafasfdasfsdfasdfasdasfsd");
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> msg = new HashMap<String, Object>();
		boolean resContent = ser.insertContent(content);
		int content_id = ser.selectContentId(content.getUser_id());
		content.setContent_id(content_id);
		boolean resImage = imageUpload(content, res, req);
		if(resContent && resImage){
			msg.put("resmsg", "게시물 추가 성공");
		}else{
			msg.put("resmsg", "게시물 추가 실패");
		}
		resEntity = new ResponseEntity<Map<String,Object>>(msg, HttpStatus.OK);
		return resEntity;
	}

	private boolean imageUpload(ContentVo content, HttpServletResponse res, HttpServletRequest req){
		FileOutputStream fos;
    	String path = "/upload";
    	String realPath = req.getServletContext().getRealPath(path);
		
		int num = 1;
		boolean isDone = true;
		if(content.getImageList().size() == 1 && content.getImageList().get(0).getBase64() == ""){
			iSer.insertImage(new ImageVo(content.getContent_id(), content.getContent_id()+"-1.png", req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + path + "/"+content.getContent_id()+"-1.png", "normal"));
		}else{

			for (ImageVo image : content.getImageList()) {
				byte[] decode = Base64.decodeBase64(image.getBase64().substring(image.getBase64().lastIndexOf(",")));
				String image_name = content.getContent_id()+"-"+num+".jpg";
				String savePath = realPath+File.separator+image_name;
				String image_url = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + path + "/" +image_name;
				
				File f = new File(savePath);
				
				try {
					f.createNewFile();
					fos = new FileOutputStream(f);
					fos.write(decode);
					fos.close();
					
					image.setContent_id(content.getContent_id());
					image.setImage_name(image_name);
					image.setImage_url(image_url);
					
					iSer.insertImage(image);
					System.out.println(image.toString());
					num++;
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					isDone = false;
				}
			};
		}
			return isDone;
	}

	@DeleteMapping("/deleteContent/{content_id}")
	@ApiOperation(value = "게시물 삭제")
	private @ResponseBody ResponseEntity<Map<String, Object>> updateContent(@PathVariable("content_id") int content_id,  HttpServletResponse res, HttpServletRequest req){
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> msg = new HashMap<String, Object>();
		boolean resContent = ser.deleteContent(content_id);
		boolean resImage = imageDelete(content_id, res, req);
		if(resContent && resImage){
			msg.put("resmsg", "게시물 삭제 성공");
		}else{
			msg.put("resmsg", "게시물 삭제 실패");
		}
		resEntity = new ResponseEntity<Map<String,Object>>(msg, HttpStatus.OK);
		return resEntity;
	}

	private boolean imageDelete(int content_id, HttpServletResponse res, HttpServletRequest req){
    	String path = "/upload";
		String realPath = req.getServletContext().getRealPath(path);
		
		List<ImageVo> imageList = iSer.imageList(content_id);

		boolean isDelete = true;
		for (ImageVo imageVo : imageList) {
			String savePath = realPath+File.separator+imageVo.getImage_name();
			File file = new File(savePath);
			if(file.exists()){
				if(file.delete()){
					System.out.println(imageVo.getImage_name() + " 삭제 성공");
				}else{
					System.out.println(imageVo.getImage_name() + " 삭제 실패");
					isDelete = false;
				}
			}
		}

		boolean isDbDelete = iSer.deleteImage(content_id);


		return isDelete&&isDbDelete;
	}

	@PutMapping("/updateContent")
	@ApiOperation(value = "게시물 수정")
	private @ResponseBody ResponseEntity<Map<String, Object>> updateContent(@RequestBody ContentVo content,  HttpServletResponse res, HttpServletRequest req){
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> msg = new HashMap<String, Object>();
		boolean resContent = ser.updateContent(content);
		boolean resDeleteImage = imageDelete(content.getContent_id(), res, req);
		boolean resInsertImage = imageUpload(content, res, req);
		if(resContent && resDeleteImage && resInsertImage){
			msg.put("resmsg", "게시물 수정 성공");
		}else{
			msg.put("resmsg", "게시물 수정	 실패");
		}
		resEntity = new ResponseEntity<Map<String,Object>>(msg, HttpStatus.OK);
		return resEntity;
	}

	@PostMapping("/findByLocation")
	@ApiOperation(value = "게시물 위치정보 기반 검색")
	private @ResponseBody ResponseEntity<Map<String, Object>> findByLocation(@RequestBody LocationVo location){
		ResponseEntity<Map<String, Object>> resEntity = null;
		Map<String, Object> msg = new HashMap<String, Object>();
		List<ContentVo> contentList = ser.findContentByLocation(location);
		if(contentList != null && contentList.size() > 0){
			msg.put("resmsg", "게시물 검색 성공");
			msg.put("resValue", contentList);
		}else{
			msg.put("resmsg", "게시물 검색 실패");
		}
		resEntity = new ResponseEntity<Map<String,Object>>(msg, HttpStatus.OK);
		return resEntity;
	}
}
