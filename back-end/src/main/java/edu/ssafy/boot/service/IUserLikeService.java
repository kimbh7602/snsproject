package edu.ssafy.boot.service;

import java.util.List;

import edu.ssafy.boot.dto.ContentVo;
import edu.ssafy.boot.dto.UserLikeVo;

public interface IUserLikeService {
    public boolean userLike(UserLikeVo userLike);
    public boolean userDislike(UserLikeVo userLike);
    public List<ContentVo> userLikeList(String user_id); 
}