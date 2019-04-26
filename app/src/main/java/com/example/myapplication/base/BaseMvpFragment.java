package com.example.myapplication.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 实现mvp模式的公共Fragment类,所有使用mvp的fragment都需要继承至这个类
 * 温馨提示：
 * 1、实际开发中需要将BaseMvpFragment继承的Fragment改成你自己的BaseFragment（如果有）
 * 2、如果页面再进去其他页面之后不需要了，一定要及时finish
 */

public abstract class BaseMvpFragment extends BaseFragment {
    private BasePresenter presenter = null;
    public Context mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = getActivity();
        presenter = bindPresenter();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * 绑定presenter，主要用于销毁工作
     *
     * @return
     */
    protected abstract BasePresenter bindPresenter();

    /**
     * 如果重写了此方法，一定要调用super.onDestroy();
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.onDestroy();
            presenter = null;
            System.gc();
        }
    }
}
