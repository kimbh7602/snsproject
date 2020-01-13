package edu.ssafy.boot.repository;

import edu.ssafy.boot.dto.FollowVo;

public interface IFollowDAO {
    public boolean follow(FollowVo follow);
    public boolean unfollow(FollowVo follow);
}
