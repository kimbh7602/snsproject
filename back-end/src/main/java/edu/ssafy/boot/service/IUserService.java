package edu.ssafy.boot.service;

import edu.ssafy.boot.dto.UserVo;

public interface IUserService {
	public boolean login(UserVo user);
	public boolean signup(UserVo user);
	public boolean updateUserInfo(UserVo user);
	public boolean deleteUserInfo(String user_id);
	public UserVo info(String user_id);
}
