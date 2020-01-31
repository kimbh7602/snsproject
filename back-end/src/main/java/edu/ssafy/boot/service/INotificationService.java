package edu.ssafy.boot.service;

import java.util.List;

import edu.ssafy.boot.dto.NotificationVo;

public interface INotificationService {
    public boolean insertNotification(NotificationVo notification);
    public List<NotificationVo> selectList(String user_id);
    public List<NotificationVo> selectUncheckedList(String user_id);
    public List<NotificationVo> selectCategory(String user_id, String category);
    public List<NotificationVo> selectUncheckedCategory(String user_id, String category);
    public boolean updateCheck(int notification_id);
    public boolean deleteLike(String user_id, int content_id);
    public boolean deleteScrap(String user_id, int content_id);
    public boolean deleteFollow(String follower_id, String follow_id);
    public boolean deleteComment(String user_id, String target_id, int comment_id);
}