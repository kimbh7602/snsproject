package edu.ssafy.boot.repository;

import edu.ssafy.boot.dto.UserLikeVo;

public interface IUserLikeDAO {
    public boolean userLike(UserLikeVo userLike);
    public boolean userDislike(UserLikeVo userLike);
}