package com.zjg.collect.network;

import com.github.tobato.fastdfs.domain.fdfs.FileInfo;
import com.github.tobato.fastdfs.domain.fdfs.MetaData;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.zjg.collect.dao.model.Image;
import com.zjg.collect.dao.model.Record;
import com.zjg.collect.service.ImageService;
import com.zjg.collect.service.RecordService;
import com.zjg.collect.util.FastDFSHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author zjg
 * @create 2020-01-19 21:47
 */
@Component
public class GetResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetResource.class);

    @Autowired
    private FastDFSHelper fastDFSHelper;
    @Autowired
    private ImageService imageService;
    @Autowired
    private RecordService recordService;

    /**
     * 获取资源
     */
    @Transactional
    public void getResource() {
        String siteUrl = "http://www.hezegd.com/";
        String domainName = getDomainForUrl(siteUrl);

        Record record = new Record();
        record.setDomainName(domainName);
        long oneSize = 0;
        long oneTime = 0;

        ArrayList<Image> imageList = new ArrayList<>();

        Instant start = Instant.now();
        try {
            ArrayList<String> httpsHtml = getHttpsHtml(siteUrl);
            List<String> imageSrcList = getImageSrc(httpsHtml, siteUrl);
            imageSrcList.forEach((str) -> {
                try {
                    imageList.add(getImagesAndUpload(str, domainName));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        } catch (IOException e) {
            System.out.println(siteUrl + "，请确认可以正常访问！");
            e.printStackTrace();
        }
        oneTime = Duration.between(start, Instant.now()).toMillis();

        Optional<Long> reduce = imageList.stream()
                .map(image -> image.getImageSize())
                .reduce(Long::sum);
        oneSize = reduce.get();


        record.setImageNumber(imageList.size());
        record.setOneSize(oneSize);
        record.setOneTime(oneTime);

        recordService.addRecord(record);
    }

    /**
     * 获取 http[s] 中的的域名
     *
     * 传来的不是 url 地址则会直接把 url 返回
     * @param url
     * @return
     */
    public String getDomainForUrl(String url) {
        Pattern pattern = Pattern.compile("(https?://)([\\w.]*)");
        Matcher matcher = pattern.matcher(url);
        if (matcher.find()) {
            return matcher.group(2);
        }
        return url;
    }

    /**
     * 获取整个页面的 html 源码
     *
     * @param websiteUrl
     * @return
     * @throws IOException
     */
    public ArrayList<String> getHttpsHtml(String websiteUrl) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        URL url = new URL(websiteUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.connect();
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("utf8")));
        ) {
            String tmp = "";
            while ((tmp = reader.readLine()) != null) {
                list.add(tmp);
            }
        }
        connection.disconnect();
        return list;
    }

    /**
     * 获取源码中的 src 地址
     *
     * @param htmlSource
     * @param domainUrl 域名的 url 写法
     * @return
     */
    public List<String> getImageSrc(List<String> htmlSource, String domainUrl) {
        ArrayList<String> imgSrcList = new ArrayList<>();
        //开始提取 img 信息
        htmlSource.forEach((str) -> {
            Pattern imgPattern = Pattern.compile("<img.*?>");
            Matcher imgMatcher = imgPattern.matcher(str);
            if (imgMatcher.find()) {
                String imgStr = imgMatcher.group();
                Pattern srcPattern = Pattern.compile("src=[\\S]*");
                Matcher srcMatcher = srcPattern.matcher(imgStr);
                if (srcMatcher.find()) {
                    String srcStr = srcMatcher.group();
                    String imgURL = srcStr.replaceAll("src=\"?|[\">]?", "");
                    imgSrcList.add(imgURL);
                }
            }
        });
        List<String> completionSrcList = completionSrc(imgSrcList, domainUrl);
        return completionSrcList;
    }

    /**
     * 补全 image src 地址
     * 优的 src 地址是站内写法(xxx/xxx/xxx.jpg)，有的是 url(http://xxx/xxx/xxx.jpg) 写法。
     * 将站内写法转为 url 写法
     * 如果是 url 写法不做任何处理
     * 还会做去重处理
     *
     * @param imageSrcList
     * @param domainUrl 域名的 http 形式
     * @return
     */
    private List<String> completionSrc(List<String> imageSrcList, String domainUrl) {

        Pattern pattern = Pattern.compile("^http");
        List<String> completionImageSrcList = imageSrcList.stream()
                .map((str) -> {
                    Matcher matcher = pattern.matcher(str);
                    if (!matcher.find()) {
                        if (str.startsWith("/")) {
                            return domainUrl + str.replaceFirst("/", "");
                        }else if (str.startsWith("./")) {
                            return domainUrl + str.replaceFirst("./", "");
                        }
                        return domainUrl + str;
                    }
                    return str;
                })
                .distinct()
                .collect(Collectors.toList());

        return completionImageSrcList;
    }

    /**
     * 下载图片
     * 上传 fastdfs
     * 保存记录到数据库
     *
     * @param resourceName 资源的地址（一般是静态资源）
     * @param domainName   资源的域名
     * @throws IOException 文件创建失败
     */
    @Transactional()
    public Image getImagesAndUpload(String resourceName, String domainName) throws IOException {
        LOGGER.debug("###即将下载的url地址为：{} ###", resourceName);
        //获取文件的名字
        String[] split = resourceName.split("/");
        String imgName = split[split.length - 1];
        LOGGER.debug("###即将要创建临时文件的文件名为 {} ###", imgName);

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
        LOGGER.debug("###连接到 {} ###", resourceName);
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
            LOGGER.debug("###临时文件 {} 下载完成###", tempFile.getAbsolutePath());
        }
        connection.disconnect();
        LOGGER.debug("###断开与 {} 的连接###", resourceName);

        //上传到 fastdfs
        LOGGER.debug("###即将上传到 fastdfs : {} ###",fastDFSHelper);
        StorePath storePath = fastDFSHelper.uploadImageAndCrtThumbImageByScale(tempFile, domainName);
        LOGGER.debug("###图片的 StorePath {}###", storePath);
        //保存到数据库中
        FileInfo fileInfo = fastDFSHelper.getFileInfo(storePath.getGroup(), storePath.getPath());
        LOGGER.debug("###图片的 FileInfo {}###", fileInfo);
        String thumbImagePath = fastDFSHelper.getThumbImagePath(storePath.getPath());
        LOGGER.debug("###图片的 thumbImagePath {}###", thumbImagePath);
        Set<MetaData> metaDataSet = fastDFSHelper.getMetadata(storePath.getGroup(), storePath.getPath());
        LOGGER.debug("###图片的 metaDataSet {}###", metaDataSet);

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
        LOGGER.debug("###生成的 image {}###",image);

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
            LOGGER.debug("###删除临时文件 {} ###", imgName);
        }

        return image;
    }






    /*public static void main01(String[] args) {


        URL url = null;
        try {
            url = new URL("https://www.tmall.com/");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), Charset.forName("utf8")));
        ) {
            String tmp = "";
            while ((tmp = reader.readLine()) != null) {
                System.out.println(tmp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/


}
