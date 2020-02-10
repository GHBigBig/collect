package com.zjg.collect.dao.mapper;

import com.zjg.collect.dao.model.Image;
import com.zjg.collect.dao.model.ImageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ImageMapper {
    int countByExample(ImageExample example);

    int deleteByExample(ImageExample example);

    int deleteByPrimaryKey(String resourceUrl);

    int insert(Image record);

    int insertSelective(Image record);

    List<Image> selectByExample(ImageExample example);

    Image selectByPrimaryKey(String resourceUrl);

    int updateByExampleSelective(@Param("record") Image record, @Param("example") ImageExample example);

    int updateByExample(@Param("record") Image record, @Param("example") ImageExample example);

    int updateByPrimaryKeySelective(Image record);

    int updateByPrimaryKey(Image record);
}