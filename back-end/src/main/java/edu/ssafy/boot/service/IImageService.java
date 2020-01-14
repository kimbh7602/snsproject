package edu.ssafy.boot.service;

import edu.ssafy.boot.dto.ImageVo;

public interface IImageService {
    public boolean insertImage(ImageVo image);
    public boolean tmpInsertImage(ImageVo image);
    public boolean cancelImage(String user_id);
}
