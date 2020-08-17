package com.loe.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.TextView;

@SuppressLint("AppCompatCustomView")
public class AlphaTextView extends TextView
{
    public AlphaTextView(Context context)
    {
        super(context);
        init();
    }

    public AlphaTextView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public AlphaTextView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public AlphaTextView(Context context, AttributeSet attrs, int defStyleAttr, int
            defStyleRes)
    {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init()
    {
        setClickable(true);
        setTextColor(getTextColors().getDefaultColor());
    }

    @Override
    public void setTextColor(int color)
    {
        int[][] states = new int[3][];
        states[0] = new int[]{android.R.attr.state_enabled, -android.R.attr.state_pressed};
        states[1] = new int[]{android.R.attr.state_enabled, android.R.attr.state_pressed};
        states[2] = new int[]{-android.R.attr.state_enabled};
        int alphaColor = (0x99 << 030) + (color & 0xFFFFFF);
        setTextColor(new ColorStateList(states, new int[]{color, alphaColor, alphaColor}));
    }
}