package edu.ssafy.boot.service;

import java.util.List;

import edu.ssafy.boot.dto.NoticeVo;

public interface INoticeService {
    public boolean insertNotice(NoticeVo notice);
    public boolean updateNotice(NoticeVo notice);
    public boolean deleteNotice(String id);
    public List<NoticeVo> selectNoticeList();
    public List<NoticeVo> selectNoticeNow();
}