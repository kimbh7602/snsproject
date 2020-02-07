package edu.ssafy.boot.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.ssafy.boot.dto.CommentVo;

@Repository("CommentDAOImpl")
public class CommentDAOImpl implements ICommentDAO {

	@Autowired
	SqlSession session;

	@Override
	public List<CommentVo> commentsList(String content_id) {
		return session.selectList("ssafy.comment.selectList", content_id);
	}

	@Override
	public boolean insertComment(CommentVo comment) {
		int insert = session.insert("ssafy.comment.insertComment", comment);

		if (insert > 0) {
			session.update("ssafy.comment.updateReComment", comment);
			return true;
		} else
			return false;
	}

	@Override
	public boolean insertReComment(CommentVo comment) {
		int insert = session.insert("ssafy.comment.insertReComment", comment);

		if (insert > 0) {
			return true;
		} else
			return false;
	}

	@Override
	public boolean updateComment(CommentVo comment) {
		int update = session.update("ssafy.comment.updateComment", comment);
		if (update > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteComment(int comment_id) {
		int delete = session.delete("ssafy.comment.deleteComment", comment_id);
		if (delete > 0) {
			return true;
		} else {
			return false;
		}
	}

}
