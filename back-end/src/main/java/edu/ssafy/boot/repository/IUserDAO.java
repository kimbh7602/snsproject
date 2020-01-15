package edu.ssafy.boot.repository;

import edu.ssafy.boot.dto.UserVo;

public interface IUserDAO {
	public boolean login(UserVo user);
	public boolean signup(UserVo user);
	public boolean updateUserInfo(UserVo user);
	public boolean deleteUserInfo(String user_id);
	public UserVo info(String user_id);
	public boolean updateTempPassword(String email, String tempPw);
	public void updateUserStatus();
	public boolean emailDuplicateCheck(String email);
}
