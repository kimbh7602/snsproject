package edu.ssafy.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import edu.ssafy.boot.dto.ContentVo;
import edu.ssafy.boot.dto.UserLikeVo;
import edu.ssafy.boot.repository.IUserLikeDAO;

@Service("UserLikeService")
public class UserLikeService implements IUserLikeService {

    @Autowired
    @Qualifier("UserLikeDAOImpl")
    IUserLikeDAO dao;

    @Override
    public boolean userLike(UserLikeVo userLike) {
        return dao.userLike(userLike);
    }

    @Override
    public boolean userDislike(UserLikeVo userLike) {
        return dao.userDislike(userLike);
    }

    @Override
    public List<ContentVo> userLikeList(String user_id) {
        return dao.userLikeList(user_id);
    }
}