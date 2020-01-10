package edu.ssafy.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ssafy.boot.service.IImageService;

@CrossOrigin(origins="*")
@RestController()
@RequestMapping("/api/image")
public class ImageController {

	@Autowired
	@Qualifier("ImageService")
	IImageService ser;
	
}
