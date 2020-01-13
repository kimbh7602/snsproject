package edu.ssafy.boot.service;

import edu.ssafy.boot.dto.FollowVo;

public interface IFollowService {
    public boolean follow(FollowVo follow);
    public boolean unfollow(FollowVo follow);
}
