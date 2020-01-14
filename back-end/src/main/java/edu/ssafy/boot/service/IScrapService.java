package edu.ssafy.boot.service;

import java.util.List;

import edu.ssafy.boot.dto.ContentVo;
import edu.ssafy.boot.dto.ScrapVo;

public interface IScrapService {
    public boolean insertScrap(ScrapVo scrap);
    public boolean deleteScrap(ScrapVo scrap);
    public List<ContentVo> scrapList(String user_id);
}
