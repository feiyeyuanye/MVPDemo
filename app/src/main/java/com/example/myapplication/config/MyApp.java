package com.example.myapplication.config;

import android.app.Activity;
import android.app.Application;

import com.example.myapplication.bean.UserBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xwxwaa on 2019/4/25.
 */

public class MyApp extends Application {

    private static MyApp instance;

    public static MyApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    /**
     * 循环遍历退出
     */
    List<Activity> activitys = new ArrayList<>();

    public void addActivity(Activity activity) {
        activitys.add(activity);
    }

    public void exit() {
        for (int i = 0; i < activitys.size(); i++) {
            if (activitys.get(i) != null) {
                activitys.get(i).finish();
            }
        }
    }

    /**
     * 数据单元共享
     */
    private UserBean user;

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }
}
