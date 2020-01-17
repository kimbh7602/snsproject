package edu.ssafy.boot.repository;

import java.util.List;

import edu.ssafy.boot.dto.NoticeDBVo;

public interface INoticeDAO {
    public boolean insertNotice(NoticeDBVo notice);
    public boolean updateNotice(NoticeDBVo notice);
    public boolean deleteNotice(String id);
    public List<NoticeDBVo> selectNoticeList();
    public List<NoticeDBVo> selectNoticeNow();
}
