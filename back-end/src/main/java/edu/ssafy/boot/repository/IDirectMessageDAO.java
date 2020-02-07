package edu.ssafy.boot.repository;

import java.util.List;

import edu.ssafy.boot.dto.DirectMessageVo;
import edu.ssafy.boot.dto.UserDmVo;

public interface IDirectMessageDAO {
    public boolean insertDirectMessage(DirectMessageVo directMessage);
    public boolean deleteDirectMessage(DirectMessageVo directMessage);
    public List<DirectMessageVo> directMessageList(UserDmVo userDm);
    public boolean directMessageReadCheck(UserDmVo userDm);
    public int directMessageUnReadCnt(int dm_id);
    public void deleteOldMessage();
}