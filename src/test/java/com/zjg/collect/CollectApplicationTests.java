package com.zjg.collect;

import com.github.tobato.fastdfs.domain.fdfs.FileInfo;
import com.github.tobato.fastdfs.domain.fdfs.MetaData;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.zjg.collect.dao.mapper.ImageMapper;
import com.zjg.collect.dao.model.Image;
import com.zjg.collect.dao.model.ImageExample;
import com.zjg.collect.dao.model.Record;
import com.zjg.collect.network.ForkJoinDownload;
import com.zjg.collect.network.GetResource;
import com.zjg.collect.util.FastDFSHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
class CollectApplicationTests {
    @Autowired
    FastDFSHelper fastDFSHelper;
    @Autowired
    ImageMapper imageMapper;
    @Autowired
    GetResource getResource;


    @Test
    void contextLoads() {
    }

    /**
     * 测试文件上传
     */
    @Test
    void test01() {
        File file = new File("C:\\Users\\maize\\Pictures\\btree.PNG");
        String domainName = "www.baidu.com";
        try {
            StorePath storePath = fastDFSHelper.uploadFileAndMetaData(file, domainName);
            String group = storePath.getGroup();
            String path = storePath.getPath();
            Set<MetaData> metaDataSet = fastDFSHelper.getMetadata(storePath.getGroup(), storePath.getPath());

            Image image = new Image();
            image.setGroupName(group);
            image.setStorePath(path);

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

            FileInfo fileInfo = fastDFSHelper.getFileInfo(storePath.getGroup(), storePath.getPath());
            image.setImageSize(fileInfo.getFileSize());
            image.setResourceUrl("------------test-------------");


            int insert = imageMapper.insert(image);
            System.out.println("影响 " + insert + " 数据");
        } catch (FileNotFoundException e) {
            System.out.println(file.getName() + "上传失败");
            e.printStackTrace();
        }
    }

    /**
     * 测试图像上传
     */
    @Test
    public void test02() {
        File file = new File("C:\\Users\\maize\\Pictures\\btree.PNG");
        String domainName = "www.baidu.com";

        try {
            StorePath storePath = fastDFSHelper.uploadImageAndCrtThumbImageByScale(file, domainName);
            String thumbImagePath = fastDFSHelper.getThumbImagePath(storePath.getPath());


            String group = storePath.getGroup();
            String path = storePath.getPath();
            Set<MetaData> metaDataSet = fastDFSHelper.getMetadata(storePath.getGroup(), storePath.getPath());

            Image image = new Image();
            image.setGroupName(group);
            image.setStorePath(path);
            image.setThumbnailStorePath(thumbImagePath);

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

            FileInfo fileInfo = fastDFSHelper.getFileInfo(storePath.getGroup(), storePath.getPath());
            image.setImageSize(fileInfo.getFileSize());
            image.setResourceUrl("------------test-------------");


            int insert = imageMapper.insert(image);
            System.out.println("影响 " + insert + " 数据");

        } catch (FileNotFoundException e) {
            System.out.println("图片上传失败：" + file);
            e.printStackTrace();
        }
    }

    @Test
//    @Transactional
    public void test03() {
        String domainName = "www.heze.cn";
        String websiteName = "http://" + domainName + "/";

        try {
            ArrayList<String> httpsHtml = getResource.getHttpsHtml(websiteName);
            List<String> imageSrcList = getResource.getImageSrc(httpsHtml, websiteName);
            imageSrcList.forEach((str) -> {
                try {
                    getResource.getImagesAndUpload(str, domainName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        } catch (IOException e) {
            System.out.println(websiteName + "，请确认可以正常访问！");
            e.printStackTrace();
        }
    }

    /**
     * 获取所有的 image 记录并进行流操作
     * <p>
     * 内置 4 大核心函数接口
     * Consumer<T>：消费型接口
     * Supplier<T>：供给型接口
     * Function<T,R>：函数型接口，利用 T 生成 R
     * Predicate<T>：断定型接口
     */
    @Test
    public void test04() {
        List<Image> imageList = imageMapper.selectByExample(new ImageExample());

        Image initImage = new Image();
        Integer initInteger = new Integer(0);
        Long initLong = new Long(0);
        initImage.setDomainName("*************************");
        Optional<Image> optionalImage = Optional.of(initImage);

        /*
            多个中间操作可以连接起来形成一个流水线，除非流水
            线上触发终止操作，否则中间操作不会执行任何的处理！
            而在终止操作时一次性全部处理，称为“惰性求值”。
         */
        Pattern pattern = Pattern.compile("[^a]");
        AtomicInteger filterCount = new AtomicInteger(0);
        Long reduce = imageList.stream()
                .filter((image) -> {        //筛选与切片：过滤
                    System.out.println("pattern hashcode : " + pattern.hashCode() + ", filterCount : " + filterCount.addAndGet(1));
                    Matcher matcher = pattern.matcher(image.getStorePath());
                    return matcher.find();
                })
                .distinct()         //筛选与切片：去除
                .skip(5)            //筛选与切片：跳过前 n 个元素
                .limit(20)          //截断流，使其元素不超过给定数量。
                .map((img) -> {     //映射：接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
                    Image image = new Image();
                    image.setDomainName(img.getDomainName() + " ---> map a new item");
                    image.setGroupName(img.getGroupName());
                    image.setImageSize(img.getImageSize());
                    image.setResourceUrl(img.getResourceUrl());
                    image.setStorePath(img.getStorePath());
                    image.setThumbnailStorePath(img.getThumbnailStorePath());
                    image.setCreateDateTime(new Date());
                    return image;
                })
//                .flatMap()        //映射，与map的不同 R 需为 stram
                .sorted((img1, img2) -> {    //排序
                    long result = img1.getImageSize() - img2.getImageSize();
                    if (result > 0) {
                        return -1;
                    } else if (result < 0) {
                        return 1;
                    } else {
                        return 0;
                    }
                })
                /*.allMatch(image -> {  //终止操作：检查是否匹配所有元素
                    return image.getImageSize() > 30000;
                });*/
                /*.anyMatch(image -> {  //查找与匹配：检查是否至少匹配一个元素
                    return image.getImageSize()>30000;
                });*/
                /*.noneMatch(image -> {   //查找与匹配：检查是否没有匹配所有元素
                    return image.getImageSize()>30000;
                })*/
//                .findFirst();   //查找与匹配：返回第一个元素
//                .findAny();     //查找与匹配：返回当前流中的任意元素
//                .count();       //查找与匹配：返回流中元素总数
                /*.max((img1, img2) -> {    //查找与匹配：返回流中最大值
                    return img1.getResouceUri().length() - img2.getResouceUri().length();
                });*/
                /*.min((img1,img2) -> {   //查找与匹配：返回流中最小值
                    long result = img1.getImageSize() - img2.getImageSize();
                    if (result <0) {
                        return -1;
                    }else if (result>0) {
                        return 1;
                    }else {
                        return 0;
                    }
                });*/
//                .forEach(System.out::println);      //终止操作：内部迭代
                /*
                    BinaryOperator接口，可以看到reduce方法接受一个函数，这个函数有两个参数，
                    第一个参数是上次函数执行的返回值（也称为中间结果），第二个参数是stream中的元素，
                    这个函数把这两个值相加，得到的和会被赋值给下次执行这个函数的第一个参数。
                    要注意的是：第一次执行的时候第一个参数的值是Stream的第一个元素，
                    第二个参数是Stream的第二个元素。这个方法返回值类型是Optional，
                 */
                /*.reduce((image, image2) -> {    //规约：用于对stream中元素进行聚合求值，最常见的用法就是将stream中一连串的值合成为单个值，比如为一个包含一系列数值的数组求和。
                    image2.setDomainName(image.getDomainName()+image2.getDomainName());
                    return image2;
                });*/
                /*.reduce(initImage, (image, image2) -> {    //规约：用于对stream中元素进行聚合求值，最常见的用法就是将stream中一连串的值合成为单个值，比如为一个包含一系列数值的数组求和。
                    image2.setDomainName(image.getDomainName() + image2.getDomainName());
                    return image2;
                });*/
                /*.reduce(initLong,
                        new BiFunction<Long, Image, Long>() {
                            @Override
                            public Long apply(Long aLong, Image image) {
                                return aLong;
                            }
                        },
                        new BinaryOperator<Long>() {
                            @Override
                            public Long apply(Long aLong, Long aLong2) {
                                return aLong;
                            }
                        }
                );*/
                .reduce(initLong,   //reduce的第三个参数 BinaryOperate 是在使用parallelStream的reduce操作时，合并各个流结果的
                        (aLong, image) -> aLong,
                        (aLong5, aLong6) -> null
                );


        System.out.println(reduce);

    }

    @Test
    public void test05() {
        getResource.getResource();
    }

    /**
     * 获取网页所有的超链接
     */
    @Test
    public void test06() {

    }

    /**
     * 测试多线程的下载，使用 Fork/Join 框架
     *
     * Fork/Join 框架：就是在必要的情况下，将一个大任务，进行拆分(fork)成若干个
     * 小任务（拆到不可再拆时），再将一个个的小任务运算的结果进行 join 汇总
     *
     * 采用 “工作窃取”模式（work-stealing）：
     * 当执行新的任务时它可以将其拆分分成更小的任务执行，并将小任务加到线
     * 程队列中，然后再从一个随机线程的队列中偷一个并把它放在自己的队列中。
     * 相对于一般的线程池实现,fork/join框架的优势体现在对其中包含的任务的
     * 处理方式上.在一般的线程池中,如果一个线程正在执行的任务由于某些原因
     * 无法继续运行,那么该线程会处于等待状态.而在fork/join框架实现中,如果
     * 某个子问题由于等待另外一个子问题的完成而无法继续运行.那么处理该子
     * 问题的线程会主动寻找其他尚未运行的子问题来执行.这种方式减少了线程
     * 的等待时间,提高了性能
     */
    @Test
    public void test07() throws IOException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();

        //获取图片的 src
        String webSiteUrl = "http://www.heze.cn/";
        String domainName = getResource.getDomainForUrl(webSiteUrl);

        GetResource getResource = new GetResource();
        ArrayList<String> htmlList = getResource.getHttpsHtml(webSiteUrl);
        List<String> imageSrcList = getResource.getImageSrc(htmlList, webSiteUrl);

        ForkJoinTask<Record> task = new ForkJoinDownload(imageSrcList, domainName);
        Record record = forkJoinPool.invoke(task);
        System.out.println(record);
    }




}
