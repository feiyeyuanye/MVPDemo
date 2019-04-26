package com.example.myapplication.fragment;

import com.example.myapplication.base.BaseCallbackListener;
import com.example.myapplication.base.BasePresenter;
import com.example.myapplication.bean.UserBean;

/**
 * Created by xwxwaa on 2019/4/25.
 */

public class Contract {
    /**
     * M层
     */
    public interface ContractModel{
        void getDataList(String id, String type, BaseCallbackListener<UserBean> callbackListener);
    }
    /**
     * V层
     */
    public interface ContractView{
        void showView(UserBean user);
    }
    /**
     * P层
     */
    public interface ContractPresenter extends BasePresenter {
        void startRequestData(String id, String type);
    }

}

