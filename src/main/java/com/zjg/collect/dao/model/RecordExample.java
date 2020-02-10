package com.zjg.collect.dao.model;

import java.util.ArrayList;
import java.util.List;

public class RecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RecordExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
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

        public Criteria andImageNumberIsNull() {
            addCriterion("image_number is null");
            return (Criteria) this;
        }

        public Criteria andImageNumberIsNotNull() {
            addCriterion("image_number is not null");
            return (Criteria) this;
        }

        public Criteria andImageNumberEqualTo(Integer value) {
            addCriterion("image_number =", value, "imageNumber");
            return (Criteria) this;
        }

        public Criteria andImageNumberNotEqualTo(Integer value) {
            addCriterion("image_number <>", value, "imageNumber");
            return (Criteria) this;
        }

        public Criteria andImageNumberGreaterThan(Integer value) {
            addCriterion("image_number >", value, "imageNumber");
            return (Criteria) this;
        }

        public Criteria andImageNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("image_number >=", value, "imageNumber");
            return (Criteria) this;
        }

        public Criteria andImageNumberLessThan(Integer value) {
            addCriterion("image_number <", value, "imageNumber");
            return (Criteria) this;
        }

        public Criteria andImageNumberLessThanOrEqualTo(Integer value) {
            addCriterion("image_number <=", value, "imageNumber");
            return (Criteria) this;
        }

        public Criteria andImageNumberIn(List<Integer> values) {
            addCriterion("image_number in", values, "imageNumber");
            return (Criteria) this;
        }

        public Criteria andImageNumberNotIn(List<Integer> values) {
            addCriterion("image_number not in", values, "imageNumber");
            return (Criteria) this;
        }

        public Criteria andImageNumberBetween(Integer value1, Integer value2) {
            addCriterion("image_number between", value1, value2, "imageNumber");
            return (Criteria) this;
        }

        public Criteria andImageNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("image_number not between", value1, value2, "imageNumber");
            return (Criteria) this;
        }

        public Criteria andOneSizeIsNull() {
            addCriterion("one_size is null");
            return (Criteria) this;
        }

        public Criteria andOneSizeIsNotNull() {
            addCriterion("one_size is not null");
            return (Criteria) this;
        }

        public Criteria andOneSizeEqualTo(Long value) {
            addCriterion("one_size =", value, "oneSize");
            return (Criteria) this;
        }

        public Criteria andOneSizeNotEqualTo(Long value) {
            addCriterion("one_size <>", value, "oneSize");
            return (Criteria) this;
        }

        public Criteria andOneSizeGreaterThan(Long value) {
            addCriterion("one_size >", value, "oneSize");
            return (Criteria) this;
        }

        public Criteria andOneSizeGreaterThanOrEqualTo(Long value) {
            addCriterion("one_size >=", value, "oneSize");
            return (Criteria) this;
        }

        public Criteria andOneSizeLessThan(Long value) {
            addCriterion("one_size <", value, "oneSize");
            return (Criteria) this;
        }

        public Criteria andOneSizeLessThanOrEqualTo(Long value) {
            addCriterion("one_size <=", value, "oneSize");
            return (Criteria) this;
        }

        public Criteria andOneSizeIn(List<Long> values) {
            addCriterion("one_size in", values, "oneSize");
            return (Criteria) this;
        }

        public Criteria andOneSizeNotIn(List<Long> values) {
            addCriterion("one_size not in", values, "oneSize");
            return (Criteria) this;
        }

        public Criteria andOneSizeBetween(Long value1, Long value2) {
            addCriterion("one_size between", value1, value2, "oneSize");
            return (Criteria) this;
        }

        public Criteria andOneSizeNotBetween(Long value1, Long value2) {
            addCriterion("one_size not between", value1, value2, "oneSize");
            return (Criteria) this;
        }

        public Criteria andOneTimeIsNull() {
            addCriterion("one_time is null");
            return (Criteria) this;
        }

        public Criteria andOneTimeIsNotNull() {
            addCriterion("one_time is not null");
            return (Criteria) this;
        }

        public Criteria andOneTimeEqualTo(Long value) {
            addCriterion("one_time =", value, "oneTime");
            return (Criteria) this;
        }

        public Criteria andOneTimeNotEqualTo(Long value) {
            addCriterion("one_time <>", value, "oneTime");
            return (Criteria) this;
        }

        public Criteria andOneTimeGreaterThan(Long value) {
            addCriterion("one_time >", value, "oneTime");
            return (Criteria) this;
        }

        public Criteria andOneTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("one_time >=", value, "oneTime");
            return (Criteria) this;
        }

        public Criteria andOneTimeLessThan(Long value) {
            addCriterion("one_time <", value, "oneTime");
            return (Criteria) this;
        }

        public Criteria andOneTimeLessThanOrEqualTo(Long value) {
            addCriterion("one_time <=", value, "oneTime");
            return (Criteria) this;
        }

        public Criteria andOneTimeIn(List<Long> values) {
            addCriterion("one_time in", values, "oneTime");
            return (Criteria) this;
        }

        public Criteria andOneTimeNotIn(List<Long> values) {
            addCriterion("one_time not in", values, "oneTime");
            return (Criteria) this;
        }

        public Criteria andOneTimeBetween(Long value1, Long value2) {
            addCriterion("one_time between", value1, value2, "oneTime");
            return (Criteria) this;
        }

        public Criteria andOneTimeNotBetween(Long value1, Long value2) {
            addCriterion("one_time not between", value1, value2, "oneTime");
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