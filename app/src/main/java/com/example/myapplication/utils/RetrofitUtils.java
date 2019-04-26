package com.example.myapplication.utils;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retofit网络请求工具类
 */
public class RetrofitUtils {
    private static final int READ_TIMEOUT = 60;//读取超时时间,单位  秒
    private static final int CONN_TIMEOUT = 12;//连接超时时间,单位  秒
    private static Retrofit mRetrofit;
    private RetrofitUtils() {

    }

    public static Retrofit newInstence() {
        mRetrofit = null;
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //打印retrofit日志
                Log.e("TAG","(RetrofitLog)"+message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //初始化一个client,不然retrofit会自己默认添加一个
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(READ_TIMEOUT, TimeUnit.MINUTES)
                .connectTimeout(CONN_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build();
//        client.setReadTimeout();//设置读取时间为一分钟
//        client.setConnectTimeout(;//设置连接时间为12s
        mRetrofit = new Retrofit.Builder()
                .client(client) //添加一个client,不然retrofit会自己默认添加一个
                .baseUrl("https://url/") //https://url/a/b
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return mRetrofit;
    }
}
