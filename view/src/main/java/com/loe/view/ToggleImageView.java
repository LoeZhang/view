package com.loe.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

@SuppressLint("AppCompatCustomView")
public class ToggleImageView extends ImageView
{
    private boolean isSelect;

    private boolean clickable;

    private int srcUnSelect;
    private int srcSelect;

    private OnSelectChangeListener onChangeListener;

    public ToggleImageView(Context context)
    {
        this(context, null);
        init(context, null);
    }

    public ToggleImageView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
        init(context, attrs);
    }

    public ToggleImageView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs)
    {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ToggleImageView);

        isSelect = array.getBoolean(R.styleable.ToggleImageView_toggle_isSelect, false);
        clickable = array.getBoolean(R.styleable.ToggleImageView_toggle_clickable, true);
        srcUnSelect = array.getResourceId(R.styleable.ToggleImageView_toggle_srcUnSelect, 0);
        srcSelect = array.getResourceId(R.styleable.ToggleImageView_toggle_srcSelect, 0);
        resetSelect();

        setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(clickable)
                {
                    setSelect(!isSelect);
                    if (onChangeListener != null)
                    {
                        onChangeListener.onChanged(isSelect);
                    }
                }
            }
        });
    }

    private void resetSelect()
    {
        setImageResource(isSelect ? srcSelect : srcUnSelect);
    }

    public boolean isSelect()
    {
        return isSelect;
    }

    public void setSelect(boolean select)
    {
        isSelect = select;
        resetSelect();
    }

    public boolean isToggleClickable()
    {
        return clickable;
    }

    public void setToggleClickable(boolean clickable)
    {
        this.clickable = clickable;
    }

    public int getSrcUnSelect()
    {
        return srcUnSelect;
    }

    public void setSrcUnSelect(int srcUnSelect)
    {
        this.srcUnSelect = srcUnSelect;
        resetSelect();
    }

    public int getSrcSelect()
    {
        return srcSelect;
    }

    public void setSrcSelect(int srcSelect)
    {
        this.srcSelect = srcSelect;
        resetSelect();
    }

    public void setOnChangeListener(OnSelectChangeListener onChangeListener)
    {
        this.onChangeListener = onChangeListener;
    }

    public interface OnSelectChangeListener
    {
        void onChanged(boolean isSelect);
    }
}