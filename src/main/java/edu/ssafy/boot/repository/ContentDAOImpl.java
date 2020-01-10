package edu.ssafy.boot.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.ssafy.boot.dto.ContentVo;

@Repository("ContentDAOImpl")
public class ContentDAOImpl implements IContentDAO {

	@Autowired
	SqlSession session;
	
	@Override
	public List<ContentVo> contentMyList(String user_id) {
		// TODO Auto-generated method stub
		return session.selectList("ssafy.content.selectMyList", user_id);
	}

	@Override
	public ContentVo detail(int content_id) {
		ContentVo content = session.selectOne("ssafy.content.selectOne", content_id);
		return content;
	}

	@Override
	public List<String> detailUrls(int content_id) {
		List<String> urls = session.selectList("ssafy.image.selectList", content_id);
		return urls;
	}

}
