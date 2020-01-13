package edu.ssafy.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import edu.ssafy.boot.dto.UserVo;
import edu.ssafy.boot.repository.IUserDAO;

@Service("UserService")
public class UserService implements IUserService {

	@Autowired
	@Qualifier("UserDAOImpl")
	IUserDAO dao;

	@Override
	public boolean login(UserVo user) {
		return dao.login(user);
	}

	@Override
	public boolean signup(UserVo user) {
		List<String> interestList = user.getInterestList();
		List<String> dislikeList = user.getDislikeList();

		String interest = "";
		String dislike = "";
		
		if(interestList != null){
			for (String in : interestList) {
				interest += in+" ";
			}
		}
		if(dislikeList != null){
			for (String dis : dislikeList) {
				dislike += dis+" ";
			}
		}

		user.setInterest(interest.trim());
		user.setDislike(dislike.trim());

		return dao.signup(user);
	}

	@Override
	public boolean updateUserInfo(UserVo user) {
		List<String> interestList = user.getInterestList();
		List<String> dislikeList = user.getDislikeList();

		String interest = "";
		String dislike = "";
		
		if(interestList != null){
			for (String in : interestList) {
				interest += in+" ";
			}
		}
		if(dislikeList != null){
			for (String dis : dislikeList) {
				dislike += dis+" ";
			}
		}

		user.setInterest(interest.trim());
		user.setDislike(dislike.trim());

		return dao.updateUserInfo(user);
	}

	@Override
	public boolean deleteUserInfo(String user_id) {
		return dao.deleteUserInfo(user_id);
	}

	@Override
	public UserVo info(String user_id) {
		return dao.info(user_id);
	}
}
