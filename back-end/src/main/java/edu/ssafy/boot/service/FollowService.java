package edu.ssafy.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import edu.ssafy.boot.dto.FollowVo;
import edu.ssafy.boot.dto.UserVo;
import edu.ssafy.boot.repository.IFollowDAO;

@Service("FollowService")
public class FollowService implements IFollowService {

	@Autowired
	@Qualifier("FollowDAOImpl")
	IFollowDAO dao;

	@Override
	public boolean follow(FollowVo follow) {
		return dao.follow(follow);
	}

	@Override
	public boolean unfollow(FollowVo follow) {
		return dao.unfollow(follow);

	}

	@Override
	public List<UserVo> followList(String follower_id) {
		return dao.followList(follower_id);
	}

	@Override
	public List<UserVo> followerList(String follow_id) {
		return dao.followerList(follow_id);
	}

	@Override
	public List<UserVo> followfollowerList(String user_id) {
		return dao.followfollowerList(user_id);
	}
}
