package com.example.myapplication.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Toast;

import com.example.myapplication.config.MyApp;

/**
 * Created by xwxwaa on 2019/4/25.
 */

public abstract class BaseActivity extends AppCompatActivity {
    {
        MyApp.getInstance().addActivity(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        initView();
        initData();
    }


    /**
     * 组件初始化操作
     */
    public abstract void initView();

    /**
     * 页面初始化页面数据，在initView之后调用
     */
    public abstract void initData();

    /**
     * 返回资源的布局
     */
    public abstract int getLayoutResId();

    /**
     * 页面跳转
     * @param cls
     */
    public void jumpActivity(Class cls) {
        startActivity(new Intent(this, cls));
    }

    public void jumpActivity(Class cls, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        intent.putExtra("bundle", bundle);
        startActivity(intent);
    }

    /**
     * 吐司
     * @param msg
     */
    public void showToast(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(BaseActivity.this,msg,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}