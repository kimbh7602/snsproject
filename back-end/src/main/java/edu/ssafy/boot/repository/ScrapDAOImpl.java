package edu.ssafy.boot.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.ssafy.boot.dto.ContentVo;
import edu.ssafy.boot.dto.ScrapVo;

@Repository("ScrapDAOImpl")
public class ScrapDAOImpl implements IScrapDAO {

	@Autowired
	SqlSession session;

	@Override
	public boolean insertScrap(ScrapVo scrap) {
		int insert = session.insert("ssafy.scrap.insert", scrap);
		if (insert > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteScrap(ScrapVo scrap) {
		int delete = session.delete("ssafy.scrap.delete", scrap);
		if (delete > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<ContentVo> scrapList(String user_id) {
		return session.selectList("ssafy.scrap.scrapList", user_id);
	}
}
