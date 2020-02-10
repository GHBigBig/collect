package com.zjg.collect.service;

import com.zjg.collect.dao.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zjg
 * @create 2020-02-01 20:54
 */
public interface ImageService {
    int addImage(Image image);
    List<Image> getAllImage();
}
