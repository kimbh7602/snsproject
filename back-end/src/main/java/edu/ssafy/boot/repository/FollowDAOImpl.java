package edu.ssafy.boot.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.ssafy.boot.dto.FollowVo;

@Repository("FollowDAOImpl")
public class FollowDAOImpl implements IFollowDAO {
	
	@Autowired
	SqlSession session;

	@Override
	public boolean follow(FollowVo follow) {
		int insert = session.insert("ssafy.follow.insert", follow);
		if (insert > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean unfollow(FollowVo follow) {
		int delete = session.delete("ssafy.follow.delete", follow);
		if (delete > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<String> followList(String follower_id) {
		return session.selectList("ssafy.follow.followList", follower_id);
	}

	@Override
	public List<String> followerList(String follow_id) {
		return session.selectList("ssafy.follow.followerList", follow_id);
	}

	@Override
	public List<String> followfollowerList(String user_id) {
		return session.selectList("ssafy.follow.followfollowerList", user_id);
	}
}
