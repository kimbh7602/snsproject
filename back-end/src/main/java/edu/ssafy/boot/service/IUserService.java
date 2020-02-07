package edu.ssafy.boot.service;

import java.util.List;

import edu.ssafy.boot.dto.UserVo;
import edu.ssafy.boot.dto.WordCloudVo;

public interface IUserService {
	public UserVo login(UserVo user);
	public boolean signup(UserVo user);
	public boolean updateUserInfo(UserVo user);
	public boolean deleteUserInfo(String user_id);
	public UserVo info(String user_id);
	public void updateUserStatus();
	public boolean emailDuplicateCheck(String email);
	public List<UserVo> searchByUserId(String keyword);
	public List<UserVo> searchByInterest(List<String> list);
	public List<UserVo> userList();
	public List<WordCloudVo> wordList();
	public String usertoday();
	public int totalcontents(int month);
}
