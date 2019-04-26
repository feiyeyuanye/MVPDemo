package com.example.myapplication.info;

import com.example.myapplication.bean.UserBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by xwxwaa on 2019/4/25.
 */

public interface Info {
    /**
     * https://url/a/b
     */
    @POST("a/b")
    @FormUrlEncoded
    Observable<UserBean> getDataList(@Field("id") String id);

}
