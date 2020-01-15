package edu.ssafy.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import edu.ssafy.boot.dto.ImageVo;
import edu.ssafy.boot.repository.IImageDAO;

@Service("ImageService")
public class ImageService implements IImageService {

	@Autowired
	@Qualifier("ImageDAOImpl")
	IImageDAO dao;

	@Override
	public boolean insertImage(ImageVo image) {
		return dao.insertImage(image);
	}

	@Override
	public boolean tmpInsertImage(ImageVo image) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean cancelImage(String user_id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteImage(int content_id) {
		return dao.deleteImage(content_id);
	}

	@Override
	public List<ImageVo> imageList(int content_id) {
		return dao.imageList(content_id);
	}
}
