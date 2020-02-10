package com.zjg.collect.service.impl;

import com.zjg.collect.dao.mapper.RecordMapper;
import com.zjg.collect.dao.model.Record;
import com.zjg.collect.service.RecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zjg
 * @create 2020-02-03 20:18
 */
@Service
public class RecordServiceImpl implements RecordService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RecordServiceImpl.class);
    @Autowired
    private RecordMapper recordMapper;

    @Override
    public int addRecord(Record record) {
        LOGGER.debug("###添加 record ： {}###", record);
        int insert = recordMapper.insert(record);
        LOGGER.debug("###添加操作影响 {} 行记录###", insert);
        return insert;
    }
}
