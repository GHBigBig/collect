package com.zjg.collect.dao.model;

import java.util.Date;

public class Image {
    private String resourceUrl;

    private String groupName;

    private String thumbnailStorePath;

    private String storePath;

    private Date createDateTime;

    private Long imageSize;

    private String domainName;

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl == null ? null : resourceUrl.trim();
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public String getThumbnailStorePath() {
        return thumbnailStorePath;
    }

    public void setThumbnailStorePath(String thumbnailStorePath) {
        this.thumbnailStorePath = thumbnailStorePath == null ? null : thumbnailStorePath.trim();
    }

    public String getStorePath() {
        return storePath;
    }

    public void setStorePath(String storePath) {
        this.storePath = storePath == null ? null : storePath.trim();
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    public Long getImageSize() {
        return imageSize;
    }

    public void setImageSize(Long imageSize) {
        this.imageSize = imageSize;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName == null ? null : domainName.trim();
    }
}