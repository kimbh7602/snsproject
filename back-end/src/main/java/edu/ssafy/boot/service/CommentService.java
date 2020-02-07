package edu.ssafy.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import edu.ssafy.boot.dto.CommentVo;
import edu.ssafy.boot.repository.ICommentDAO;

@Service("CommentService")
public class CommentService implements ICommentService {

	@Autowired
	@Qualifier("CommentDAOImpl")
	ICommentDAO dao;

	@Override
	public List<CommentVo> commentsList(String content_id) {
		return dao.commentsList(content_id);
	}

	@Override
	public boolean insertContent(CommentVo comment) {
		return dao.insertComment(comment);
	}

	@Override
	public boolean updateComments(CommentVo comment) {
		return dao.updateComment(comment);
	}

	@Override
	public boolean deleteComments(int comment_id) {
		return dao.deleteComment(comment_id);
	}

	@Override
	public boolean insertReComment(CommentVo comment) {
		return dao.insertReComment(comment);
	}
}
