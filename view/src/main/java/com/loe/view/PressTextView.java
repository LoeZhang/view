package com.loe.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.TextView;

@SuppressLint("AppCompatCustomView")
public class PressTextView extends TextView
{
    public PressTextView(Context context)
    {
        super(context);
        init();
    }

    public PressTextView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public PressTextView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PressTextView(Context context, AttributeSet attrs, int defStyleAttr, int
            defStyleRes)
    {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init()
    {
        setClickable(true);
    }

    @Override
    protected void dispatchSetPressed(boolean pressed)
    {
        if(pressed)
        {
            setScaleX(1.05f);
            setScaleY(1.05f);
        }else
        {
            setScaleX(1f);
            setScaleY(1f);
        }
        super.dispatchSetPressed(pressed);
    }
}