package edu.ssafy.boot.repository;

import java.util.List;

import edu.ssafy.boot.dto.ContentVo;
import edu.ssafy.boot.dto.LocationVo;

public interface IContentDAO {
	public List<ContentVo> contentMyList(String user_id);
	public ContentVo detail(int content_id);
	public List<String> detailUrls(int content_id);
	public boolean insertContent(ContentVo content);
	public int selectContentId(String user_id);
	public boolean deleteContent(int content_id);
	public boolean updateContent(ContentVo content);
	public List<ContentVo> findContentByLocation(LocationVo location);
	public List<ContentVo> contentUserList(String user_id);
	public List<String> deleteReportedContents();
	public List<ContentVo> contentListHashtag(String tag);
	public List<ContentVo> contentListHashtagList(List<String> tagList);
}
