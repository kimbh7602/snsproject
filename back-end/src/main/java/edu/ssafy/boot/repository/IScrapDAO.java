package edu.ssafy.boot.repository;

import java.util.List;

import edu.ssafy.boot.dto.ContentVo;
import edu.ssafy.boot.dto.ScrapVo;

public interface IScrapDAO {
    public boolean insertScrap(ScrapVo scrap);
    public boolean deleteScrap(ScrapVo scrap);
    public List<ContentVo> scrapList(String user_id);
}
