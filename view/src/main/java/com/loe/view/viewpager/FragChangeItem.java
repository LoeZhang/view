package com.loe.view.viewpager;

import com.loe.view.ImageTextView;

@SuppressWarnings("rawtypes")
public class FragChangeItem extends ViewPagerItem
{
    private ImageTextView textView;

    private int res0,res1;
    private int unSelectColor;
    private int selectColor;

    public FragChangeItem(Class cls, ImageTextView textView, int res0, int res1, int color0,
                          int color1)
    {
        super(cls);
        this.view = textView;
        this.textView = textView;

        this.res0 = res0;
        this.res1 = res1;
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
        textView.setTopImage(res1);
    }

    /**
     * 当取消了消息tab时，恢复控件的图片和文字颜色
     */
    @Override
    public void selectMove()
    {
        textView.setTextColor(unSelectColor);
        textView.setTopImage(res0);
    }
}
