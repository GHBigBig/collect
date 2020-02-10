package com.zjg.collect.dao.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ImageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ImageExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andResourceUrlIsNull() {
            addCriterion("resource_url is null");
            return (Criteria) this;
        }

        public Criteria andResourceUrlIsNotNull() {
            addCriterion("resource_url is not null");
            return (Criteria) this;
        }

        public Criteria andResourceUrlEqualTo(String value) {
            addCriterion("resource_url =", value, "resourceUrl");
            return (Criteria) this;
        }

        public Criteria andResourceUrlNotEqualTo(String value) {
            addCriterion("resource_url <>", value, "resourceUrl");
            return (Criteria) this;
        }

        public Criteria andResourceUrlGreaterThan(String value) {
            addCriterion("resource_url >", value, "resourceUrl");
            return (Criteria) this;
        }

        public Criteria andResourceUrlGreaterThanOrEqualTo(String value) {
            addCriterion("resource_url >=", value, "resourceUrl");
            return (Criteria) this;
        }

        public Criteria andResourceUrlLessThan(String value) {
            addCriterion("resource_url <", value, "resourceUrl");
            return (Criteria) this;
        }

        public Criteria andResourceUrlLessThanOrEqualTo(String value) {
            addCriterion("resource_url <=", value, "resourceUrl");
            return (Criteria) this;
        }

        public Criteria andResourceUrlLike(String value) {
            addCriterion("resource_url like", value, "resourceUrl");
            return (Criteria) this;
        }

        public Criteria andResourceUrlNotLike(String value) {
            addCriterion("resource_url not like", value, "resourceUrl");
            return (Criteria) this;
        }

        public Criteria andResourceUrlIn(List<String> values) {
            addCriterion("resource_url in", values, "resourceUrl");
            return (Criteria) this;
        }

        public Criteria andResourceUrlNotIn(List<String> values) {
            addCriterion("resource_url not in", values, "resourceUrl");
            return (Criteria) this;
        }

        public Criteria andResourceUrlBetween(String value1, String value2) {
            addCriterion("resource_url between", value1, value2, "resourceUrl");
            return (Criteria) this;
        }

        public Criteria andResourceUrlNotBetween(String value1, String value2) {
            addCriterion("resource_url not between", value1, value2, "resourceUrl");
            return (Criteria) this;
        }

        public Criteria andGroupNameIsNull() {
            addCriterion("group_name is null");
            return (Criteria) this;
        }

        public Criteria andGroupNameIsNotNull() {
            addCriterion("group_name is not null");
            return (Criteria) this;
        }

        public Criteria andGroupNameEqualTo(String value) {
            addCriterion("group_name =", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotEqualTo(String value) {
            addCriterion("group_name <>", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameGreaterThan(String value) {
            addCriterion("group_name >", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameGreaterThanOrEqualTo(String value) {
            addCriterion("group_name >=", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLessThan(String value) {
            addCriterion("group_name <", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLessThanOrEqualTo(String value) {
            addCriterion("group_name <=", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLike(String value) {
            addCriterion("group_name like", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotLike(String value) {
            addCriterion("group_name not like", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameIn(List<String> values) {
            addCriterion("group_name in", values, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotIn(List<String> values) {
            addCriterion("group_name not in", values, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameBetween(String value1, String value2) {
            addCriterion("group_name between", value1, value2, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotBetween(String value1, String value2) {
            addCriterion("group_name not between", value1, value2, "groupName");
            return (Criteria) this;
        }

        public Criteria andThumbnailStorePathIsNull() {
            addCriterion("thumbnail_store_path is null");
            return (Criteria) this;
        }

        public Criteria andThumbnailStorePathIsNotNull() {
            addCriterion("thumbnail_store_path is not null");
            return (Criteria) this;
        }

        public Criteria andThumbnailStorePathEqualTo(String value) {
            addCriterion("thumbnail_store_path =", value, "thumbnailStorePath");
            return (Criteria) this;
        }

        public Criteria andThumbnailStorePathNotEqualTo(String value) {
            addCriterion("thumbnail_store_path <>", value, "thumbnailStorePath");
            return (Criteria) this;
        }

        public Criteria andThumbnailStorePathGreaterThan(String value) {
            addCriterion("thumbnail_store_path >", value, "thumbnailStorePath");
            return (Criteria) this;
        }

        public Criteria andThumbnailStorePathGreaterThanOrEqualTo(String value) {
            addCriterion("thumbnail_store_path >=", value, "thumbnailStorePath");
            return (Criteria) this;
        }

        public Criteria andThumbnailStorePathLessThan(String value) {
            addCriterion("thumbnail_store_path <", value, "thumbnailStorePath");
            return (Criteria) this;
        }

        public Criteria andThumbnailStorePathLessThanOrEqualTo(String value) {
            addCriterion("thumbnail_store_path <=", value, "thumbnailStorePath");
            return (Criteria) this;
        }

        public Criteria andThumbnailStorePathLike(String value) {
            addCriterion("thumbnail_store_path like", value, "thumbnailStorePath");
            return (Criteria) this;
        }

        public Criteria andThumbnailStorePathNotLike(String value) {
            addCriterion("thumbnail_store_path not like", value, "thumbnailStorePath");
            return (Criteria) this;
        }

        public Criteria andThumbnailStorePathIn(List<String> values) {
            addCriterion("thumbnail_store_path in", values, "thumbnailStorePath");
            return (Criteria) this;
        }

        public Criteria andThumbnailStorePathNotIn(List<String> values) {
            addCriterion("thumbnail_store_path not in", values, "thumbnailStorePath");
            return (Criteria) this;
        }

        public Criteria andThumbnailStorePathBetween(String value1, String value2) {
            addCriterion("thumbnail_store_path between", value1, value2, "thumbnailStorePath");
            return (Criteria) this;
        }

        public Criteria andThumbnailStorePathNotBetween(String value1, String value2) {
            addCriterion("thumbnail_store_path not between", value1, value2, "thumbnailStorePath");
            return (Criteria) this;
        }

        public Criteria andStorePathIsNull() {
            addCriterion("store_path is null");
            return (Criteria) this;
        }

        public Criteria andStorePathIsNotNull() {
            addCriterion("store_path is not null");
            return (Criteria) this;
        }

        public Criteria andStorePathEqualTo(String value) {
            addCriterion("store_path =", value, "storePath");
            return (Criteria) this;
        }

        public Criteria andStorePathNotEqualTo(String value) {
            addCriterion("store_path <>", value, "storePath");
            return (Criteria) this;
        }

        public Criteria andStorePathGreaterThan(String value) {
            addCriterion("store_path >", value, "storePath");
            return (Criteria) this;
        }

        public Criteria andStorePathGreaterThanOrEqualTo(String value) {
            addCriterion("store_path >=", value, "storePath");
            return (Criteria) this;
        }

        public Criteria andStorePathLessThan(String value) {
            addCriterion("store_path <", value, "storePath");
            return (Criteria) this;
        }

        public Criteria andStorePathLessThanOrEqualTo(String value) {
            addCriterion("store_path <=", value, "storePath");
            return (Criteria) this;
        }

        public Criteria andStorePathLike(String value) {
            addCriterion("store_path like", value, "storePath");
            return (Criteria) this;
        }

        public Criteria andStorePathNotLike(String value) {
            addCriterion("store_path not like", value, "storePath");
            return (Criteria) this;
        }

        public Criteria andStorePathIn(List<String> values) {
            addCriterion("store_path in", values, "storePath");
            return (Criteria) this;
        }

        public Criteria andStorePathNotIn(List<String> values) {
            addCriterion("store_path not in", values, "storePath");
            return (Criteria) this;
        }

        public Criteria andStorePathBetween(String value1, String value2) {
            addCriterion("store_path between", value1, value2, "storePath");
            return (Criteria) this;
        }

        public Criteria andStorePathNotBetween(String value1, String value2) {
            addCriterion("store_path not between", value1, value2, "storePath");
            return (Criteria) this;
        }

        public Criteria andCreateDateTimeIsNull() {
            addCriterion("create_date_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateTimeIsNotNull() {
            addCriterion("create_date_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateTimeEqualTo(Date value) {
            addCriterion("create_date_time =", value, "createDateTime");
            return (Criteria) this;
        }

        public Criteria andCreateDateTimeNotEqualTo(Date value) {
            addCriterion("create_date_time <>", value, "createDateTime");
            return (Criteria) this;
        }

        public Criteria andCreateDateTimeGreaterThan(Date value) {
            addCriterion("create_date_time >", value, "createDateTime");
            return (Criteria) this;
        }

        public Criteria andCreateDateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_date_time >=", value, "createDateTime");
            return (Criteria) this;
        }

        public Criteria andCreateDateTimeLessThan(Date value) {
            addCriterion("create_date_time <", value, "createDateTime");
            return (Criteria) this;
        }

        public Criteria andCreateDateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_date_time <=", value, "createDateTime");
            return (Criteria) this;
        }

        public Criteria andCreateDateTimeIn(List<Date> values) {
            addCriterion("create_date_time in", values, "createDateTime");
            return (Criteria) this;
        }

        public Criteria andCreateDateTimeNotIn(List<Date> values) {
            addCriterion("create_date_time not in", values, "createDateTime");
            return (Criteria) this;
        }

        public Criteria andCreateDateTimeBetween(Date value1, Date value2) {
            addCriterion("create_date_time between", value1, value2, "createDateTime");
            return (Criteria) this;
        }

        public Criteria andCreateDateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_date_time not between", value1, value2, "createDateTime");
            return (Criteria) this;
        }

        public Criteria andImageSizeIsNull() {
            addCriterion("image_size is null");
            return (Criteria) this;
        }

        public Criteria andImageSizeIsNotNull() {
            addCriterion("image_size is not null");
            return (Criteria) this;
        }

        public Criteria andImageSizeEqualTo(Long value) {
            addCriterion("image_size =", value, "imageSize");
            return (Criteria) this;
        }

        public Criteria andImageSizeNotEqualTo(Long value) {
            addCriterion("image_size <>", value, "imageSize");
            return (Criteria) this;
        }

        public Criteria andImageSizeGreaterThan(Long value) {
            addCriterion("image_size >", value, "imageSize");
            return (Criteria) this;
        }

        public Criteria andImageSizeGreaterThanOrEqualTo(Long value) {
            addCriterion("image_size >=", value, "imageSize");
            return (Criteria) this;
        }

        public Criteria andImageSizeLessThan(Long value) {
            addCriterion("image_size <", value, "imageSize");
            return (Criteria) this;
        }

        public Criteria andImageSizeLessThanOrEqualTo(Long value) {
            addCriterion("image_size <=", value, "imageSize");
            return (Criteria) this;
        }

        public Criteria andImageSizeIn(List<Long> values) {
            addCriterion("image_size in", values, "imageSize");
            return (Criteria) this;
        }

        public Criteria andImageSizeNotIn(List<Long> values) {
            addCriterion("image_size not in", values, "imageSize");
            return (Criteria) this;
        }

        public Criteria andImageSizeBetween(Long value1, Long value2) {
            addCriterion("image_size between", value1, value2, "imageSize");
            return (Criteria) this;
        }

        public Criteria andImageSizeNotBetween(Long value1, Long value2) {
            addCriterion("image_size not between", value1, value2, "imageSize");
            return (Criteria) this;
        }

        public Criteria andDomainNameIsNull() {
            addCriterion("domain_name is null");
            return (Criteria) this;
        }

        public Criteria andDomainNameIsNotNull() {
            addCriterion("domain_name is not null");
            return (Criteria) this;
        }

        public Criteria andDomainNameEqualTo(String value) {
            addCriterion("domain_name =", value, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameNotEqualTo(String value) {
            addCriterion("domain_name <>", value, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameGreaterThan(String value) {
            addCriterion("domain_name >", value, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameGreaterThanOrEqualTo(String value) {
            addCriterion("domain_name >=", value, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameLessThan(String value) {
            addCriterion("domain_name <", value, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameLessThanOrEqualTo(String value) {
            addCriterion("domain_name <=", value, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameLike(String value) {
            addCriterion("domain_name like", value, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameNotLike(String value) {
            addCriterion("domain_name not like", value, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameIn(List<String> values) {
            addCriterion("domain_name in", values, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameNotIn(List<String> values) {
            addCriterion("domain_name not in", values, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameBetween(String value1, String value2) {
            addCriterion("domain_name between", value1, value2, "domainName");
            return (Criteria) this;
        }

        public Criteria andDomainNameNotBetween(String value1, String value2) {
            addCriterion("domain_name not between", value1, value2, "domainName");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}