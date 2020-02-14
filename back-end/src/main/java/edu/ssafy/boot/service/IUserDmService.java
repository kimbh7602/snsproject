package edu.ssafy.boot.service;

import java.util.List;

import edu.ssafy.boot.dto.DirectMessageVo;
import edu.ssafy.boot.dto.UserDmVo;

public interface IUserDmService {
    public UserDmVo insertUserDm(UserDmVo userDm);
    public boolean deleteUserDm(int dm_id);
    public boolean updateUserDm(DirectMessageVo message);
    public List<UserDmVo> userDmList(String user_id);
    public List<UserDmVo> allDmList();
}