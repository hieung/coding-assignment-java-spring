package com.gfg.product.util;

import java.io.Serializable;

public class SearchCriteria implements Serializable {
    private String key;
    private String operation;
    private transient Object value;
    private boolean orPredicate;

    private static final long serialVersionUID = 1905122041950251207L;

    public SearchCriteria() {
    }

    public SearchCriteria(String key, String operation, Object value) {
        super();
        this.key = key;
        this.operation = operation;
        this.value = value;
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public boolean isOrPredicate() {
        return orPredicate;
    }

    public void setOrPredicate(boolean orPredicate) {
        this.orPredicate = orPredicate;
    }
}