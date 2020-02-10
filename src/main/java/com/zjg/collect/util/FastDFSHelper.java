package com.zjg.collect.util;

import com.github.tobato.fastdfs.domain.fdfs.*;
import com.github.tobato.fastdfs.domain.upload.FastFile;
import com.github.tobato.fastdfs.domain.upload.FastImageFile;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.github.tobato.fastdfs.service.TrackerClient;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

/**
 * 对 FastDFS 的封装
 *
 * @author zjg
 * @create 2020-01-31 19:07
 */
@Component
public class FastDFSHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(FastDFSHelper.class);
    @Autowired
    private FastFileStorageClient storageClient;
    @Autowired
    private ThumbImageConfig thumbImageConfig;

    public StorePath uploadImageAndCrtThumbImageByScale(File file, String siteName) throws FileNotFoundException {
        LOGGER.debug("###为 {} 的 {} 生成 FastImageFile", siteName, file);
        FastImageFile fastImageFile = new FastImageFile.Builder()
                .withThumbImage()
                .withFile(new FileInputStream(file), file.length(), FilenameUtils.getExtension(file.getName()))
                .withMetaData(createMetadata(siteName))
                .build();
        LOGGER.debug("###{} 生成完成###", fastImageFile);
        return processUploadImageAndCrtThumbImage(fastImageFile);
    }

    private StorePath processUploadImageAndCrtThumbImage(FastImageFile fastImageFile){
        LOGGER.debug("##上传文件..##");
        StorePath path = storageClient.uploadImage(fastImageFile);
        LOGGER.debug("测试上传主文件路径{}", path);

        return path;
    }

    /**
     * 文件和元素的上传
     * @param file
     * @param siteName
     * @throws FileNotFoundException
     */
    public StorePath uploadFileAndMetaData(File file, String siteName) throws FileNotFoundException {
        FastFile fastFile = new FastFile.Builder()
                .withFile(new FileInputStream(file), file.length(), FilenameUtils.getExtension(file.getName()))
                .withMetaData(createMetadata(siteName))
                .build();

        return processUploadFileAndMetaData(fastFile);
    }

    /**
     * 获取缩略图得存储路径
     * @param storePath
     * @return
     */
    public String getThumbImagePath(String storePath) {
        LOGGER.debug("##获取 {} 得缩略图...#", storePath);
        String thumbImagePath = thumbImageConfig.getThumbImagePath(storePath);
        LOGGER.debug("缩略图路径：{}", thumbImagePath);
        return thumbImagePath;
    }

    /**
     * 进行文件和元数据的上传
     *
     * @return
     */
    private StorePath processUploadFileAndMetaData(FastFile fastFile) {
        LOGGER.debug("##上传文件..##");
        // 上传文件和Metadata
        StorePath path = storageClient.uploadFile(fastFile);
        LOGGER.debug("上传文件路径{}", path);
        return path;
    }

    /**
     * 获取 storePath 的元数据
     * @param storePath
     * @return
     */
    public Set<MetaData> getMetadata(String groupName, String storePath) {
        Set<MetaData> metadata = storageClient.getMetadata(groupName, storePath);
        return metadata;
    }

    /**
     * 创建 Matedata
     *
     * @param siteName
     * @return
     */
    private Set<MetaData> createMetadata(String siteName) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

        Set<MetaData> metaDataSet = new HashSet<>();
        metaDataSet.add(new MetaData("SiteName", siteName));
        metaDataSet.add(new MetaData("CreateDate", LocalDateTime.now().format(formatter)));
        return metaDataSet;
    }

    /**
     * 获取存储文件的信息
     *
     * @param groupName
     * @param storePath
     * @return
     */
    public FileInfo getFileInfo(String groupName, String storePath) {
        FileInfo fileInfo = storageClient.queryFileInfo(groupName, storePath);
        return fileInfo;
    }

}
