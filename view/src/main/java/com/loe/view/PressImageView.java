package com.loe.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

@SuppressLint("AppCompatCustomView")
public class PressImageView extends ImageView
{
    public PressImageView(Context context)
    {
        super(context);
        init(context);
    }

    public PressImageView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context);
    }

    public PressImageView(Context context, AttributeSet attrs, int defStyleAttr)
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
