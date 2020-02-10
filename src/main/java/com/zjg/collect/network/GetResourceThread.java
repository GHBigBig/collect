package com.zjg.collect.network;

import com.github.tobato.fastdfs.domain.fdfs.FileInfo;
import com.github.tobato.fastdfs.domain.fdfs.MetaData;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.zjg.collect.dao.model.Image;
import com.zjg.collect.service.ImageService;
import com.zjg.collect.util.FastDFSHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Callable;

/**
 * 多线程下载
 *
 * @author zjg
 * @create 2020-02-04 19:26
 */
public class GetResourceThread implements Callable<Image> {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetResourceThread.class);
    private String resourceName;
    private String domainName;
    @Autowired
    private ImageService imageService;
    @Autowired
    private FastDFSHelper fastDFSHelper;

    public GetResourceThread(String resourceName, String domainName) {
        Objects.requireNonNull(resourceName, "resourceName 不能为空");
        Objects.requireNonNull(domainName, "domainName 不能为空");
        this.resourceName = resourceName;
        this.domainName = domainName;
    }

    @Override
    public Image call() throws Exception {
        //获取文件的名字
        String[] split = resourceName.split("/");
        String imgName = split[split.length - 1];

        //生成临时文件
        File tempFile = new File(imgName);
        //防止文件已成存在
        if (tempFile.exists()) {
            tempFile.delete();
        }

        //下载文件
        URL url = new URL(resourceName);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();
        LOGGER.debug("###成功连接到 {} ###", url);
        //临时存储到本地
        try (
                BufferedInputStream bufferedInputStream = new BufferedInputStream(connection.getInputStream());
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(tempFile));
        ) {
            byte[] bytes = new byte[1024];
            int len;
            while ((len = bufferedInputStream.read(bytes)) != -1) {
                bufferedOutputStream.write(bytes, 0, len);
            }
            LOGGER.debug("###{} 文件下载成功###", tempFile.getName());
        }
        connection.disconnect();
        LOGGER.debug("###断开与 {} 的连接###", url);

        LOGGER.debug("###文件 {} 开始上传到 fastdfs 服务器###", tempFile.getName());
        //上传到 fastdfs
        StorePath storePath = fastDFSHelper.uploadImageAndCrtThumbImageByScale(tempFile, domainName);
        LOGGER.debug("###上传成功，StorePath: {} ###", storePath);
        //保存到数据库中
        FileInfo fileInfo = fastDFSHelper.getFileInfo(storePath.getGroup(), storePath.getPath());
        LOGGER.debug("###上传成功，FileInfo: {} ###", fileInfo);
        String thumbImagePath = fastDFSHelper.getThumbImagePath(storePath.getPath());
        Set<MetaData> metaDataSet = fastDFSHelper.getMetadata(storePath.getGroup(), storePath.getPath());

        com.zjg.collect.dao.model.Image image = new Image();
        image.setResourceUrl(resourceName);
        image.setGroupName(storePath.getGroup());
        image.setStorePath(storePath.getPath());
        image.setThumbnailStorePath(thumbImagePath);
        image.setImageSize(fileInfo.getFileSize());

        metaDataSet.forEach(metaData -> {
            switch (metaData.getName()) {
                case "SiteName":
                    image.setDomainName(metaData.getValue());
                    break;
                case "CreateDate":
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
                    LocalDateTime localDateTime = LocalDateTime.parse(metaData.getValue(), formatter);
                    Instant instant = localDateTime.toInstant(ZoneOffset.ofHours(8));
                    Date createDateTime = Date.from(instant);
                    image.setCreateDateTime(createDateTime);
                    break;
                default:
                    throw new RuntimeException("元数据错误，出现位置字段：" + metaData.getName() + "=" + metaData.getValue());
            }
        });
        LOGGER.debug("###image信息：{}###", image);

        imageService.addImage(image);


        System.out.println(tempFile.getAbsoluteFile() + " : " + tempFile.exists());
        //删除临时文件
        if (tempFile.exists()) {
            /*
                这里遇到了文件无法删除的情况，上网搜一下可能的原因有：
                    1、多线程操作；
                    2打开的文件流没有关闭；
                    3、需要垃圾回收下。
             */
            /*
                System.gc()函数的作用只是提醒或告诉虚拟机，希望进行一次垃圾回收。
                至于什么时候进行回收还是取决于虚拟机，
                而且也不能保证一定进行回收
                （如果-XX:+DisableExplicitGC设置成true，则不会进行回收）。
             */
            System.gc();
            tempFile.delete();
            LOGGER.debug("###删除文件 {} ###", tempFile.getName());
        }

        return image;
    }
}
