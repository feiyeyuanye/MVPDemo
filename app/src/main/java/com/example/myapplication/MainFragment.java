package com.example.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.example.myapplication.base.BaseMvpFragment;
import com.example.myapplication.base.BasePresenter;
import com.example.myapplication.bean.UserBean;
import com.example.myapplication.fragment.Contract;
import com.example.myapplication.fragment.PresenterIml;

/**
 * Created by xwxwaa on 2019/4/25.
 */

public class MainFragment extends BaseMvpFragment implements Contract.ContractView {
    private Contract.ContractPresenter presenter = new PresenterIml(this);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        // 请求数据
//        if (presenter!=null)
//            presenter.startRequestData("","type");
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_main;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void initData() {
    }

    @Override
    protected BasePresenter bindPresenter() {
        return presenter;
    }

    @Override
    public void showView(UserBean user) {
        Log.e("TAG",user.toString());
    }

}
