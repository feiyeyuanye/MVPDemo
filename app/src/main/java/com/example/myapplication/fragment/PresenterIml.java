package com.example.myapplication.fragment;

import android.util.Log;

import com.example.myapplication.base.BaseCallbackListener;
import com.example.myapplication.bean.UserBean;

/**
 * Created by xwxwaa on 2019/4/25.
 */

public class PresenterIml implements Contract.ContractPresenter {
    private Contract.ContractView contractView;
    private Contract.ContractModel contractModel;

    public PresenterIml(Contract.ContractView contractView) {
        this.contractView = contractView;
        contractModel =new ModelIml();
    }

    @Override
    public void onDestroy() {
        contractView = null;
        System.gc();
    }

    @Override
    public void startRequestData(String id, final String type) {
        contractModel.getDataList(id,type, new BaseCallbackListener<UserBean>() {
            @Override
            public void onStart() {
                //拿到结果之后判断v层是否已经销毁，防止空对象
                if (contractView == null) {
                    return;
                }
            }

            @Override
            public void onNext(UserBean result) {
                //拿到结果之后判断v层是否已经销毁，防止空对象
                if (contractView == null) {
                    return;
                }
                switch (result.getCode()) {
                    case "200":
                            contractView.showView(result);
                        break;
                    case "500":
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onError(String str) {
                //拿到结果之后判断v层是否已经销毁，防止空对象
                if (contractView == null) {
                    return;
                }
                // 逻辑代码
            }
        });
    }
}