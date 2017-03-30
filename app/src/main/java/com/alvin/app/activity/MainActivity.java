package com.alvin.app.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.alvin.app.R;
import com.alvin.app.adapter.TabAdapter;
import com.alvin.app.fragment.TestFragment;
import com.alvin.app.widght.MDTabLayout;


/**
 * 此代码是仿照博客:
 * http://blog.csdn.net/qibin0506/article/details/51346930
 * 所写
 */
public class MainActivity extends AppCompatActivity {
    private MDTabLayout mMDTabLayout;
    private String[] mMenus = new String[]{"智能助理", "照片", "相册"};

    private ViewPager mViewPager;
    private FragmentPagerAdapter mFragmentPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMDTabLayout = (MDTabLayout) findViewById(R.id.tabLayout);
        mMDTabLayout.setAdapter(new Adapter());
        mMDTabLayout.setItemChecked(0);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);

        mMDTabLayout.setItemCheckedListener(new MDTabLayout.ItemCheckedListener() {
            @Override
            public void onItemChecked(int position, View view) {
                mViewPager.setCurrentItem(position);
            }
        });

        mMDTabLayout.setupWithViewPager(mViewPager);
        mFragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return TestFragment.newInstance(mMenus[position]);
            }

            @Override
            public int getCount() {
                return mMenus.length;
            }
        };
        mViewPager.setAdapter(mFragmentPagerAdapter);
    }

    class Adapter extends TabAdapter {

        @Override
        public int getItemCount() {
            return 3;
        }

        @Override
        public Drawable getDrawable(int position) {
            int res = getResources().getIdentifier("icon" + position, "drawable", getPackageName());
            return getResources().getDrawable(res);
        }

        @Override
        public CharSequence getText(int position) {
            return mMenus[position];
        }
    }
}
