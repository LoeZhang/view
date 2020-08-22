package com.loe.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

@SuppressLint("AppCompatCustomView")
public class StatusView extends View
{
    public StatusView(Context context)
    {
        super(context);
    }

    public StatusView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public StatusView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public StatusView(Context context, AttributeSet attrs, int defStyleAttr, int
            defStyleRes)
    {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && resourceId > 0)
        {
            int heightSize = getResources().getDimensionPixelSize(resourceId);
            setMeasuredDimension(widthSize, heightSize);
        }else
        {
            setMeasuredDimension(widthSize, 0);
        }
    }
}