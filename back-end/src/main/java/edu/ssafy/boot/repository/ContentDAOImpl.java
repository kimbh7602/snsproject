package edu.ssafy.boot.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.ssafy.boot.dto.ContentVo;
import edu.ssafy.boot.dto.ImageVo;
import edu.ssafy.boot.dto.LocationVo;

@Repository("ContentDAOImpl")
public class ContentDAOImpl implements IContentDAO {

	@Autowired
	SqlSession session;

	@Override
	public List<ContentVo> contentMyList(String user_id) {
		List<ContentVo> contentList = session.selectList("ssafy.content.selectMyFollowList", user_id);
		List<ContentVo> myList = session.selectList("ssafy.content.selectMyList", user_id);
		for (ContentVo contentVo : myList) {
			contentList.add(contentVo);
		}
		for (ContentVo contentVo : contentList) {
			List<ImageVo> imageList = session.selectList("ssafy.content.imageListByContentId", contentVo.getContent_id());
			contentVo.setImageList(imageList);
		}
		return contentList;
	}

	@Override
	public ContentVo detail(int content_id) {
		ContentVo content = session.selectOne("ssafy.content.selectOne", content_id);
		List<ImageVo> imageList = session.selectList("ssafy.image.imageList", content_id);
		content.setImageList(imageList);
		return content;
	}

	@Override
	public List<String> detailUrls(int content_id) {
		List<String> urls = session.selectList("ssafy.image.selectList", content_id);
		return urls;
	}

	@Override
	public boolean insertContent(ContentVo content) {
		// List<ImageVo> imageList = content.getImageList();
		int contentInsert = session.insert("ssafy.content.insert", content);

		// boolean imageFlag = true;
		// for (ImageVo imageVo : imageList) {
		// int imageInsert = session.insert("ssafy.image.insert", imageVo);
		// if(imageInsert <= 0){
		// imageFlag = false;
		// break;
		// }
		// }
		if (contentInsert > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int selectContentId(String user_id) {
		int content_id = session.selectOne("ssafy.content.selectContentId", user_id);
		return content_id;
	}

	@Override
	public boolean deleteContent(int content_id) {
		int delete = session.delete("ssafy.content.delete", content_id);
		if (delete > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updateContent(ContentVo content) {
		int update = session.update("ssafy.content.update", content);
		if (update > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<ContentVo> findContentByLocation(LocationVo location) {
		List<ContentVo> contentList = session.selectList("ssafy.content.findByLocation", location);
		for (ContentVo contentVo : contentList) {
			List<ImageVo> imageList = session.selectList("ssafy.content.imageListByContentId", contentVo.getContent_id());
			contentVo.setImageList(imageList);
		}
		return contentList;
	}

	@Override
	public List<ContentVo> contentUserList(String user_id) {
		List<ContentVo> contentList = session.selectList("ssafy.content.contentUserList", user_id);
		for (ContentVo contentVo : contentList) {
			List<ImageVo> imageList = session.selectList("ssafy.content.imageListByContentId", contentVo.getContent_id());
			contentVo.setImageList(imageList);
		}
		return contentList;
	}

}
