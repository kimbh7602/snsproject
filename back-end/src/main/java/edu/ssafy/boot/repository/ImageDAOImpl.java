package edu.ssafy.boot.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("ImageDAOImpl")
public class ImageDAOImpl implements IImageDAO {
	
	@Autowired
	SqlSession session;
}
