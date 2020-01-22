package edu.ssafy.boot.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.ssafy.boot.dto.UserDmVo;

@Repository("UserDmDAOImpl")
public class UserDmDAOImpl implements IUserDmDAO {

    @Autowired
    SqlSession session;

    @Override
    public boolean insertUserDm(UserDmVo userDm) {
        int insert = session.insert("ssafy.userDm.insert", userDm);
        if (insert > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteUserDm(int dm_id) {
        int delete = session.delete("ssafy.userDm.delete", dm_id);
        if (delete > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateUserDm(UserDmVo userDm) {
        int update = session.update("ssafy.userDm.update", userDm);
        if (update > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<UserDmVo> userDmList(String user_id) {
        return session.selectList("ssafy.userDm.userDmList", user_id);
    }
}