package com.test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.view.LineChartView;

/**
 * Created by Won on 2016/5/4.
 */
public class MainTab01 extends LazyFragment implements View.OnClickListener, SpinerAdapter.OnItemSelectListener{
    private List<String> mListType = new ArrayList<String>();  //类型列表
    private TextView mTView;
    private SpinerAdapter mAdapter;
    private RelativeLayout relativeLayout;
    private SpinerPopWindow mSpinerPopWindow;


    private List<String> mListType2 = new ArrayList<String>();  //类型列表
    private TextView mTView2;
    private RelativeLayout relativeLayout2;
    private SpinerPopWindow mSpinerPopWindow2;
    private SpinerAdapter mAdapter2;
    private View view;
    private LineChartView lineChartView;
    // 标志位，标志已经初始化完成。
    private boolean isPrepared;

//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    @Override
    public View initView() {
        view=View.inflate(mContext, R.layout.tab_01, null);
        initUI();//实例化控件
        isPrepared = true;
        lazyLoad();//加载数据

        return view;
    }

//    view = inflater.inflate(R.layout.tab_01, container, false);

//    }

    /**
     * 实例化组件
     */
    private void initUI() {
        lineChartView=view.findViewById(R.id.chart);
        place();
        school();
    }

    /**
     * 实现懒加载,当屏幕显示这个界面的时候才会触发次方法
     */
    @Override
    protected void lazyLoad() {
        if (isPrepared && isVisible) {
            //TODO 在这里处理逻辑
            Toast.makeText(getContext(), "MainTab01", Toast.LENGTH_SHORT).show();
        }
    }

    //设置PopWindow
    private void showSpinWindow(SpinerPopWindow mSpinerPopWindow,RelativeLayout relativeLayout) {
        //设置mSpinerPopWindow显示的宽度
        mSpinerPopWindow.setWidth(relativeLayout.getWidth());
        //设置显示的位置在哪个控件的下方
        mSpinerPopWindow.showAsDropDown(relativeLayout);
    }


    public void place(){
        mTView = (TextView) view.findViewById(R.id.tv_value);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.relativelayout);
        relativeLayout.setOnClickListener(this);
        //初始化数据
        mListType.add("武陵区");
        mListType.add("鼎城区");
        mListType.add("安乡县");
        mListType.add("汉寿县");
        mListType.add("桃源县");
        mListType.add("临澧县");
        mListType.add("石门县");
        mListType.add("澧县");

        mAdapter = new SpinerAdapter(mContext, mListType);
        mAdapter.refreshData(mListType, 0);

        //初始化PopWindow
        mSpinerPopWindow = new SpinerPopWindow(mContext);
        mSpinerPopWindow.setAdatper(mAdapter);
        mSpinerPopWindow.setItemListener(this);
    }
    public void school(){

        mTView2 = (TextView) view.findViewById(R.id.tv_value);
        relativeLayout2 = (RelativeLayout) view.findViewById(R.id.relativelayout2);
        relativeLayout2.setOnClickListener(this);
        //初始化数据
        mListType2.add("水景广场");
        mListType2.add("图书馆");
        mListType2.add("第一实验楼");
        mListType2.add("第二实验楼");
        mListType2.add("第三实验楼");
        mListType2.add("第一教学楼");
        mListType2.add("第二教学楼");
        mListType2.add("第三教学楼");
        mListType2.add("逸夫楼");
        mAdapter2 = new SpinerAdapter(mContext, mListType2);
        mAdapter2.refreshData(mListType2, 0);

        //初始化PopWindow
        mSpinerPopWindow2 = new SpinerPopWindow(mContext);
        mSpinerPopWindow2.setAdatper(mAdapter2);
        mSpinerPopWindow2.setItemListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.relativelayout:
                showSpinWindow(mSpinerPopWindow,relativeLayout);//显示SpinerPopWindow
                break;
            case R.id.relativelayout2:
                showSpinWindow(mSpinerPopWindow2,relativeLayout2);
                break;
        }
    }


    /**
     * SpinerPopWindow中的条目点击监听
     * @param pos
     */
    @Override
    public void onItemClick(int pos) {

        String value = mListType.get(pos);
        mTView.setText(value.toString());

//        String value2 = mListType2.get(pos);
//        mTView2.setText(value2.toString());
    }


}
