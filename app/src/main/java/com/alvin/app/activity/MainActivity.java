package com.alvin.app.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alvin.app.R;
import com.alvin.app.adapter.TabAdapter;
import com.alvin.app.widght.MDTabLayout;


/**
 * 此代码是仿照博客:
 * http://blog.csdn.net/qibin0506/article/details/51346930
 * 所写
 */
public class MainActivity extends AppCompatActivity {
    private MDTabLayout mMDTabLayout;
    private String[] mMenus = new String[]{"智能助理", "照片", "相册"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMDTabLayout = (MDTabLayout) findViewById(R.id.tabLayout);
        mMDTabLayout.setAdapter(new Adapter());
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
