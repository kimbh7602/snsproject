package edu.ssafy.boot.service;

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
		return dao.signup(user);
	}

	@Override
	public boolean updateUserInfo(UserVo user) {
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
