package com.loe.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;

public class LetterBarView extends View
{
    private HashMap<String, Integer> map;
    // 字母列表颜色
    int mLetterColor = 0xff666666;
    // 被选中的字母颜色
    int mSelectLetterColor = 0xffff0000;
    // 字母字体大小
    float mLetterSize = 35;
    //是否是粗体字母
    boolean mIsBoldface;
    //背景
    Drawable mBackground;
    //选中时的背景
    Drawable mSelectBackground;

    private int mWidth;//宽度
    private int mHeight;//去除padding后的高度
    private int mChoose = -1;// 选中的字母是第几个
    private Paint mPaint;//画笔0
    private TextView mTextDialog;//可以设置一个显示当前索引字母的对话框
    private String mChars = "ABCDFGHJKLMNPQRSTWXYZ";//默认字符
    private OnLetterChangedListener mLetterChangedListener;// 触摸字母改变事件

    public LetterBarView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    public LetterBarView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context, attrs);
    }

    public LetterBarView(Context context)
    {
        super(context);
        init(context, null);
    }

    private void init(Context context, AttributeSet attrs)
    {
        map = new HashMap<>();
        if (context != null && attrs != null)
        {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LetterBarView, 0, 0);
            mLetterSize = a.getDimension(R.styleable.LetterBarView_letter_size, dp_px(14));
            mLetterColor = a.getColor(R.styleable.LetterBarView_letter_color, mLetterColor);
            mSelectLetterColor = a.getColor(R.styleable.LetterBarView_letter_select_color, mSelectLetterColor);
            mSelectBackground = a.getDrawable(R.styleable.LetterBarView_letter_select_background);
            mIsBoldface = a.getBoolean(R.styleable.LetterBarView_letter_is_bold, false);
            String chars = a.getString(R.styleable.LetterBarView_letter_chars);
            mChars = chars != null ? chars : mChars;
            a.recycle();
        }
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTypeface(mIsBoldface ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT);
        mPaint.setTextSize(mLetterSize);
        mPaint.setAntiAlias(true);
        setClickable(true);
        mBackground = getBackground();
    }

    public void setChars(String chars)
    {
        this.mChars = chars;
    }

    public void setTextDialog(TextView textDialog)
    {
        this.mTextDialog = textDialog;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeight = h - getPaddingTop() - getPaddingBottom();
        mWidth = w;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        for (int i = 0, len = mChars.length(); i < len; i++)
        {
            String letter = mChars.substring(i, i + 1);
            float letterWidth = mPaint.measureText(letter);
            mPaint.setColor(i == mChoose ? mSelectLetterColor : mLetterColor);
            float xPos = (mWidth - letterWidth) / 2;
            float yPos = mHeight / mChars.length() * (i + 0.5f) + getPaddingTop();
            canvas.drawText(letter, xPos, yPos, mPaint);
        }
        super.onDraw(canvas);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public boolean dispatchTouchEvent(MotionEvent event)
    {
        float y = event.getY();// 点击y坐标
        int oldChoose = mChoose;
        if (y < getPaddingTop())
        {
            mChoose = 0;
        } else if (y > mHeight + getPaddingTop())
        {
            mChoose = mChars.length() - 1;
        } else
        {
            // 点击y坐标所占总高度的比例*b数组的长度就等于点击b中的个数.
            mChoose = (int) ((y - getPaddingTop()) / mHeight * mChars.length());
        }
        switch (event.getAction())
        {
            case MotionEvent.ACTION_UP:
                mChoose = -1;//
                if (mTextDialog != null)
                {
                    mTextDialog.setVisibility(View.INVISIBLE);
                }
                setBackground(mBackground);
            default:
                if (oldChoose != mChoose && mChoose != -1)
                {
                    if (mLetterChangedListener != null)
                    {
                        mLetterChangedListener.onChanged(mChars.substring(mChoose, mChoose + 1)
                                , mChoose);
                    }
                    if (mTextDialog != null)
                    {
                        mTextDialog.setText(mChars.substring(mChoose, mChoose + 1));
                        mTextDialog.setVisibility(View.VISIBLE);
                    }
                    setBackground(mSelectBackground);
                }
        }
        invalidate();
        return super.dispatchTouchEvent(event);
    }

    public void setTitle(String c, int index)
    {
        map.put(c, index);
    }

    public Integer getTitleIndex(String c)
    {
        return map.get(c);
    }

    private static int dp_px(float dpValue)
    {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    //设置接口
    public void setOnLetterChangedListener(OnLetterChangedListener letterChangedListener)
    {
        mLetterChangedListener = letterChangedListener;
    }

    /**
     * 触摸选中的字母发生改变的接口
     */
    public interface OnLetterChangedListener
    {
        void onChanged(String s, int position);
    }
}
