package edu.ssafy.boot.repository;

import java.util.List;

import edu.ssafy.boot.dto.UserDmVo;

public interface IUserDmDAO {
    public boolean insertUserDm(UserDmVo userDm);
    public boolean deleteUserDm(int dm_id);
    public boolean updateUserDm(UserDmVo userDm);
    public List<UserDmVo> userDmList(String user_id);
}