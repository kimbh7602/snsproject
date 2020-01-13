package edu.ssafy.boot.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.ssafy.boot.dto.UserLikeVo;

@Repository("UserLikeDAOImpl")
public class UserLikeDAOImpl implements IUserLikeDAO {

    @Autowired
    SqlSession session;

    @Override
    public boolean userLike(UserLikeVo userLike) {
        int insert = session.insert("ssafy.userLike.insert", userLike);
        if (insert > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean userDislike(UserLikeVo userLike) {
        int delete = session.delete("ssafy.userLike.delete", userLike);
        if (delete > 0) {
            return true;
        } else {
            return false;
        }
    }
}