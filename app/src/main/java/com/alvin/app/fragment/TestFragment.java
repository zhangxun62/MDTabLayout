package com.alvin.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @Title TestFragment
 * @Description:
 * @Author: alvin
 * @Date: 2016/10/27.17:08
 * @E-mail: 49467306@qq.com
 */
public class TestFragment extends Fragment {
    private static final String TAG = TestFragment.class.getSimpleName();
    private LinearLayout mLinearLayout;
    private String mTitle;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null != getArguments()) {
            mTitle = getArguments().getString("title");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return mLinearLayout = new LinearLayout(getContext());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLinearLayout.setOrientation(LinearLayout.VERTICAL);
        for (int i = 0; i < 20; i++) {
            TextView tv = new TextView(getContext());
            tv.setText(mTitle + String.valueOf(i));
            tv.setPadding(0, 20, 0, 20);
            mLinearLayout.addView(tv);

        }
    }

    public static Fragment newInstance(String i) {
        TestFragment fragment = new TestFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", i);
        fragment.setArguments(bundle);
        return fragment;
    }
}
