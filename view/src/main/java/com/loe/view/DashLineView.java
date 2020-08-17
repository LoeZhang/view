package com.loe.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class DashLineView extends View
{
    public DashLineView(Context context)
    {
        super(context);
        init(context, null);
    }

    public DashLineView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context, context.obtainStyledAttributes(attrs, R.styleable.DashLineView));
    }

    public DashLineView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init(context, context.obtainStyledAttributes(attrs, R.styleable.DashLineView, defStyleAttr, 0));
    }

    @SuppressLint("NewApi")
    public DashLineView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
    {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, context.obtainStyledAttributes(attrs, R.styleable.DashLineView, defStyleAttr, defStyleRes));
    }

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Path path;

    private float lineWidth;

    private void init(Context context, TypedArray typedArray)
    {
        if (typedArray != null)
        {
            int color = typedArray.getColor(R.styleable.DashLineView_line_color, 0xFFdadce6);
            paint.setColor(color);

            lineWidth = typedArray.getDimension(R.styleable.DashLineView_line_width, dp_px(1));
            paint.setStrokeWidth(lineWidth);

            float dashGap = typedArray.getDimension(R.styleable.DashLineView_line_dashGap, dp_px(6));
            float dashWidth = typedArray.getDimension(R.styleable.DashLineView_line_dashWidth, dp_px(4));
            paint.setPathEffect(new DashPathEffect(new float[]{dashGap, dashWidth}, 0));
        }
        else
        {
            lineWidth = dp_px(1);
            paint.setColor(0xFFdadce6);
            paint.setStrokeWidth(lineWidth);
            paint.setPathEffect(new DashPathEffect(new float[]{dp_px(6), dp_px(4)}, 0));
        }

        paint.setStyle(Paint.Style.STROKE);

        path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        float y = getPaddingTop() + lineWidth / 2;
        path.reset();
        path.moveTo(getPaddingLeft(), y);
        path.lineTo(getWidth() - getPaddingRight(), y);
        canvas.drawPath(path, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //用于获取设定的模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        // 用于获取设定的长度
        int widthSize = widthMode != MeasureSpec.EXACTLY ? 0 : MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = heightMode != MeasureSpec.EXACTLY ? (int) lineWidth + getPaddingTop() + getPaddingBottom() : MeasureSpec.getSize(heightMeasureSpec);

        setMeasuredDimension(widthSize, heightSize);
    }

    public void setLineWidth(float lineWidth)
    {
        this.lineWidth = lineWidth;
        invalidate();
    }

    public void setLineDash(float dashGap, float dashWidth)
    {
        paint.setPathEffect(new DashPathEffect(new float[]{dashGap, dashWidth}, 0));
        invalidate();
    }

    public int dp_px(float dpValue)
    {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
