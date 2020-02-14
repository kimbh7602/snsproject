package edu.ssafy.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import edu.ssafy.boot.dto.DirectMessageVo;
import edu.ssafy.boot.dto.UserDmVo;
import edu.ssafy.boot.repository.IUserDmDAO;

@Service("UserDmService")
public class UserDmService implements IUserDmService {

    @Autowired
    @Qualifier("UserDmDAOImpl")
    IUserDmDAO dao;

    
    @Override
    public UserDmVo insertUserDm(UserDmVo userDm) {
        return dao.insertUserDm(userDm);
    }

    
    @Override
    public boolean deleteUserDm(int dm_id) {
        return dao.deleteUserDm(dm_id);
    }

    @Override
    public boolean updateUserDm(DirectMessageVo message) {
        return dao.updateUserDm(message);
    }

    
    @Override
    public List<UserDmVo> userDmList(String user_id) {
        return dao.userDmList(user_id);
    }

    @Override
    public List<UserDmVo> allDmList() {
        return dao.allDmList();
    }
}