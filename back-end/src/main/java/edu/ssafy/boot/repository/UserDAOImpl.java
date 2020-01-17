package edu.ssafy.boot.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.ssafy.boot.dto.UserVo;

@Repository("UserDAOImpl")
public class UserDAOImpl implements IUserDAO {

	@Autowired
	SqlSession session;

	@Override
	public boolean login(UserVo user) {
		UserVo result = session.selectOne("ssafy.user.login", user);
		if (result == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean signup(UserVo user) {
		int insert = session.insert("ssafy.user.signup", user);
		if (insert > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updateUserInfo(UserVo user) {
		int update = session.update("ssafy.user.updateUserInfo", user);
		if (update > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteUserInfo(String user_id) {
		int delete = session.delete("ssafy.user.deleteUserInfo", user_id);
		if (delete > 0) {
			return true;
		} else {
			return false;	
		}
	}

	@Override
	public UserVo info(String user_id) {
		return session.selectOne("ssafy.user.info", user_id);
	}

	@Override
	public boolean updateTempPassword(String email, String password) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("password", password);
		map.put("email", email);
		int update = session.update("ssafy.user.updateTempPw", map);
		
		if(update > 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public void updateUserStatus() {
		session.update("ssafy.user.updateUserStatus");
	}

	@Override
	public boolean emailDuplicateCheck(String email) {
		boolean isOk = session.selectOne("ssafy.user.emailDuplicateCheck", email);
		System.out.println(isOk);
		return isOk;
	}
}
