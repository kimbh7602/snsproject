package edu.ssafy.boot.service;

import java.util.List;

import edu.ssafy.boot.dto.FollowVo;

public interface IFollowService {
    public boolean follow(FollowVo follow);
    public boolean unfollow(FollowVo follow);
    public List<String> followList(String follower_id);
    public List<String> followerList(String follow_id);
    public List<String> followfollowerList(String user_id);
}
