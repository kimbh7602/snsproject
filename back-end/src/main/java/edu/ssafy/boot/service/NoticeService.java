package edu.ssafy.boot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import edu.ssafy.boot.dto.End;
import edu.ssafy.boot.dto.NoticeDBVo;
import edu.ssafy.boot.dto.NoticeVo;
import edu.ssafy.boot.dto.Start;
import edu.ssafy.boot.repository.INoticeDAO;

@Service("NoticeService")
public class NoticeService implements INoticeService {

    @Autowired
    @Qualifier("NoticeDAOImpl")
    INoticeDAO dao;

    @Override
    public boolean insertNotice(NoticeVo notice) {
        NoticeDBVo noticedb = new NoticeDBVo(notice.getCalendarId(), notice.getCategory(), notice.getId(), notice.getTitle()
        , notice.getLocation(), notice.isAllDay(), notice.getStart().get_date(), notice.getEnd().get_date());
        return dao.insertNotice(noticedb);
    }

    @Override
    public boolean updateNotice(NoticeVo notice) {
        NoticeDBVo noticedb = new NoticeDBVo(notice.getCalendarId(), notice.getCategory(), notice.getId(), notice.getTitle()
        , notice.getLocation(), notice.isAllDay(), notice.getStart().get_date(), notice.getEnd().get_date());
        return dao.updateNotice(noticedb);
    }

    @Override
    public boolean deleteNotice(String id) {
        return dao.deleteNotice(id);
    }

    @Override
    public List<NoticeVo> selectNoticeList() {
        List<NoticeDBVo> noticeDBList = dao.selectNoticeList();
        List<NoticeVo> noticeList = new ArrayList<NoticeVo>();
        for (NoticeDBVo notice : noticeDBList) {
            noticeList.add(new NoticeVo(notice.getCalendarId(), notice.getCategory(), notice.getId(), notice.getTitle()
            , notice.getLocation(), notice.isAllDay(), new Start(notice.getStart()), new End(notice.getEnd())));
        }
        return noticeList;
    }

    @Override
    public List<NoticeVo> selectNoticeNow() {
        List<NoticeDBVo> noticeDBList = dao.selectNoticeList();
        List<NoticeVo> noticeList = new ArrayList<NoticeVo>();
        for (NoticeDBVo notice : noticeDBList) {
            noticeList.add(new NoticeVo(notice.getCalendarId(), notice.getCategory(), notice.getId(), notice.getTitle()
            , notice.getLocation(), notice.isAllDay(), new Start(notice.getStart()), new End(notice.getEnd())));
        }
        return noticeList;
    }
    
}