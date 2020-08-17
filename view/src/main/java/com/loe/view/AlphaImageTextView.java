package com.loe.view;

import android.content.Context;
import android.util.AttributeSet;

public class AlphaImageTextView extends ImageTextView
{
    public AlphaImageTextView(Context context)
    {
        super(context);
        init();
    }

    public AlphaImageTextView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public AlphaImageTextView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }

    public AlphaImageTextView(Context context, AttributeSet attrs, int defStyleAttr, int
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
        setAlpha(pressed ? 0.6f : 1);
        super.dispatchSetPressed(pressed);
    }

    @Override
    public void setEnabled(boolean enabled)
    {
        setAlpha(enabled ? 1 : 0.6f);
        super.setEnabled(enabled);
    }
}
