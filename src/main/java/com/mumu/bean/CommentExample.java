package com.mumu.bean;

import java.util.ArrayList;
import java.util.List;

public class CommentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CommentExample() {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTheoryIdIsNull() {
            addCriterion("theory_id is null");
            return (Criteria) this;
        }

        public Criteria andTheoryIdIsNotNull() {
            addCriterion("theory_id is not null");
            return (Criteria) this;
        }

        public Criteria andTheoryIdEqualTo(Integer value) {
            addCriterion("theory_id =", value, "theoryId");
            return (Criteria) this;
        }

        public Criteria andTheoryIdNotEqualTo(Integer value) {
            addCriterion("theory_id <>", value, "theoryId");
            return (Criteria) this;
        }

        public Criteria andTheoryIdGreaterThan(Integer value) {
            addCriterion("theory_id >", value, "theoryId");
            return (Criteria) this;
        }

        public Criteria andTheoryIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("theory_id >=", value, "theoryId");
            return (Criteria) this;
        }

        public Criteria andTheoryIdLessThan(Integer value) {
            addCriterion("theory_id <", value, "theoryId");
            return (Criteria) this;
        }

        public Criteria andTheoryIdLessThanOrEqualTo(Integer value) {
            addCriterion("theory_id <=", value, "theoryId");
            return (Criteria) this;
        }

        public Criteria andTheoryIdIn(List<Integer> values) {
            addCriterion("theory_id in", values, "theoryId");
            return (Criteria) this;
        }

        public Criteria andTheoryIdNotIn(List<Integer> values) {
            addCriterion("theory_id not in", values, "theoryId");
            return (Criteria) this;
        }

        public Criteria andTheoryIdBetween(Integer value1, Integer value2) {
            addCriterion("theory_id between", value1, value2, "theoryId");
            return (Criteria) this;
        }

        public Criteria andTheoryIdNotBetween(Integer value1, Integer value2) {
            addCriterion("theory_id not between", value1, value2, "theoryId");
            return (Criteria) this;
        }

        public Criteria andStudentIdIsNull() {
            addCriterion("student_id is null");
            return (Criteria) this;
        }

        public Criteria andStudentIdIsNotNull() {
            addCriterion("student_id is not null");
            return (Criteria) this;
        }

        public Criteria andStudentIdEqualTo(String value) {
            addCriterion("student_id =", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotEqualTo(String value) {
            addCriterion("student_id <>", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdGreaterThan(String value) {
            addCriterion("student_id >", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdGreaterThanOrEqualTo(String value) {
            addCriterion("student_id >=", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdLessThan(String value) {
            addCriterion("student_id <", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdLessThanOrEqualTo(String value) {
            addCriterion("student_id <=", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdLike(String value) {
            addCriterion("student_id like", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotLike(String value) {
            addCriterion("student_id not like", value, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdIn(List<String> values) {
            addCriterion("student_id in", values, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotIn(List<String> values) {
            addCriterion("student_id not in", values, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdBetween(String value1, String value2) {
            addCriterion("student_id between", value1, value2, "studentId");
            return (Criteria) this;
        }

        public Criteria andStudentIdNotBetween(String value1, String value2) {
            addCriterion("student_id not between", value1, value2, "studentId");
            return (Criteria) this;
        }

        public Criteria andCarefullyChosenIsNull() {
            addCriterion("carefully_chosen is null");
            return (Criteria) this;
        }

        public Criteria andCarefullyChosenIsNotNull() {
            addCriterion("carefully_chosen is not null");
            return (Criteria) this;
        }

        public Criteria andCarefullyChosenEqualTo(Boolean value) {
            addCriterion("carefully_chosen =", value, "carefullyChosen");
            return (Criteria) this;
        }

        public Criteria andCarefullyChosenNotEqualTo(Boolean value) {
            addCriterion("carefully_chosen <>", value, "carefullyChosen");
            return (Criteria) this;
        }

        public Criteria andCarefullyChosenGreaterThan(Boolean value) {
            addCriterion("carefully_chosen >", value, "carefullyChosen");
            return (Criteria) this;
        }

        public Criteria andCarefullyChosenGreaterThanOrEqualTo(Boolean value) {
            addCriterion("carefully_chosen >=", value, "carefullyChosen");
            return (Criteria) this;
        }

        public Criteria andCarefullyChosenLessThan(Boolean value) {
            addCriterion("carefully_chosen <", value, "carefullyChosen");
            return (Criteria) this;
        }

        public Criteria andCarefullyChosenLessThanOrEqualTo(Boolean value) {
            addCriterion("carefully_chosen <=", value, "carefullyChosen");
            return (Criteria) this;
        }

        public Criteria andCarefullyChosenIn(List<Boolean> values) {
            addCriterion("carefully_chosen in", values, "carefullyChosen");
            return (Criteria) this;
        }

        public Criteria andCarefullyChosenNotIn(List<Boolean> values) {
            addCriterion("carefully_chosen not in", values, "carefullyChosen");
            return (Criteria) this;
        }

        public Criteria andCarefullyChosenBetween(Boolean value1, Boolean value2) {
            addCriterion("carefully_chosen between", value1, value2, "carefullyChosen");
            return (Criteria) this;
        }

        public Criteria andCarefullyChosenNotBetween(Boolean value1, Boolean value2) {
            addCriterion("carefully_chosen not between", value1, value2, "carefullyChosen");
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