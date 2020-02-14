package edu.ssafy.boot.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import edu.ssafy.boot.dto.ContentVo;
import edu.ssafy.boot.dto.UserReportVo;
import edu.ssafy.boot.repository.IUserReportDAO;

@Service("UserReportService")
public class UserReportService implements IUserReportService {

    @Autowired
    @Qualifier("UserReportDAOImpl")
    IUserReportDAO dao;

    @Override
    public boolean insertReport(UserReportVo report) {
        return dao.insertReport(report);
    }

    @Override
    public boolean deleteReport(UserReportVo report) {
        return dao.deleteReport(report);
    }

    @Override
    public List<UserReportVo> selectMyReport(String user_id) {
        return dao.selectMyReport(user_id);
    }

    @Override
    public List<ContentVo> reportContent(String user_id) {
        return dao.reportContent(user_id);
    }

    @Override
	public List<ContentVo> adminreportContentList() {
		List<ContentVo> contentList = dao.adminreportContentList();

		contentList.sort(new Comparator<ContentVo>() {

			@Override
			public int compare(ContentVo o1, ContentVo o2) {
				return o2.getTimestamp().compareTo(o1.getTimestamp());
			}
			
		});
		return contentList;
	}
    
    @Override
    public List<String> getreportcategory(String content_id) {
        return dao.getreportcategory(content_id);
    }
    @Override
    public int getreportvalue(String content_id, String key) {
        return dao.getreportvalue(content_id, key);
    }
    
    @Override
    public List<String> getreportexval(String content_id) {
        return dao.getreportexval(content_id);
    }
}