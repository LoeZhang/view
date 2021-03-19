package com.loe.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

@SuppressLint("AppCompatCustomView")
public class RecyclerScrollView extends RecyclerView
{
    public RecyclerScrollView(Context context)
    {
        super(context);
        init();
    }

    public RecyclerScrollView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public RecyclerScrollView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        init();
    }

    private void init()
    {
        setNestedScrollingEnabled(false);
    }

    /**
     * 重写该方法，达到使ListView适应ScrollView的效果
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int expandSpec = View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, View.MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    private ArrayList<OnItemTouchListener> listeners = new ArrayList<>();

    @Override
    public void addOnItemTouchListener(@NonNull OnItemTouchListener listener)
    {
        listeners.add(listener);
        super.addOnItemTouchListener(listener);
    }

    public void clearItemTouchListener()
    {
        for(OnItemTouchListener listener: listeners)
        {
            removeOnItemTouchListener(listener);
        }
    }
}
