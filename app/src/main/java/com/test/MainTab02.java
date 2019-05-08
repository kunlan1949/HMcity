package com.test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by Won on 2016/5/4.
 */
public class MainTab02 extends LazyFragment {

    private View view;
    // 标志位，标志已经初始化完成。
    private boolean isPrepared;

//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        view = inflater.inflate(R.layout.tab_02, container, false);
//
//        initUI();//实例化控件
//        isPrepared = true;
//        lazyLoad();//加载数据
//
//        return view;
//    }

    @Override
    public View initView() {
        view=View.inflate(mContext, R.layout.tab_02, null);
        initUI();//实例化控件
        isPrepared = true;
        lazyLoad();//加载数据

        return view;
    }

    /**
     * 实例化组件
     */
    private void initUI() {

    }

    /**
     * 实现懒加载,当屏幕显示这个界面的时候才会触发次方法
     */
    @Override
    protected void lazyLoad() {
        if (isPrepared && isVisible) {
            //TODO 在这里处理逻辑
            Toast.makeText(getContext(), "MainTab02", Toast.LENGTH_SHORT).show();
        }
    }
}
