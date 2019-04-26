package com.example.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.example.myapplication.activity.Contract;
import com.example.myapplication.activity.PresenterIml;
import com.example.myapplication.base.BaseMvpActivity;
import com.example.myapplication.base.BasePresenter;
import com.example.myapplication.bean.UserBean;

public class MainActivity extends BaseMvpActivity implements Contract.ContractView {

    private Contract.ContractPresenter presenter = new PresenterIml(this);

    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        showFragment(0);
    }

    @Override
    protected BasePresenter bindPresenter() {
        return presenter;
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 请求数据
//        if (presenter!=null)
//            presenter.startRequestData("","type");
    }

    @Override
    public void showView(UserBean user) {
        Log.e("TAG",user.toString());
    }

    /**
     * 切换Fragment
     */
    // 上一个fragment的序列号
    int currIndex = -1;
    Fragment[] fragmentList = new Fragment[1];

    //展示fragment
    @Override
    public void showFragment(int index) {
        if (currIndex == index)
            return;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (currIndex != -1) {
            ft.detach(fragmentList[currIndex]);
        }
        if (fragmentList[index] != null) {
            ft.attach(fragmentList[index]);
        } else {
            creatFragment(index);
            ft.replace(R.id.fg, fragmentList[index]);
        }
//        if (currIndex != -1) {
//            tab.getChildAt(currIndex).setSelected(false);
//        }
//        tab.getChildAt(index).setSelected(true);
        currIndex = index;
        ft.commit();
    }

    public String[] str = {"MainFragment"};

    public void creatFragment(int index) {
        try {
            Fragment fragment = (Fragment) Class.forName("com.example.myapplication." + str[index]).newInstance();
            fragmentList[index] = fragment;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
