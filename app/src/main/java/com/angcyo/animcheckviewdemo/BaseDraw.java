package com.angcyo.animcheckviewdemo;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Copyright (C) 2016,深圳市红鸟网络科技股份有限公司 All rights reserved.
 * 项目名称：
 * 类的描述：
 * 创建人员：Robi
 * 创建时间：2018/04/19 09:59
 * 修改人员：Robi
 * 修改时间：2018/04/19 09:59
 * 修改备注：
 * Version: 1.0.0
 */
public abstract class BaseDraw {
    public Paint mBasePaint;
    protected View mView;

    public BaseDraw(View view) {
        this(view, null);
    }

    /**
     * 请注意, 需要在继承类 中手动调用 {@link #initAttribute(AttributeSet)} 方法
     */
    public BaseDraw(View view, AttributeSet attr) {
        mView = view;
        mBasePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBasePaint.setFilterBitmap(true);
        mBasePaint.setStyle(Paint.Style.FILL);
        mBasePaint.setTextSize(12 * density());
        mBasePaint.setColor(getBaseColor());

        //initAttribute(attr);//父类当中调用此方法初始化子类的成员, 会导致被覆盖的BUG
        //所以此方法, 请在子类当中触发
    }

    protected int getBaseColor() {
//        if (isInEditMode()) {
//            return getColor(R.color.base_dark_red);
//        } else {
//            return SkinHelper.getSkin().getThemeSubColor();
//        }
        return -1;
    }

    protected float density() {
        return getContext().getResources().getDisplayMetrics().density;
    }

    protected Context getContext() {
        return mView.getContext();
    }

    protected Resources getResources() {
        return getContext().getResources();
    }

    protected int getColor(@ColorRes int id) {
        return ContextCompat.getColor(getContext(), id);
    }

    protected boolean isInEditMode() {
        return mView.isInEditMode();
    }

    protected void postInvalidate() {
        mView.postInvalidate();
    }

    protected void postInvalidateOnAnimation() {
        ViewCompat.postInvalidateOnAnimation(mView);
    }

    protected void scrollTo(int x, int y) {
        mView.scrollTo(x, y);
    }

    protected int getPaddingBottom() {
        return mView.getPaddingBottom();
    }

    protected int getPaddingRight() {
        return mView.getPaddingRight();
    }

    protected int getPaddingLeft() {
        return mView.getPaddingLeft();
    }

    protected int getPaddingTop() {
        return mView.getPaddingTop();
    }

    protected int getViewDrawWidth() {
        return getViewWidth() - getPaddingLeft() - getPaddingRight();
    }

    protected int getViewDrawHeight() {
        return getViewHeight() - getPaddingTop() - getPaddingBottom();
    }

    protected int getViewWidth() {
        return mView.getMeasuredWidth();
    }

    protected int getViewHeight() {
        return mView.getMeasuredHeight();
    }

    protected void requestLayout() {
        mView.requestLayout();
    }

    protected int getChildCount() {
        return ((ViewGroup) mView).getChildCount();
    }

    protected View getChildAt(int index) {
        return ((ViewGroup) mView).getChildAt(index);
    }

    public void onLayout(boolean changed, int left, int top, int right, int bottom) {
    }

    public void onSizeChanged(int w, int h, int oldw, int oldh) {
    }

    public void onDetachedFromWindow() {
    }

    public void onAttachedToWindow() {

    }

    public void draw(@NonNull Canvas canvas) {

    }

    public void onDraw(@NonNull Canvas canvas) {

    }

    protected TypedArray obtainStyledAttributes(AttributeSet set, int[] attrs) {
        return getContext().obtainStyledAttributes(set, attrs);
    }

    protected abstract void initAttribute(AttributeSet attr);

    public int measureDrawWidth() {
        return mView.getMeasuredWidth();
    }

    public int measureDrawHeight() {
        return (int) (mBasePaint.descent() - mBasePaint.ascent());
    }

    public int[] measureDraw(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = View.MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = View.MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = View.MeasureSpec.getMode(heightMeasureSpec);

        if (widthMode == View.MeasureSpec.AT_MOST) {
            //wrap_content
            widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(measureDrawWidth(), View.MeasureSpec.EXACTLY);
        }

        if (heightMode == View.MeasureSpec.AT_MOST) {
            heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(measureDrawHeight(), View.MeasureSpec.EXACTLY);
        }

        return new int[]{widthMeasureSpec, heightMeasureSpec};
    }

    public int drawCenterX() {
        return getPaddingLeft() + getViewDrawWidth() / 2;
    }

    public int drawCenterY() {
        return getPaddingTop() + getViewDrawHeight() / 2;
    }

    /**
     * 可以容纳绘制的最大正方形, 不考虑画笔大小
     */
    public Rect maxDrawSquare() {
        int size = Math.min(getViewDrawWidth(), getViewDrawHeight());
        Rect rect = new Rect();
        rect.set(getPaddingLeft(), getPaddingTop(),
                getPaddingLeft() + size, getPaddingTop() + size);
        return rect;
    }

    public RectF maxDrawSquareF() {
        int size = Math.min(getViewDrawWidth(), getViewDrawHeight());
        RectF rectF = new RectF();
        rectF.set(getPaddingLeft(), getPaddingTop(),
                getPaddingLeft() + size, getPaddingTop() + size);
        return rectF;
    }
}
