package edu.ssafy.boot.repository;

import java.util.List;

import edu.ssafy.boot.dto.CommentVo;

public interface ICommentDAO {

	public List<CommentVo> commentsList(String content_id);

	public boolean insertComment(CommentVo comment);

	public boolean updateComment(CommentVo comment);

	public boolean deleteComment(int comment_id);

	public boolean insertReComment(CommentVo comment);

}
