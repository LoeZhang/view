package com.loe.view;

import android.content.Context;
import android.util.AttributeSet;

public class PressImageTextView extends ImageTextView
{
    public PressImageTextView(Context context)
    {
        super(context);
        init();
    }

    public PressImageTextView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public PressImageTextView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }

    public PressImageTextView(Context context, AttributeSet attrs, int defStyleAttr, int
            defStyleRes)
    {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void  init()
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

    @Override
    public void setEnabled(boolean enabled)
    {
        setAlpha(enabled ? 1 : 0.6f);
        super.setEnabled(enabled);
    }
}
