package com.example.rickwu.toolbar_tablayout;


import android.app.Dialog;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;

public class MyPagerDialog extends Dialog {
    private static final int FRIEND_LIST_PAGE = 0;
    private static final int CHAT_ROOM_LIST_PAGE = 1;
    private Context mContext;
    private Toolbar mToolbar;
    private Map<Integer, PagerView> mPagerViewMap;
    private TabLayout mTabs;
    private ViewPager mViewPager;
    public MyPagerDialog(Context context) {
        super(context, R.style.DesignAppTheme);
        mContext = context;
        setContentView(R.layout.my_dialog);
        getWindow().getAttributes().windowAnimations = R.style.sticker_animation;
        initData();
        initView();
    }
    private void initData() {
        mPagerViewMap = new HashMap<>();
        mPagerViewMap.put(FRIEND_LIST_PAGE, new FriendListView(mContext));
        mPagerViewMap.put(CHAT_ROOM_LIST_PAGE, new ChatRoomView(mContext));
    }

    private void initView(){
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setNavigationIcon(R.drawable.navi_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        mTabs = (android.support.design.widget.TabLayout) findViewById(R.id.tabs);
        mTabs.addTab(mTabs.newTab().setText("第一頁"));
        mTabs.addTab(mTabs.newTab().setText("第二頁"));
        mTabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(new SamplePagerAdapter());
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabs){

        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == event.KEYCODE_BACK) {
            dismiss();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private class SamplePagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mPagerViewMap.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return o == view;
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mPagerViewMap.get(position));
            return mPagerViewMap.get(position);
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }
}
