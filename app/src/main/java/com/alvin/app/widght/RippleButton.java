package com.alvin.app.widght;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.alvin.app.R;


/**
 * @Title RippleButton
 * @Description:
 * @Author: alvin
 * @Date: 2016/10/26.15:10
 * @E-mail: 49467306@qq.com
 */
public class RippleButton extends AppCompatTextView {
    /**
     * 波纹画笔
     */
    private Paint mPaint;
    /**
     * 波纹变化幅度
     */
    private int mStepSize;
    /**
     * 波纹从多少开始变化
     */
    private int mMinRadius = 0;
    /**
     * 波纹当前大小
     */
    private int mCurrentRadius = 0;
    /**
     * 波纹最大值
     */
    private int mMaxRadius;
    /**
     * 波纹中心坐标
     */
    private int mCenterX, mCenterY;
    /**
     * 判断是否正在进行动画
     */
    private boolean isAnimating;

    /**
     * 自定义点击之前的监听事件
     */
    private OnBeforeClickedListener mListener;

    public RippleButton(Context context) {
        this(context, null);
    }

    public RippleButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RippleButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        resolveAttrs(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mMaxRadius = Math.max(getMeasuredWidth(), getMeasuredHeight()) / 2;
        mCenterX = getMeasuredWidth() / 2;
        mCenterY = getMeasuredHeight() / 2;
        mStepSize = mMaxRadius / 20;
    }

    private void resolveAttrs(Context context, AttributeSet attrs, int defStyle) {
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.RippleButton, defStyle, 0);
        mPaint.setColor(typedArray.getColor(R.styleable.RippleButton_rippleColor, Color.GREEN));
        typedArray.recycle();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (null != mListener)
                mListener.onBeforeClicked(this);
            mCurrentRadius = mMinRadius;
            isAnimating = true;
            postInvalidate();
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!isAnimating) {
            super.onDraw(canvas);
            return;
        }
        /**
         * 如果当前波纹半径大于最大半径则停止重绘波纹
         * 并执行onclick事件
         */
        if (isAnimating && mCurrentRadius > mMaxRadius) {
            isAnimating = false;
            mCurrentRadius = mMinRadius;
            performClick();
            super.onDraw(canvas);
            return;
        }
        /**
         * 当前波纹小于最大半径,则进行重绘
         */
        mCurrentRadius = mCurrentRadius + mStepSize;
        canvas.drawCircle(mCenterX, mCenterY, mCurrentRadius, mPaint);
        super.onDraw(canvas);
        postInvalidate();
    }

    public void setOnBeforeClickedListener(OnBeforeClickedListener li) {
        mListener = li;
    }

    public interface OnBeforeClickedListener {
        void onBeforeClicked(View view);
    }

    public void setRippleColor(int color) {
        mPaint.setColor(color);
    }

    public void cancel() {
        isAnimating = false;
    }
}
