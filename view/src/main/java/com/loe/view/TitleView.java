package com.loe.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class TitleView extends FrameLayout
{
    public TitleView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context, context.obtainStyledAttributes(attrs, R.styleable.TitleView));
    }

    public TitleView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init(context, context.obtainStyledAttributes(attrs, R.styleable.TitleView, defStyleAttr, 0));
    }

    @SuppressLint("NewApi")
    public TitleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
    {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, context.obtainStyledAttributes(attrs, R.styleable.TitleView, defStyleAttr, defStyleRes));
    }

    private ImageView imageLeft;
    private TextView textTitle;
    private TextView textRight;
    private ImageView imageRight;

    private void init(final Context context, TypedArray typedArray)
    {
        FrameLayout layout = (FrameLayout) LayoutInflater.from(context).inflate(R.layout.title_layout, this);

        boolean leftVisible = typedArray.getBoolean(R.styleable.TitleView_title_left_visible, true);
        imageLeft = (ImageView) layout.findViewById(R.id.imageLeft);
        Drawable leftImage = typedArray.getDrawable(R.styleable.TitleView_title_left_image);
        if (leftImage != null)
        {
            imageLeft.setImageDrawable(leftImage);
        }
        if (leftVisible)
        {
            setBackListener(new OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    try
                    {
                        ((Activity) context).onBackPressed();
                    } catch (Exception e)
                    {
                    }
                }
            });
        }
        setLeftVisible(leftVisible);

        textTitle = (TextView) layout.findViewById(R.id.textTitle);
        setTitle(typedArray.getString(R.styleable.TitleView_title_text));

        textRight = (TextView) layout.findViewById(R.id.textRight);
        setRight(typedArray.getString(R.styleable.TitleView_title_right_text));

        imageRight = (ImageView) layout.findViewById(R.id.imageRight);
        Drawable rightImage = typedArray.getDrawable(R.styleable.TitleView_title_right_image);
        if (rightImage != null)
        {
            textRight.setVisibility(GONE);
            imageRight.setVisibility(VISIBLE);
            imageRight.setImageDrawable(rightImage);
        }

        // color
        int color = typedArray.getColor(R.styleable.TitleView_title_color, -22);
        if (color != -22)
        {
            setColor(color);
        }
    }

    public void setColor(int color)
    {
        imageLeft.setColorFilter(color);
        textTitle.setTextColor(color);
        textRight.setTextColor(color);
        imageRight.setColorFilter(color);
    }

    /**
     * 设置返回图片
     */
    public void setLeftImage(int resId)
    {
        imageLeft.setImageResource(resId);
    }

    /**
     * 设置返回可见
     */
    public void setLeftVisible(boolean visible)
    {
        imageLeft.setVisibility(visible ? VISIBLE : GONE);
    }

    /**
     * 设置返回监听
     */
    public void setBackListener(OnClickListener listener)
    {
        imageLeft.setOnClickListener(listener);
    }

    /**
     * 设置标题文字
     */
    public void setTitle(String title)
    {
        textTitle.setText(title);
    }

    /**
     * 设置右边文字
     */
    public void setRight(String right)
    {
        textRight.setText(right);
        if (right != null && !right.isEmpty())
        {
            textRight.setVisibility(VISIBLE);
            if (imageRight != null)
            {
                imageRight.setVisibility(GONE);
            }
        }
    }

    /**
     * 设置右边图片
     */
    public void setRightImage(int resId)
    {
        imageRight.setImageResource(resId);
        textRight.setVisibility(GONE);
        imageRight.setVisibility(VISIBLE);
    }

    private long rightDelay = 0L;

    /**
     * 设置右边允许
     */
    public void setRightEnable(boolean enable)
    {
        textRight.setEnabled(enable);
        textRight.setAlpha(enable ? 1f : 0.6f);
    }

    /**
     * 设置右边图片允许
     */
    public void setRightImageEnable(boolean enable)
    {
        imageRight.setEnabled(enable);
        imageRight.setAlpha(enable ? 1f : 0.6f);
    }

    /**
     * 设置右边监听
     */
    public void setRightListener(final OnClickListener listener)
    {
        OnClickListener rightListener = new OnClickListener()
        {
            @Override
            public void onClick(final View view)
            {
                if (System.currentTimeMillis() - rightDelay > 1200)
                {
                    listener.onClick(view);
                    rightDelay = System.currentTimeMillis();
                }
            }
        };
        textRight.setOnClickListener(rightListener);
        imageRight.setOnClickListener(rightListener);
    }
}