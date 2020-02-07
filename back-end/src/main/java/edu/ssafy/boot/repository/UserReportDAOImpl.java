package edu.ssafy.boot.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.ssafy.boot.dto.ContentVo;
import edu.ssafy.boot.dto.ImageVo;
import edu.ssafy.boot.dto.UserReportVo;
import edu.ssafy.boot.dto.UserVo;

@Repository("UserReportDAOImpl")
public class UserReportDAOImpl implements IUserReportDAO {

    @Autowired
    SqlSession session;

    @Override
    public boolean insertReport(UserReportVo report) {
        int insert = session.insert("ssafy.userReport.insertReport", report);
        int update = session.update("ssafy.content.insertReport", report.getContent_id());
        if(insert > 0 && update > 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean deleteReport(UserReportVo report) {
        int delete = session.delete("ssafy.userReport.deleteReport", report);
        int update = session.update("ssafy.content.deleteReport", report.getContent_id());
        if(delete > 0 && update > 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<UserReportVo> selectMyReport(String user_id) {
        List<UserReportVo> reportList = session.selectList("ssafy.userReport.selectMyReport", user_id);
        return reportList;
    }

    @Override
    public List<ContentVo> reportContent(String user_id) {
        List<ContentVo> contentList = session.selectList("ssafy.userReport.reportContents", user_id);
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