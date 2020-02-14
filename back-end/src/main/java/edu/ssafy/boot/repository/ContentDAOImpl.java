package edu.ssafy.boot.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.ssafy.boot.dto.ContentVo;
import edu.ssafy.boot.dto.ImageVo;
import edu.ssafy.boot.dto.LocationVo;
import edu.ssafy.boot.dto.UserVo;

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
			UserVo user = session.selectOne("ssafy.user.info", contentVo.getUser_id());
			if(user.getProfile_url() != null && user.getProfile_filter() != null){
				contentVo.setProfile_url(user.getProfile_url());
				contentVo.setProfile_filter(user.getProfile_filter());
			}
		}
		return contentList;
	}

	@Override
	public ContentVo detail(int content_id) {
		ContentVo content = session.selectOne("ssafy.content.selectOne", content_id);
		List<ImageVo> imageList = session.selectList("ssafy.image.imageList", content_id);
		content.setImageList(imageList);
		UserVo user = session.selectOne("ssafy.user.info", content.getUser_id());
			if(user.getProfile_url() != null && user.getProfile_filter() != null){
				content.setProfile_url(user.getProfile_url());
				content.setProfile_filter(user.getProfile_filter());
			}
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
			UserVo user = session.selectOne("ssafy.user.info", contentVo.getUser_id());
			if(user.getProfile_url() != null && user.getProfile_filter() != null){
				contentVo.setProfile_url(user.getProfile_url());
				contentVo.setProfile_filter(user.getProfile_filter());
			}
		}
		return contentList;
	}

	@Override
	public List<ContentVo> contentUserList(String user_id) {
		List<ContentVo> contentList = session.selectList("ssafy.content.contentUserList", user_id);
		for (ContentVo contentVo : contentList) {
			List<ImageVo> imageList = session.selectList("ssafy.content.imageListByContentId", contentVo.getContent_id());
			contentVo.setImageList(imageList);
			UserVo user = session.selectOne("ssafy.user.info", contentVo.getUser_id());
			if(user.getProfile_url() != null && user.getProfile_filter() != null){
				contentVo.setProfile_url(user.getProfile_url());
				contentVo.setProfile_filter(user.getProfile_filter());
			}
		}
		return contentList;
	}

	@Override
	public List<String> deleteReportedContents() {
		List<Integer> contentIdList = session.selectList("ssafy.content.ReportedContentId");
		List<String> imageNames = new ArrayList<String>();
		for (Integer content_id : contentIdList) {
			session.delete("ssafy.userLike.deleteReported", content_id);
			session.delete("ssafy.scrap.deleteReported", content_id);
			session.delete("ssafy.userReport.deleteReported", content_id);
			session.delete("ssafy.notification.deleteReported", content_id);
			List<String> imageName = session.selectList("ssafy.image.selectImageNames", content_id);
			for (String name : imageName) {
				imageNames.add(name);
			}
			session.delete("ssafy.image.deleteReported", content_id);
			session.delete("ssafy.content.delete", content_id);
		}
		return imageNames;
	}

	@Override
	public List<ContentVo> contentListHashtag(String tag) {
		List<ContentVo> contentList = session.selectList("ssafy.content.contentListHashtag", tag);
		for (ContentVo contentVo : contentList) {
			List<ImageVo> imageList = session.selectList("ssafy.content.imageListByContentId", contentVo.getContent_id());
			contentVo.setImageList(imageList);
			UserVo user = session.selectOne("ssafy.user.info", contentVo.getUser_id());
			if(user.getProfile_url() != null && user.getProfile_filter() != null){
				contentVo.setProfile_url(user.getProfile_url());
				contentVo.setProfile_filter(user.getProfile_filter());
			}
		}
		return contentList;
	}

	@Override
	public List<ContentVo> contentListHashtagList(List<String> tagList) {
		List<ContentVo> contentList = new ArrayList<ContentVo>();
		for (String tag : tagList) {
			List<ContentVo> list = session.selectList("ssafy.content.contentListHashtag", tag);
			for (ContentVo content : list) {
				boolean isContain = false;
				for (ContentVo contentVo : contentList) {
					if(contentVo.getContent_id() == content.getContent_id()){
						isContain = true;
						break;
					}
				}
				if(!isContain){
					contentList.add(content);
				}
			}
		}
		for (ContentVo contentVo : contentList) {
			List<ImageVo> imageList = session.selectList("ssafy.content.imageListByContentId", contentVo.getContent_id());
			contentVo.setImageList(imageList);
			UserVo user = session.selectOne("ssafy.user.info", contentVo.getUser_id());
			if(user.getProfile_url() != null && user.getProfile_filter() != null){
				contentVo.setProfile_url(user.getProfile_url());
				contentVo.setProfile_filter(user.getProfile_filter());
			}
		}
		return contentList;
	}
}
