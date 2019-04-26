package com.example.myapplication.activity;

import android.util.Log;

import com.example.myapplication.base.BaseCallbackListener;
import com.example.myapplication.bean.UserBean;
import com.example.myapplication.info.Info;
import com.example.myapplication.utils.RetrofitUtils;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

/**
 * Created by xwxwaa on 2019/4/25.
 */

public class ModelIml implements Contract.ContractModel{

    @Override
    public void getDataList(String id,String type, final BaseCallbackListener<UserBean> callBack) {
        RetrofitUtils.newInstence()//实例化Retrofit对象
                .create(Info.class)//创建Rxjava---->LoginService对象
                .getDataList(id)
                .subscribeOn(Schedulers.newThread())//在新线程中执行登录请求
                .observeOn(AndroidSchedulers.mainThread())//在主线程中执行
                .subscribe(new Observer<UserBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        callBack.onStart();
                    }

                    @Override
                    public void onNext(UserBean user) {
                        if ("200".equals(user.getCode())) {
                            callBack.onNext(user);
                        }else {
                            callBack.onError(user.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG",e.getMessage());
                        e.printStackTrace();
                        if (e instanceof HttpException) {
                            HttpException httpException = (HttpException) e;
                            //httpException.response().errorBody().string()
                            int code = httpException.code();
                            if (code == 500 || code == 404) {
                                callBack.onError("服务器出错");
                            }
                        }else if (e instanceof ConnectException) {
                            callBack.onError("网络断开,请打开网络!");
                        } else if (e instanceof SocketTimeoutException) {
                            callBack.onError("网络连接超时!!");
                        } else {
                            callBack.onError("发生未知错误" + e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
