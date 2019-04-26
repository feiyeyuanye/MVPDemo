package com.example.myapplication.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by xwxwaa on 2019/4/25.
 */

public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), getLayoutResId(), null);
        initView(view);
        initData();
        return view;
    }
    /**
     * 返回资源的布局
     *
     * @return
     */
    public abstract int getLayoutResId();

    /**
     * 组件初始化操作
     *
     * @param view 父view
     */
    public abstract void initView(View view);

    /**
     * 页面初始化页面数据，在initView之后调用
     */
    public abstract void initData();

    public void showToast(final String msg) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
            }
        });
    }
}