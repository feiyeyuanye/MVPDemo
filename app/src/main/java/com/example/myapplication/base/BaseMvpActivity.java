package com.example.myapplication.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;


/**
 * 实现mvp模式的公共activity类，所有使用mvp的activity页面都需要继承此类
 * 温馨提示：
 * 1、实际开发中需要将BaseMvpActivity继承的AppCompatActivity改成你自己的BaseActivity（如果有）
 * 2、如果页面再进去其他页面之后不需要了，一定要及时finish
 */

public abstract class BaseMvpActivity extends BaseActivity  {
    private BasePresenter presenter = null;
    public Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = bindPresenter();
        mContext = this;
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
