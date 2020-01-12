package edu.ssafy.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import edu.ssafy.boot.dto.ContentVo;
import edu.ssafy.boot.repository.IContentDAO;

@Service("ContentService")
public class ContentService implements IContentService {
	
	@Autowired
	@Qualifier("ContentDAOImpl")
	IContentDAO dao;

	@Override
	public List<ContentVo> contentMyList(String user_id) {
		return dao.contentMyList(user_id);
	}

	@Override
	public ContentVo detail(int content_id) {
		return dao.detail(content_id);
	}

	@Override
	public List<String> detailUrls(int content_id) {
		return dao.detailUrls(content_id);
	}

	@Override
	public boolean insertContent(ContentVo content) {
		return dao.insertContent(content);
	}
	
}
