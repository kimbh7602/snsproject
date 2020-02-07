package edu.ssafy.boot.service;

import java.util.List;

import edu.ssafy.boot.dto.CommentVo;

public interface ICommentService {


	public List<CommentVo> commentsList(String content_id);

	public boolean insertContent(CommentVo comment);

	public boolean updateComments(CommentVo comment);

	public boolean deleteComments(int comment_id);

	public boolean insertReComment(CommentVo comment);

}
