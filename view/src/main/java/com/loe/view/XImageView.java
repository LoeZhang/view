package com.loe.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

@SuppressLint("AppCompatCustomView")
public class XImageView extends ImageView
{
    private boolean isCorner;

    float width, height;

    private float defaultRadius = 0;
    private float radius;
    private float leftTopRadius;
    private float rightTopRadius;
    private float rightBottomRadius;
    private float leftBottomRadius;

    public XImageView(Context context)
    {
        this(context, null);
    }

    public XImageView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
        init(context, attrs);
    }

    public XImageView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs)
    {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);

        if (Build.VERSION.SDK_INT < 18)
        {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.XImageView);
        radius = array.getDimension(R.styleable.XImageView_radius, defaultRadius);
        leftTopRadius = array.getDimension(R.styleable.XImageView_radius_leftTop, defaultRadius);
        rightTopRadius = array.getDimension(R.styleable.XImageView_radius_rightTop, defaultRadius);
        rightBottomRadius = array.getDimension(R.styleable.XImageView_radius_rightBottom, defaultRadius);
        leftBottomRadius = array.getDimension(R.styleable.XImageView_radius_leftBottom, defaultRadius);
        if (defaultRadius == leftTopRadius)
        {
            leftTopRadius = radius;
        }
        if (defaultRadius == rightTopRadius)
        {
            rightTopRadius = radius;
        }
        if (defaultRadius == rightBottomRadius)
        {
            rightBottomRadius = radius;
        }
        if (defaultRadius == leftBottomRadius)
        {
            leftBottomRadius = radius;
        }

        isCorner = leftTopRadius + leftBottomRadius + rightTopRadius + rightBottomRadius > 0;

        int color = array.getColor(R.styleable.XImageView_color_filter, -2);
        if (color != -2)
        {
            setColorFilter(color);
        }

        array.recycle();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom)
    {
        super.onLayout(changed, left, top, right, bottom);

        if (isCorner)
        {
            width = getWidth();
            height = getHeight();

            float l = (width < height ? width : height) / 2;

            if (leftTopRadius > l)
            {
                leftTopRadius = l;
            }
            if (leftBottomRadius > l)
            {
                leftBottomRadius = l;
            }
            if (rightTopRadius > l)
            {
                rightTopRadius = l;
            }
            if (rightBottomRadius > l)
            {
                rightBottomRadius = l;
            }
            rectF1 = new RectF(width - 2 * rightTopRadius, 0, width, 2 * rightTopRadius);
            rectF2 = new RectF(width - 2 * rightBottomRadius, height - 2 * rightBottomRadius, width, height);
            rectF3 = new RectF(0, height - 2 * leftBottomRadius, 2 * leftBottomRadius, height);
            rectF4 = new RectF(0, 0, 2 * leftTopRadius, 2 * leftTopRadius);
        }
    }

    private RectF rectF1, rectF2, rectF3, rectF4;

    private Paint paint;

    @Override
    protected void onDraw(Canvas canvas)
    {
        if (isCorner)
        {
            Path path = new Path();
            //四个角：右上，右下，左下，左上
            path.moveTo(leftTopRadius, 0);
            path.lineTo(width - rightTopRadius, 0);
            path.arcTo(rectF1, 270, 90);

            path.lineTo(width, height - rightBottomRadius);
            path.arcTo(rectF2, 0, 90);

            path.lineTo(leftBottomRadius, height);
            path.arcTo(rectF3, 90, 90);

            path.lineTo(0, leftTopRadius);
            path.arcTo(rectF4, 180, 90);

            // canvas.clipPath(path);

            RectF srcRectF = new RectF(0, 0, getWidth(), getHeight());

            canvas.saveLayer(srcRectF, null, Canvas.ALL_SAVE_FLAG);
            // ImageView自身的绘制流程，即绘制图片
            super.onDraw(canvas);
            // 设置混合模式
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
            // 绘制path
            canvas.drawPath(path, paint);
            // 清除Xfermode
            paint.setXfermode(null);
            // 恢复画布状态
            canvas.restore();
        }
        else
        {
            super.onDraw(canvas);
        }
    }
}