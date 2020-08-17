package com.loe.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * 自适应比例imageview
 */
@SuppressLint("AppCompatCustomView")
public class AutoImageView extends ImageView
{

    protected Context mContext;

    public AutoImageView(Context context)
    {
        super(context);
        mContext = context;
    }

    public AutoImageView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        mContext = context;
    }

    public AutoImageView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        mContext = context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        if (getDrawable() == null)
        {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }
        // 计算出ImageView的宽度
        int viewWidth = MeasureSpec.getSize(widthMeasureSpec);
        // 根据图片长宽比例算出ImageView的高度
        int viewHeight = viewWidth * getDrawable().getIntrinsicHeight() / getDrawable()
                .getIntrinsicWidth();
        // 将计算出的宽度和高度设置为图片显示的大小
        setMeasuredDimension(viewWidth, viewHeight);
    }
}
