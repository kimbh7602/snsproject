package edu.ssafy.boot.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.ssafy.boot.dto.NoticeDBVo;

@Repository("NoticeDAOImpl")
public class NoticeDAOImpl implements INoticeDAO {

    @Autowired
    SqlSession session;

    @Override
    public boolean insertNotice(NoticeDBVo notice) {
        int insert = session.insert("ssafy.notice.insert", notice);
        if(insert > 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean updateNotice(NoticeDBVo notice) {
        int update = session.update("ssafy.notice.update", notice);
        System.out.println(notice.toString());
        if(update > 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean deleteNotice(String id) {
        int delete = session.delete("ssafy.notice.delete", id);
        if(delete > 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<NoticeDBVo> selectNoticeList() {
        List<NoticeDBVo> noticeList = session.selectList("ssafy.notice.selectList");
        return noticeList;
    }

    @Override
    public List<NoticeDBVo> selectNoticeNow() {
        List<NoticeDBVo> noticeNow = session.selectList("ssafy.notice.selectNow");
        return noticeNow;
    }

}