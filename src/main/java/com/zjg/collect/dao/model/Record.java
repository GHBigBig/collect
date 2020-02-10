package com.zjg.collect.dao.model;

public class Record {
    private Long id;

    private String domainName;

    private Integer imageNumber;

    private Long oneSize;

    private Long oneTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName == null ? null : domainName.trim();
    }

    public Integer getImageNumber() {
        return imageNumber;
    }

    public void setImageNumber(Integer imageNumber) {
        this.imageNumber = imageNumber;
    }

    public Long getOneSize() {
        return oneSize;
    }

    public void setOneSize(Long oneSize) {
        this.oneSize = oneSize;
    }

    public Long getOneTime() {
        return oneTime;
    }

    public void setOneTime(Long oneTime) {
        this.oneTime = oneTime;
    }

    /**
     * 合并 record
     *
     * @param record
     * @return 一个新的对象
     */
    public Record recordMerger(Record record) {
        Record merger = new Record();
        merger.setDomainName(this.domainName);
        merger.setImageNumber(this.imageNumber + record.getImageNumber());
        merger.setOneSize(this.oneSize + record.getOneSize());
        merger.setOneTime(this.oneTime + record.getOneTime());

        return merger;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", domainName='" + domainName + '\'' +
                ", imageNumber=" + imageNumber +
                ", oneSize=" + oneSize +
                ", oneTime=" + oneTime +
                '}';
    }
}