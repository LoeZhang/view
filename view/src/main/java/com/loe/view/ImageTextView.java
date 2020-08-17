package com.loe.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.TextView;

@SuppressLint("AppCompatCustomView")
public class ImageTextView extends TextView
{
    public ImageTextView(Context context)
    {
        super(context);
    }

    public ImageTextView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context, context.obtainStyledAttributes(attrs, R.styleable.ImageTextView));
    }

    public ImageTextView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init(context, context.obtainStyledAttributes(attrs, R.styleable.ImageTextView, defStyleAttr, 0));
    }

    @SuppressLint("NewApi")
    public ImageTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
    {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, context.obtainStyledAttributes(attrs, R.styleable.ImageTextView, defStyleAttr, defStyleRes));
    }

    protected boolean isAutoVisible;

    private int top;
    private int width;
    private int height;
    private int padding;
    private Drawable[] ds;

    private void init(Context context, TypedArray typedArray)
    {
        int def = dp_px(14);
        setAutoVisible(typedArray.getBoolean(R.styleable.ImageTextView_image_auto_visible,false));
        top = (int) typedArray.getDimension(R.styleable.ImageTextView_image_top, 0);
        width = (int) typedArray.getDimension(R.styleable.ImageTextView_image_width, def);
        height = (int) typedArray.getDimension(R.styleable.ImageTextView_image_height, def);
        padding = (int) typedArray.getDimension(R.styleable.ImageTextView_image_padding, 0);

        ds = getCompoundDrawables();
        for (Drawable d : ds)
        {
            if (d != null)
            {
                d.setBounds(0, top, width, height + top);
            }
        }
        setCompoundDrawables(ds[0], ds[1], ds[2], ds[3]);
        setCompoundDrawablePadding(padding);
    }

    public void setRightImage(int resId)
    {
        Drawable d = getResources().getDrawable(resId);
        d.setBounds(0, top, width, height + top);
        ds[2] = d;
        setCompoundDrawables(ds[0], ds[1], ds[2], ds[3]);
        setCompoundDrawablePadding(padding);
    }

    public void setLeftImage(int resId)
    {
        Drawable d = getResources().getDrawable(resId);
        d.setBounds(0, top, width, height + top);
        ds[0] = d;
        setCompoundDrawables(ds[0], ds[1], ds[2], ds[3]);
        setCompoundDrawablePadding(padding);
    }

    public void setTopImage(int resId)
    {
        Drawable d = getResources().getDrawable(resId);
        d.setBounds(0, top, width, height + top);
        ds[1] = d;
        setCompoundDrawables(ds[0], ds[1], ds[2], ds[3]);
        setCompoundDrawablePadding(padding);
    }

    public void setBottomImage(int resId)
    {
        Drawable d = getResources().getDrawable(resId);
        d.setBounds(0, top, width, height + top);
        ds[3] = d;
        setCompoundDrawables(ds[0], ds[1], ds[2], ds[3]);
        setCompoundDrawablePadding(padding);
    }

    public boolean isAutoVisible()
    {
        return isAutoVisible;
    }

    public void setAutoVisible(boolean autoVisible)
    {
        isAutoVisible = autoVisible;
        if (isAutoVisible)
        {
            setVisibility(getText().toString().isEmpty() ? GONE : VISIBLE);
            addTextChangedListener(new TextWatcher()
            {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after)
                {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count)
                {
                }

                @Override
                public void afterTextChanged(Editable s)
                {
                    if(isAutoVisible) setVisibility(s.toString().isEmpty() ? GONE : VISIBLE);
                }
            });
        }
    }

    public int dp_px(float dpValue)
    {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}