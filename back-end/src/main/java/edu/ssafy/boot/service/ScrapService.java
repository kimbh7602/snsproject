package edu.ssafy.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import edu.ssafy.boot.dto.ContentVo;
import edu.ssafy.boot.dto.ScrapVo;
import edu.ssafy.boot.repository.IScrapDAO;

@Service("ScrapService")
public class ScrapService implements IScrapService {

	@Autowired
	@Qualifier("ScrapDAOImpl")
	IScrapDAO dao;

	@Override
	public boolean insertScrap(ScrapVo scrap) {
		return dao.insertScrap(scrap);
	}

	@Override
	public boolean deleteScrap(ScrapVo scrap) {
		return dao.deleteScrap(scrap);
	}

	@Override
	public List<ContentVo> scrapList(String user_id) {
		return dao.scrapList(user_id);
	}
}
