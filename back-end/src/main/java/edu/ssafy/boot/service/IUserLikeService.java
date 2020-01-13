package edu.ssafy.boot.service;

import edu.ssafy.boot.dto.UserLikeVo;

public interface IUserLikeService {
    public boolean userLike(UserLikeVo userLike);
    public boolean userDislike(UserLikeVo userLike);
}