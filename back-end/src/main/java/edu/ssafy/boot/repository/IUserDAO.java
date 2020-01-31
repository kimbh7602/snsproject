package edu.ssafy.boot.repository;

import java.util.List;

import edu.ssafy.boot.dto.UserVo;

public interface IUserDAO {
	public UserVo login(UserVo user);
	public boolean signup(UserVo user);
	public boolean updateUserInfo(UserVo user);
	public boolean deleteUserInfo(String user_id);
	public UserVo info(String user_id);
	public boolean updateTempPassword(String email, String tempPw);
	public void updateUserStatus();
	public boolean emailDuplicateCheck(String email);
	public List<UserVo> searchByUserId(String keyword);
	public List<UserVo> searchByInterest(List<String> list);
	public List<UserVo> userList();
	public List<String> interestFrequency();
}
