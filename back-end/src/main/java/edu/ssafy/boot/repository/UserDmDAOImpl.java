package edu.ssafy.boot.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.ssafy.boot.dto.DirectMessageVo;
import edu.ssafy.boot.dto.UserDmVo;
import edu.ssafy.boot.dto.UserVo;

@Repository("UserDmDAOImpl")
public class UserDmDAOImpl implements IUserDmDAO {

    @Autowired
    SqlSession session;

    @Override
    public UserDmVo insertUserDm(UserDmVo userDm) {
        int insert = session.insert("ssafy.userDm.insert", userDm);
        if (insert > 0) {
            UserDmVo resultDm = session.selectOne("ssafy.userDm.selectInsertDm", userDm);
            return resultDm;
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteUserDm(int dm_id) {
        int delete = session.delete("ssafy.userDm.delete", dm_id);
        if (delete > 0) {
            session.delete("ssafy.directMessage.deleteAll", dm_id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateUserDm(DirectMessageVo message) {
        int update = session.update("ssafy.userDm.update", message);
        if (update > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<UserDmVo> userDmList(String user_id) {
        List<UserDmVo> userDmList = session.selectList("ssafy.userDm.userDmList", user_id);
        for (UserDmVo userDmVo : userDmList) {
            UserVo user = null;
            if(userDmVo.getOther_id().equals(user_id)){
                user = session.selectOne("ssafy.user.info", userDmVo.getUser_id());
            }else{
                user = session.selectOne("ssafy.user.info", userDmVo.getOther_id());
            }
            userDmVo.setUser(user);
        }
        return userDmList;
    }

    @Override
    public List<UserDmVo> allDmList() {
        List<UserDmVo> dmList = session.selectList("ssafy.userDm.allDmList");
        return dmList;
    }
}