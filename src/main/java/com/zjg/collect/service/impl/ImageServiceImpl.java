package com.zjg.collect.service.impl;

import com.zjg.collect.dao.mapper.ImageMapper;
import com.zjg.collect.dao.model.Image;
import com.zjg.collect.dao.model.ImageExample;
import com.zjg.collect.service.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zjg
 * @create 2020-02-01 20:56
 */
@Service
public class ImageServiceImpl implements ImageService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageServiceImpl.class);

    @Autowired
    private ImageMapper imageMapper;

    @Override
    public int addImage(Image image) {
        LOGGER.debug("###添加 image ： {}###", image);
        int insert = imageMapper.insert(image);
        LOGGER.debug("###添加操作影响 {} 行记录###", insert);
        return insert;
    }

    @Override
    public List<Image> getAllImage() {
        LOGGER.debug("###获取所有 image 的记录###");
        ImageExample imageExample = new ImageExample();
        List<Image> imageList = imageMapper.selectByExample(imageExample);
        LOGGER.debug("###共 {} 条记录###", imageList.size());
        return imageList;
    }


}
