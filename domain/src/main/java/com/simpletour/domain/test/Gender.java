package com.simpletour.domain.test;

/**
 * Brief : 性别
 * Author: Hawk
 * Date  : 2016/1/13
 */
public enum Gender {

    male("男"), female("女");

    private String remark;

    Gender(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }
}
