package edu.ssafy.boot.repository;

import edu.ssafy.boot.dto.ImageVo;

public interface IImageDAO {
    public boolean insertImage(ImageVo image);
    public boolean tmpInsertImage(ImageVo image);
    public boolean cancelImage(String user_id);
}
