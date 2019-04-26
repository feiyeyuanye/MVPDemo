package com.example.myapplication.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据原型
 * Created by xwxwaa on 2019/4/25.
 */

public class UserBean {

    private String code ;
    private String msg;

    private String name;
    private int age;
    private List<String> mList;

    public String getCode() {
        return code == null ? "" : code;
    }

    public String getMsg() {
        return msg == null ? "" : msg;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getmList() {
        if (mList == null) {
            return new ArrayList<>();
        }
        return mList;
    }

    public void setmList(List<String> mList) {
        this.mList = mList;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", mList=" + mList +
                '}';
    }
}
