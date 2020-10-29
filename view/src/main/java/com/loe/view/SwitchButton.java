package com.loe.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class SwitchButton extends View
{
    private final Paint mPaint = new Paint();
    private static final float RATIO = 0.55f;
    private float mAnimate = 0L;
    private boolean isChecked;
    private int mSelectColor;
    private OnCheckedChangeListener mOnCheckedChangeListener;

    private float width;
    private float height;

    private float r;
    private float off;

    private float offX;
    private float offY;

    public SwitchButton(Context context)
    {
        this(context, null);
    }

    public SwitchButton(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SwitchButton);
        mSelectColor = typedArray.getColor(R.styleable.SwitchButton_switch_color, getResources().getColor(R.color.colorPrimary));
        isChecked = typedArray.getBoolean(R.styleable.SwitchButton_switch_is_checked, false);
        typedArray.recycle();

        setLayerType(LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        width = MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft() - getPaddingRight();
        height = MeasureSpec.getSize(heightMeasureSpec) - getPaddingTop() - getPaddingBottom();
        if (width <= 0 || MeasureSpec.getMode(widthMeasureSpec) != MeasureSpec.EXACTLY)
        {
            width = height / RATIO;
        }
        if (height <= 0 || MeasureSpec.getMode(heightMeasureSpec) != MeasureSpec.EXACTLY)
        {
            height = RATIO * width;
        }
        r = height / 2f;
        off = width * 0.09f;
        offX = off + getPaddingLeft();
        offY = off + getPaddingTop();

        setMeasuredDimension(getPaddingLeft() + (int) (width + off * 2) + getPaddingRight(), getPaddingTop() + (int) (height + off * 2) + getPaddingBottom());
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);

        // 动画标示 ，重绘10次，借鉴被人的动画
        mAnimate = mAnimate - 0.1f > 0 ? mAnimate - 0.15f : 0;
        boolean isAnimate = (!isChecked ? 1 - mAnimate : mAnimate) > 0;
        //平移距离
        float translate = (width - height) * (!isChecked ? mAnimate : 1 - mAnimate) - 1f;

        //绘制圆角矩形
        mPaint.setColor(mSelectColor);
        mPaint.setShadowLayer(off, 0, off * 0.2f, 0x28000000);
        canvas.drawRoundRect(new RectF(offX, offY, offX + width, offY + height), r, r, mPaint);

        //绘制收缩白色圆角矩形
        mPaint.setColor(Color.WHITE);
        mPaint.setShadowLayer(0, 0, 0, 0);
        canvas.drawRoundRect(new RectF(offX + translate, offY - 0.5f, offX + width + 1f, offY + height + 0.5f), r, r, mPaint);

        canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);

        mPaint.setColor(Color.WHITE);
        mPaint.setShadowLayer(0, 0, 0, 0);
        canvas.drawRoundRect(new RectF(offX + translate, offY - 0.5f, offX + width + 1f, offY + height + 0.5f), r, r, mPaint);

        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        // 绘制白色圆形
        mPaint.setColor(Color.WHITE);
        mPaint.setShadowLayer(off, 0, off * 0.2f, 0x28000000);
        canvas.drawCircle(offX + r + translate, offY + r, r, mPaint);

        mPaint.setXfermode(null);
        canvas.restore();

        if (isAnimate)
        {
            mPaint.reset();
            invalidate();
        }
    }

    private boolean isDown;

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                isDown = true;
                return true;
            case MotionEvent.ACTION_MOVE:
                isDown = inRangeOfView(event);
                return true;
            case MotionEvent.ACTION_UP:
                if(isDown)
                {
                    setChecked(!isChecked);
                }
                isDown = false;
                break;
        }
        return super.onTouchEvent(event);
    }

    public boolean isChecked()
    {
        return isChecked;
    }

    private boolean inRangeOfView(MotionEvent ev)
    {
        int[] location = new int[2];
        getLocationOnScreen(location);
        int x = location[0];
        int y = location[1];
        return !(ev.getRawX() < x || ev.getRawX() > x + this.getWidth() || ev.getRawY() < y || ev.getRawY() > y + this.getHeight());
    }

    public void setCheckedNoListener(boolean checked)
    {
        if (isChecked != checked)
        {
            mAnimate = 1;
            isChecked = checked;
            invalidate();
        }
    }

    public void setCheckedNoAnimate(boolean checked)
    {
        if (isChecked != checked)
        {
            isChecked = checked;
            if (mOnCheckedChangeListener != null)
            {
                mOnCheckedChangeListener.OnCheckedChanged(isChecked);
            }
            invalidate();
        }
    }

    public void setChecked(boolean checked)
    {
        if (isChecked != checked)
        {
            mAnimate = 1;
            isChecked = checked;
            if (mOnCheckedChangeListener != null)
            {
                mOnCheckedChangeListener.OnCheckedChanged(isChecked);
            }
            invalidate();
        }
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener mOnCheckedChangeListener)
    {
        this.mOnCheckedChangeListener = mOnCheckedChangeListener;
    }

    public interface OnCheckedChangeListener
    {
        void OnCheckedChanged(boolean isChecked);
    }
}
