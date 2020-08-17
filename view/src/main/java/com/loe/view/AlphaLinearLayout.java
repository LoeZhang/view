package com.loe.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class AlphaLinearLayout extends LinearLayout
{
    public AlphaLinearLayout(Context context)
    {
        super(context);
        setClickable(true);
    }

    public AlphaLinearLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        setClickable(true);
    }

    public AlphaLinearLayout(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        setClickable(true);
    }

    @Override
    protected void dispatchSetPressed(boolean pressed)
    {
        setAlpha(pressed ? 0.7f : 1);
        super.dispatchSetPressed(pressed);
    }

    @Override
    public void setEnabled(boolean enabled)
    {
        setAlpha(enabled ? 1 : 0.7f);
        super.setEnabled(enabled);
    }
}
