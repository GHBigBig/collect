package com.zjg.collect.network;

import com.zjg.collect.dao.model.Image;
import com.zjg.collect.dao.model.Record;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.RecursiveTask;

/**
 * @author zjg
 * @create 2020-02-05 13:11
 */
public class ForkJoinDownload extends RecursiveTask<Record> {
    private List<String> resourceList = null;
    private String domainName = null;
    private static final int THRESHOLD = 5; //每个线程下载的个数


    public ForkJoinDownload(List<String> resourceList, String domainName) {
        this.resourceList = resourceList;
        this.domainName = domainName;
    }

    /**
     * 一个线程下个 5 个，超出分配到其他线程
     * @return
     */
    @Override
    public Record compute() {
        if (resourceList.size()<=THRESHOLD) {

            Record record = new Record();
            record.setDomainName(domainName);
            long oneSize;
            long oneTime;

            ArrayList<Image> imageList = new ArrayList<>();
            GetResource getResource = new GetResource();

            Instant start = Instant.now();
            resourceList.forEach((str) -> {
                try {
                    imageList.add(getResource.getImagesAndUpload(str, domainName));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            oneTime = Duration.between(start, Instant.now()).toMillis();

            Optional<Long> reduce = imageList.stream()
                    .map(image -> image.getImageSize())
                    .reduce(Long::sum);
            oneSize = reduce.get();


            record.setImageNumber(imageList.size());
            record.setOneSize(oneSize);
            record.setOneTime(oneTime);

            return record;
        }else { //二分，将元素从中分开
            List<String> divideList1 = resourceList.subList(0, resourceList.size() / 2);
            List<String> divideList2 = resourceList.subList(resourceList.size() / 2, resourceList.size());
            ForkJoinDownload divide1 = new ForkJoinDownload(divideList1, domainName);
            divide1.fork();
            ForkJoinDownload divide2 = new ForkJoinDownload(divideList2, domainName);
            divide2.fork();

            return divide1.join().recordMerger(divide2.join());
        }
    }
}
