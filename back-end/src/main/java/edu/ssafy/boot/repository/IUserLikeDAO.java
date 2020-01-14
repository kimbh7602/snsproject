package edu.ssafy.boot.repository;

import java.util.List;

import edu.ssafy.boot.dto.ContentVo;
import edu.ssafy.boot.dto.UserLikeVo;

public interface IUserLikeDAO {
    public boolean userLike(UserLikeVo userLike);
    public boolean userDislike(UserLikeVo userLike);
    public List<ContentVo> userLikeList(String user_id);
}