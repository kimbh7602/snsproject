package edu.ssafy.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import edu.ssafy.boot.dto.NotificationVo;
import edu.ssafy.boot.repository.INotificationDAO;

@Service("NotificationService")
public class NotificationService implements INotificationService {

    @Autowired
    @Qualifier("NotificationDAOImpl")
    INotificationDAO dao;

    @Override
    public boolean insertNotification(NotificationVo notification) {
        return dao.insertNotification(notification);
    }

    @Override
    public List<NotificationVo> selectList(String user_id) {
        return dao.selectList(user_id);
    }

    @Override
    public List<NotificationVo> selectUncheckedList(String user_id) {
        return dao.selectUncheckedList(user_id);
    }

    @Override
    public List<NotificationVo> selectCategory(String user_id, String category) {
        return dao.selectCategory(user_id, category);
    }

    @Override
    public List<NotificationVo> selectUncheckedCategory(String user_id, String category) {
        return dao.selectUncheckedCategory(user_id, category);
    }

    @Override
    public boolean updateCheck(int notification_id) {
        return dao.updateCheck(notification_id);
    }

    @Override
    public boolean deleteLike(String user_id, int content_id) {
        return dao.deleteLike(user_id, content_id);
    }

    @Override
    public boolean deleteScrap(String user_id, int content_id) {
        return dao.deleteScrap(user_id, content_id);
    }

    @Override
    public boolean deleteFollow(String follower_id, String follow_id) {
        return dao.deleteFollow(follower_id, follow_id);
    }

    @Override
    public boolean deleteComment(String user_id, String target_id, int comment_id) {
        return dao.deleteComment(user_id, target_id, comment_id);
    }

    @Override
    public int countUnchecked(String user_id) {
        return dao.countUnchecked(user_id);
    }

}