package edu.ssafy.boot.repository;

import java.util.List;

import edu.ssafy.boot.dto.FollowVo;
import edu.ssafy.boot.dto.UserVo;

public interface IFollowDAO {
    public boolean follow(FollowVo follow);
    public boolean unfollow(FollowVo follow);
    public List<UserVo> followList(String follower_id);
    public List<UserVo> followerList(String follow_id);
    public List<UserVo> followfollowerList(String user_id);
}
