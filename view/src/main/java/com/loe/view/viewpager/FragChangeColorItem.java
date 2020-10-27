package com.loe.view.viewpager;

import android.graphics.PorterDuff;
import android.support.v4.app.Fragment;;

import com.loe.view.ImageTextView;

@SuppressWarnings("rawtypes")
public class FragChangeColorItem extends ViewPagerItem
{
    private ImageTextView textView;

    private int unSelectColor;
    private int selectColor;

    public FragChangeColorItem(Class cls, ImageTextView textView, int color0, int color1)
    {
        super(cls);

        this.view = textView;
        this.textView = textView;

        unSelectColor = color0;
        selectColor = color1;
    }

    /**
     * 当点击了消息tab时，改变控件的图片和文字颜色
     */
    @Override
    public void selectIn()
    {
        textView.setTextColor(selectColor);
        textView.getCompoundDrawables()[1].mutate().setColorFilter(selectColor, PorterDuff.Mode.SRC_ATOP);
    }

    /**
     * 当取消了消息tab时，恢复控件的图片和文字颜色
     */
    @Override
    public void selectMove()
    {
        textView.setTextColor(unSelectColor);
        textView.getCompoundDrawables()[1].mutate().setColorFilter(unSelectColor, PorterDuff.Mode.SRC_ATOP);
    }
}
