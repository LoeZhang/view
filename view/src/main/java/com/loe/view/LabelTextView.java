package com.loe.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.Html;
import android.util.AttributeSet;
import android.widget.TextView;

@SuppressLint("AppCompatCustomView")
public class LabelTextView extends TextView
{
    protected String title = "";
    protected String tail = "";
    protected String label = "";

    private String titleColor = "";
    private String tailColor = "";

    private boolean isLock = false;

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
        isLock = true;
        label = super.getText().toString();

        setAutoVisible(typedArray.getBoolean(R.styleable.LabelTextView_label_auto_visible, false));
        setTitle(typedArray.getString(R.styleable.LabelTextView_label_title));
        setLabel(label.isEmpty() ? typedArray.getString(R.styleable.LabelTextView_label_label) : label);
        setTail(typedArray.getString(R.styleable.LabelTextView_label_tail));

        setTitleColor(typedArray.getColor(R.styleable.LabelTextView_label_title_color, getTextColors().getDefaultColor()));
        setTailColor(typedArray.getColor(R.styleable.LabelTextView_label_tail_color, getTextColors().getDefaultColor()));


        isLock = false;
        notifyText();
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

    public void setLabel(String label)
    {
        this.label = label == null ? "" : label;
        notifyText();
    }

    public String getLabel()
    {
        return label;
    }

    public boolean isAutoVisible()
    {
        return isAutoVisible;
    }

    public void setAutoVisible(boolean autoVisible)
    {
        isAutoVisible = autoVisible;
        notifyVisible(label);
    }

    public void setTitleColor(int color)
    {
        this.titleColor = String.format("#%06X", 0xFFFFFF & color);
        notifyText();
    }

    public void setTailColor(int color)
    {
        this.tailColor = String.format("#%06X", 0xFFFFFF & color);
        notifyText();
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
        if (isLock)
        {
            return;
        }

        notifyVisible(label);
        String titleF = "<font color='" + titleColor + "'>" + title + "</font>";
        String tailF = "<font color='" + tailColor + "'>" + tail + "</font>";

        super.setText(Html.fromHtml(titleF + label + tailF));
    }
}