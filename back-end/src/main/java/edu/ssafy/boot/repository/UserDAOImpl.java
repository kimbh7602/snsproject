package edu.ssafy.boot.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import edu.ssafy.boot.dto.UserVo;
import edu.ssafy.boot.service.ISecurityService;

@Repository("UserDAOImpl")
public class UserDAOImpl implements IUserDAO {

	@Autowired
	SqlSession session;

	@Autowired
	@Qualifier("SecurityService")
	ISecurityService sersc;
	
	
	@Override
	public UserVo login(UserVo user) {
		UserVo result = session.selectOne("ssafy.user.login", user);
		if(result != null){
			session.update("ssafy.user.loginTime", user.getUser_id());
		}
		return result;
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
		map.put("password", sersc.computePw(password));
		map.put("email", email);
		int update = session.update("ssafy.user.updateTempPw", map);

		if (update > 0) {
			return true;
		} else {
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

	@Override
	public List<UserVo> searchByUserId(String keyword) {
		List<UserVo> userList = session.selectList("ssafy.user.searchByUserId", keyword);
		return userList;
	}

	@Override
	public List<UserVo> searchByInterest(List<String> list) {
		List<UserVo> userList = session.selectList("ssafy.user.searchByInterest", list);
		return userList;
	}

	@Override
	public List<UserVo> userList() {
		List<UserVo> userList = session.selectList("ssafy.user.userList");
		return userList;
	}

	@Override
	public List<String> interestFrequency() {
		List<String> interestList = session.selectList("ssafy.user.interest");
		return interestList;
	}
	@Override
	public String usertoday() {
		
		return session.selectOne("ssafy.user.todayvisit");
	}
	
	@Override
	public int totalcontents(int month) {
		
		return session.selectOne("ssafy.user.totalcontents", month);
	}

	@Override
	public String myInterest(String user_id) {
		String myInterest = session.selectOne("ssafy.user.myInterest", user_id);
		return myInterest;
	}
	@Override
	public int moncontents(int currentdate) {
		if(session.selectOne("ssafy.user.moncontents", currentdate)==null) {
			return 0;
		}else {
			return session.selectOne("ssafy.user.moncontents", currentdate);
			
		}
	}
	
	@Override
	public int currentdate() {
		return session.selectOne("ssafy.user.currentdate");
	}
}
