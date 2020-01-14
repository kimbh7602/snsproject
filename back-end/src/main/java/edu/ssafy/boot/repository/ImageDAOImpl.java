package edu.ssafy.boot.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.ssafy.boot.dto.ImageVo;

@Repository("ImageDAOImpl")
public class ImageDAOImpl implements IImageDAO {

	@Autowired
	SqlSession session;

	@Override
	public boolean insertImage(ImageVo image) {
		int insert = session.insert("ssafy.image.insert", image);
		if(insert >0){
			return true;
		}else{
			return false;
		}
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
}
