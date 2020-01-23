package edu.ssafy.boot.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import edu.ssafy.boot.dto.ImageVo;
import edu.ssafy.boot.service.IImageService;

@CrossOrigin(origins="*")
@RestController()
@RequestMapping("/api/image")
public class ImageController {

	@Autowired
	@Qualifier("ImageService")
	IImageService ser;
	
	@PostMapping("/image/fileupload/{user_id}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> handleFileUpload(HttpServletResponse res, HttpServletRequest req, @PathVariable("user_id") String user_id) {
    	String path = "/upload";
    	String realPath = req.getServletContext().getRealPath(path);
    	System.out.println(realPath);
		File f = new File(realPath);
		if (!f.exists()) {// 경로가 없다면 폴더를 만든다.
			f.mkdir();
		}
		ResponseEntity<Map<String,Object>> resEntity = null;
		Map<String, Object> map = new HashMap<String, Object>();
		MultipartHttpServletRequest mhsr = (MultipartHttpServletRequest) req;
		Iterator<String> fileNames = mhsr.getFileNames();
		if (!f.isDirectory()) {
			f.mkdir();
		}
		
		Map<String, Object> outData = new HashMap<String, Object>();
		try {
			int num = 1;
			while (fileNames.hasNext()) {
				String fileName = fileNames.next();
				System.out.println(fileName);
				MultipartFile file = mhsr.getFile(fileName);
				String oriName = new String(file.getOriginalFilename().getBytes("UTF-8"));
				String ext = oriName.substring(oriName.lastIndexOf(".")); //확장자 끊어서.
				String saveFileName = user_id+"-"+num+ext; //저장할 파일이름을 만드는데 뒤에 확장자도 붙임?
				File serverFile = new File(realPath+File.separator+saveFileName);
				file.transferTo(serverFile);
//				map.put("resmsg", "succ");
				map.put("uploaded", 1);
				map.put("url", req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + path + "/" + saveFileName);
				map.put("fileName", saveFileName);
//				JSONObject outData = new JSONObject();
				outData.put("uploaded", 1);
				outData.put("url", req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + path + "/" + saveFileName);
				outData.put("fileName", saveFileName);
				System.out.println(outData);
				System.out.println("!!!");
				num++;
			}
			resEntity = new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resEntity;
	}
	
	@PostMapping("/image/convetToImage")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> convertToImage(@RequestBody ImageVo image ,HttpServletResponse res, HttpServletRequest req) {
		ResponseEntity<Map<String,Object>> resEntity = null;

		FileOutputStream fos;
		byte[] decode = Base64.decodeBase64(image.getBase64());

    	String path = "/upload";
    	String realPath = req.getServletContext().getRealPath(path);
    	System.out.println(realPath);
		File f = new File(realPath+File.separator+"image.jpg");
		
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			f.createNewFile();
			fos = new FileOutputStream(f);
			fos.write(decode);
			fos.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Map<String, Object> outData = new HashMap<String, Object>();
		
		resEntity = new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		
		return resEntity;
	}
}
