package edu.ssafy.boot.repository;

import java.util.List;

import edu.ssafy.boot.dto.ImageVo;

public interface IImageDAO {
    public boolean insertImage(ImageVo image);
    public boolean tmpInsertImage(ImageVo image);
    public boolean cancelImage(String user_id);
    public boolean deleteImage(int content_id);
    public List<ImageVo> imageList(int content_id);
}
