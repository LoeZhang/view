package com.loe.view.viewpager;

import android.support.v4.app.Fragment;
import android.widget.TextView;

@SuppressWarnings("rawtypes")
public class FragTextItem extends ViewPagerItem
{
    private TextView textView;

    private int unSelectColor;
    private int selectColor;

    public FragTextItem(Class cls, TextView textView, int color0, int color1)
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
//        btn_text.setTypeface(Typeface.DEFAULT_BOLD);

    }

    /**
     * 当取消了消息tab时，恢复控件的图片和文字颜色
     */
    @Override
    public void selectMove()
    {
        textView.setTextColor(unSelectColor);
//        btn_text.setTypeface(Typeface.DEFAULT);
    }
}
