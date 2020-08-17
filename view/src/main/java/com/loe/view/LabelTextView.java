package com.loe.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

@SuppressLint("AppCompatCustomView")
public class LabelTextView extends TextView
{
    protected String title = "";
    protected String tail = "";
    protected String text = "";

    protected boolean isAutoVisible;

    public LabelTextView(Context context)
    {
        super(context);
    }

    public LabelTextView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context, context.obtainStyledAttributes(attrs, R.styleable.LabelTextView));
    }

    public LabelTextView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init(context, context.obtainStyledAttributes(attrs, R.styleable.LabelTextView, defStyleAttr, 0));
    }

    @SuppressLint("NewApi")
    public LabelTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
    {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, context.obtainStyledAttributes(attrs, R.styleable.LabelTextView, defStyleAttr, defStyleRes));
    }

    private void init(Context context, TypedArray typedArray)
    {
        text = super.getText().toString();
        setAutoVisible(typedArray.getBoolean(R.styleable.LabelTextView_label_auto_visible,false));
        setTitle(typedArray.getString(R.styleable.LabelTextView_label_title));
        setTail(typedArray.getString(R.styleable.LabelTextView_label_tail));
    }

    public void setTitle(String title)
    {
        this.title = title == null ? "" : title;
        notifyText();
    }

    public String getTitle()
    {
        return title;
    }

    public void setTail(String tail)
    {
        this.tail = tail == null ? "" : tail;
        notifyText();
    }

    public String getTail()
    {
        return tail;
    }

    public void setText(String text)
    {
        this.text = text;
        notifyText();
    }

    public String getText()
    {
        return text;
    }

    public boolean isAutoVisible()
    {
        return isAutoVisible;
    }

    public void setAutoVisible(boolean autoVisible)
    {
        isAutoVisible = autoVisible;
        notifyVisible(text);
    }

    protected void notifyVisible(String s)
    {
        if (isAutoVisible)
        {
            setVisibility(s.isEmpty() ? GONE : VISIBLE);
        }
    }

    private void notifyText()
    {
        notifyVisible(text);
        super.setText(title + text + tail);
    }
}