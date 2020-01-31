package edu.ssafy.boot.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.ssafy.boot.dto.NotificationVo;

@Repository("NotificationDAOImpl")
public class NotificationDAOImpl implements INotificationDAO {

    @Autowired
    SqlSession session;

    @Override
    public boolean insertNotification(NotificationVo notification) {
        int insert = session.insert("ssafy.notification.insert", notification);
        if(insert > 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<NotificationVo> selectList(String user_id) {
        List<NotificationVo> notificationList = session.selectList("ssafy.notification.selectList", user_id);
        // System.out.println("123");
        return notificationList;
    }

    @Override
    public List<NotificationVo> selectUncheckedList(String user_id) {
        List<NotificationVo> notificationList = session.selectList("ssafy.notification.selectUnchecked", user_id);
        return notificationList;
    }

    @Override
    public List<NotificationVo> selectCategory(String user_id, String category) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("user_id", user_id);
        map.put("category", category);
        List<NotificationVo> notificationList = session.selectList("ssafy.notification.selectCategory", map);
        return notificationList;
    }

    @Override
    public List<NotificationVo> selectUncheckedCategory(String user_id, String category) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("user_id", user_id);
        map.put("category", category);
        List<NotificationVo> notificationList = session.selectList("ssafy.notification.selectUncheckedCategory", map);
        return notificationList;
    }

    @Override
    public boolean updateCheck(int notification_id) {
        int update = session.update("ssafy.notification.updateCheck", notification_id);
        if(update > 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean deleteLike(String user_id, int content_id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("user_id", user_id);
        map.put("content_id", content_id);
        int delete = session.delete("ssafy.notification.deleteLike", map);
        if(delete > 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean deleteScrap(String user_id, int content_id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("user_id", user_id);
        map.put("content_id", content_id);
        int delete = session.delete("ssafy.notification.deleteScrap", map);
        if(delete > 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean deleteFollow(String follower_id, String follow_id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("follower_id", follower_id);
        map.put("follow_id", follow_id);
        int delete = session.delete("ssafy.notification.deleteFollow", map);
        if(delete > 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean deleteComment(String user_id, String target_id, int comment_id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("user_id", user_id);
        map.put("target_id", target_id);
        map.put("comment_id", comment_id);
        int delete = session.delete("ssafy.notification.deleteComment", map);
        if(delete > 0){
            return true;
        }else{
            return false;
        }
    }

    

}