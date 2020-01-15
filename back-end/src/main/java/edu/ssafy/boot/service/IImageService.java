package edu.ssafy.boot.service;

import java.util.List;

import edu.ssafy.boot.dto.ImageVo;

public interface IImageService {
    public boolean insertImage(ImageVo image);
    public boolean tmpInsertImage(ImageVo image);
    public boolean cancelImage(String user_id);
    public boolean deleteImage(int content_id);
    public List<ImageVo> imageList(int content_id);
}
