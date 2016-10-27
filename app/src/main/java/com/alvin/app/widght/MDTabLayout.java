package com.alvin.app.widght;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.alvin.app.R;
import com.alvin.app.adapter.TabAdapter;


/**
 * @Title TabLayout
 * @Description:
 * @Author: alvin
 * @Date: 2016/10/26.16:14
 * @E-mail: 49467306@qq.com
 */
public class MDTabLayout extends LinearLayout {
    private static final String TAG = MDTabLayout.class.getSimpleName();
    private TabAdapter mAdapter;
    private LayoutParams params;
    /**
     * 选中颜色
     */
    private int mCheckedItemColor;
    /**
     * 默认颜色
     */
    private int mNormalItemColor;

    /**
     * 选中监听事件
     */
    private ItemCheckedListener mItemCheckedListener;
    /**
     * 选中的位置
     */
    private int mCheckedPosition;

    public MDTabLayout(Context context) {
        this(context, null);
    }

    public MDTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MDTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(HORIZONTAL);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MDTabLayout, defStyleAttr, 0);
        mCheckedItemColor = typedArray.getColor(R.styleable.MDTabLayout_checkedItemColor, getResources().getColor(android.R.color.holo_orange_light));
        mNormalItemColor = typedArray.getColor(R.styleable.MDTabLayout_normalItemColor, 0xFF444444);
        params = new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public void setAdapter(TabAdapter adapter) {
        mAdapter = adapter;
        mAdapter.registerObserver(mObserver);
        mAdapter.notifyDataSetChanged();

    }

    public void setCheckedItemColor(int checkedItemColor) {
        mCheckedItemColor = checkedItemColor;
    }

    public void setNormalItemColor(int normalItemColor) {
        mNormalItemColor = normalItemColor;
    }

    private DataSetObserver mObserver = new DataSetObserver() {
        @Override
        public void onChanged() {
            onInvalidated();
            if (null == mAdapter)
                return;
            int itemCount = mAdapter.getItemCount();
            params.weight = 1;
            for (int i = 0; i < itemCount; i++) {
                addView(buildRipple(i), params);
            }
        }

        @Override
        public void onInvalidated() {
            removeAllViews();
        }
    };


    private RippleButton buildRipple(final int pos) {
        RippleButton ripple = new RippleButton(getContext());
        ripple.setGravity(Gravity.CENTER);
        ripple.setRippleColor(getResources().getColor(android.R.color.darker_gray));
        ripple.setTextSize(TypedValue.COMPLEX_UNIT_PX, 20);
        ripple.setPadding(0, 20, 0, 20);
        ripple.setTextColor(mNormalItemColor);
        ripple.setText(mAdapter.getText(pos));

        ripple.setCompoundDrawablesWithIntrinsicBounds(null, mAdapter.getDrawable(pos),
                null, null);
        ripple.setOnBeforeClickedListener(new RippleButton.OnBeforeClickedListener() {
            @Override
            public void onBeforeClicked(View view) {
                if (mItemCheckedListener != null && pos != mCheckedPosition) {
                    mItemCheckedListener.onItemChecked(pos, getChildAt(pos));
                }
                itemChecked(pos);
            }
        });

        return ripple;
    }

    private void itemChecked(int pos) {
        mCheckedPosition = pos;
        int itemCount = getChildCount();
        RippleButton rippleButton;
        Drawable drawable;
        for (int i = 0; i < itemCount; i++) {
            rippleButton = (RippleButton) getChildAt(i);
            /**
             * getCompoundDrawables()方法.
             * Returns drawables for the left, top, right, and bottom borders.
             * 该方法返回包含控件左,上,右,下四个位置的Drawable的数组
             */
            rippleButton.cancel();
            drawable = rippleButton.getCompoundDrawables()[1];
            if (i == mCheckedPosition) {
                rippleButton.setTextSize(TypedValue.COMPLEX_UNIT_PX, 30);
                rippleButton.setTextColor(mCheckedItemColor);
                if (null != drawable) {
                    drawable.setColorFilter(new PorterDuffColorFilter(mCheckedItemColor,
                            PorterDuff.Mode.SRC_IN));
                }
                continue;
            }
            rippleButton.setTextSize(TypedValue.COMPLEX_UNIT_PX, 20);
            rippleButton.setTextColor(mNormalItemColor);
            if (drawable != null) {
                drawable.clearColorFilter();
            }

        }
    }

    public interface ItemCheckedListener {
        void onItemChecked(int position, View view);
    }


}
