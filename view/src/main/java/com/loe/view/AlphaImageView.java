package com.loe.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

@SuppressLint("AppCompatCustomView")
public class AlphaImageView extends ImageView
{
    public AlphaImageView(Context context)
    {
        super(context);
        init(context);
    }

    public AlphaImageView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context);
    }

    public AlphaImageView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void  init(Context context)
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
