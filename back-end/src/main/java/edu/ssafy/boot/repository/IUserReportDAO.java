package edu.ssafy.boot.repository;

import java.util.List;

import edu.ssafy.boot.dto.ContentVo;
import edu.ssafy.boot.dto.UserReportVo;

public interface IUserReportDAO {
    public boolean insertReport(UserReportVo report);
    public boolean deleteReport(UserReportVo report);
    public List<UserReportVo> selectMyReport(String user_id);
    public List<ContentVo> reportContent(String user_id);
}