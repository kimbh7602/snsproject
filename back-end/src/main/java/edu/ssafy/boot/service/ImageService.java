package edu.ssafy.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import edu.ssafy.boot.repository.IImageDAO;

@Service("ImageService")
public class ImageService implements IImageService {
	
	@Autowired
	@Qualifier("ImageDAOImpl")
	IImageDAO dao;
}
