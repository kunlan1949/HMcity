package com.test;

import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends FragmentActivity {

    //顶部三个LinearLayout
    private LinearLayout mTab01;
    private LinearLayout mTab02;
    private LinearLayout mTab03;

    //顶部的三个TextView
    private TextView id_tab01_info;
    private TextView id_tab02_info;
    private TextView id_tab03_info;

    //Tab的那个引导线
    private ImageView mTabLine;
    //屏幕的宽度
    private int screenWidth;

    private ViewPager mViewPager;
    private FragmentAdapter mAdapter;
    private List<Fragment> fragments = new ArrayList<Fragment>();

    private Resources res;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        res = getResources();

        initView();

        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);

        //初始化Adapter
        mAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments);

        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(new TabOnPageChangeListener());

        initTabLine();
    }

    /**
     * 根据屏幕的宽度，初始化引导线的宽度
     */
    private void initTabLine() {
        mTabLine = (ImageView) findViewById(R.id.id_tab_line);

        //获取屏幕的宽度
        DisplayMetrics outMetrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
        screenWidth = outMetrics.widthPixels;

        //获取控件的LayoutParams参数(注意：一定要用父控件的LayoutParams写LinearLayout.LayoutParams)
        LinearLayout.LayoutParams lp = (android.widget.LinearLayout.LayoutParams) mTabLine.getLayoutParams();
        lp.width = screenWidth / 3;//设置该控件的layoutParams参数
        mTabLine.setLayoutParams(lp);//将修改好的layoutParams设置为该控件的layoutParams
    }

    /**
     * 初始化控件，初始化Fragment
     */
    private void initView() {
        id_tab01_info = (TextView) findViewById(R.id.id_tab01_info);
        id_tab02_info = (TextView) findViewById(R.id.id_tab02_info);
        id_tab03_info = (TextView) findViewById(R.id.id_tab03_info);

        mTab01 = (LinearLayout) findViewById(R.id.id_tab01);
        mTab02 = (LinearLayout) findViewById(R.id.id_tab02);
        mTab03 = (LinearLayout) findViewById(R.id.id_tab03);

        mTab01.setOnClickListener(new TabOnClickListener(0));
        mTab02.setOnClickListener(new TabOnClickListener(1));
        mTab03.setOnClickListener(new TabOnClickListener(2));

        fragments.add(new MainTab01());
        fragments.add(new MainTab02());
        fragments.add(new MainTab03());

    }

    /**
     * 重置颜色
     */
    private void resetTextView() {
        id_tab01_info.setTextColor(res.getColor(R.color.black));
        id_tab02_info.setTextColor(res.getColor(R.color.black));
        id_tab03_info.setTextColor(res.getColor(R.color.black));
    }

    /**
     * 功能：点击主页TAB事件
     */
    public class TabOnClickListener implements View.OnClickListener {
        private int index = 0;

        public TabOnClickListener(int i) {
            index = i;
        }

        public void onClick(View v) {
            mViewPager.setCurrentItem(index);//选择某一页
        }

    }

    /**
     * 功能：Fragment页面改变事件
     */
    public class TabOnPageChangeListener implements ViewPager.OnPageChangeListener {

        //当滑动状态改变时调用
        public void onPageScrollStateChanged(int state) {

        }

        //当前页面被滑动时调用
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            LinearLayout.LayoutParams lp = (android.widget.LinearLayout.LayoutParams) mTabLine.getLayoutParams();
            //返回组件距离左侧组件的距离
            lp.leftMargin = (int) ((positionOffset + position) * screenWidth / 3);
            mTabLine.setLayoutParams(lp);
        }

        //当新的页面被选中时调用
        public void onPageSelected(int position) {
            //重置所有TextView的字体颜色
            resetTextView();
            switch (position) {
                case 0:
                    id_tab01_info.setTextColor(res.getColor(R.color.colorAccent));
                    break;
                case 1:
                    id_tab02_info.setTextColor(res.getColor(R.color.colorAccent));
                    break;
                case 2:
                    id_tab03_info.setTextColor(res.getColor(R.color.colorAccent));
                    break;
            }
        }
    }
}
