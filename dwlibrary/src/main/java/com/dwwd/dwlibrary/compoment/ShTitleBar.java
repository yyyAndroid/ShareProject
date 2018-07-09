package com.dwwd.dwlibrary.compoment;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;



/**
 * Created by abe on 2018/6/16.
 * titlebar
 */

public class ShTitleBar extends ViewGroup{
    private TextView mTitle;
    public ShTitleBar(Context context) {
        super(context);

        setBackgroundColor(0xffaaaaaa);

        mTitle = new TextView(context);
        mTitle.setText("标题");
        mTitle.setTextColor(0xff333333);
        addView(mTitle);
    }

    /**
     * View的测量
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int width= MeasureSpec.getSize(widthMeasureSpec);
        int height = WaUI.getDensityDimen(getContext(), 35);

        mTitle.measure(0, 0);

        setMeasuredDimension(width, height);
    }

    /**
     * View 摆放
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int offsetX = (getMeasuredWidth() - mTitle.getMeasuredWidth()) / 2;
        int offsetY = (getMeasuredHeight() - mTitle.getMeasuredHeight()) / 2;

        WaUI.layoutViewAtPos(mTitle, offsetX, offsetY);
    }
}
