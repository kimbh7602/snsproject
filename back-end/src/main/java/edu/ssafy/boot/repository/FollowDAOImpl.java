package edu.ssafy.boot.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.ssafy.boot.dto.FollowVo;

@Repository("FollowDAOImpl")
public class FollowDAOImpl implements IFollowDAO {
	
	@Autowired
	SqlSession session;

	@Override
	public boolean follow(FollowVo follow){
		int insert = session.insert("ssafy.follow.insert", follow);
		if (insert > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean unfollow(FollowVo follow){
		int delete = session.delete("ssafy.follow.delete", follow);
		if (delete > 0) {
			return true;
		} else {
			return false;
		}
	}
}
