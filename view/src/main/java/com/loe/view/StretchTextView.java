package com.loe.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;

@SuppressLint("AppCompatCustomView")
public class StretchTextView extends TextView
{
    private Context context;
    private float viewWidth;
    private float viewHight;
    private float oneWidth;
    private float oneHight;
    private int num;
    private Paint paint;
    private String[] texts;

    public StretchTextView(Context context)
    {
        super(context);
        this.context = context;
        setText(getText().toString());
        super.setText("");
        init();
    }

    public StretchTextView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        this.context = context;
        setText(getText().toString());
        super.setText("");
        init();
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        init();
        drawText(canvas);
    }

    private void drawText(Canvas canvas)
    {
        if (num == 0)
        {
            return;
        }
        float lineWidth = viewWidth / num;
        float drawY = (viewHight - oneHight) / 2 + oneHight*0.91f;
        for (int i = 0; i < num; i++)
        {
            if (i == num - 1)
            {// 最后一个
                canvas.drawText(texts[i], viewWidth - oneWidth, drawY, paint);
            }
            else if (i != 0 && i != num - 1)
            {//
                canvas.drawText(texts[i], i * lineWidth + lineWidth / 2 - oneWidth / 2, drawY, paint);
            }
            else
            {// 第一个
                canvas.drawText(texts[i], i, drawY, paint);
            }
        }
    }

    private void init()
    {
        this.setPadding(0, 0, 0, 0);
        viewWidth = this.getWidth();
        viewHight = this.getHeight();
    }

    public void setText(String str)
    {
        if (str == null)
        {
            return;
        }
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(getCurrentTextColor());
        paint.setTextSize(getTextSize());
        num = str.length();
        int width = ce("汉").width();
        this.oneWidth = width*1.1f;
        this.oneHight = ce(str).height();
        String[] strs = new String[num];
        for (int i = 0; i < num; i++)
        {
            strs[i] = str.substring(i, i + 1);
        }
        this.texts = strs;
        this.invalidate();
    }

    public Rect ce(String str)
    {
        Rect rect = new Rect();
        paint.getTextBounds(str, 0, 1, rect);
        return rect;
    }

    public static int dp2px(Context context, float dpValue)
    {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
