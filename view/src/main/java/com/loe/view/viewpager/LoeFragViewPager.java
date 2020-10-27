package com.loe.view.viewpager;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Scroller;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class LoeFragViewPager extends ViewPager implements OnClickListener
{
    private List<ViewPagerItem> items;
    private FragmentManager fragmentManager;
    private FragAdapter pagerAdapter;
    private Field mScroller;
    public int index = 0;
    // 卷动器
    private SpeedScroller scroller;
    /**
     * 是否可滑动
     */
    private boolean canMove = true;

    public LoeFragViewPager(Context context)
    {
        super(context);
    }

    public LoeFragViewPager(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public void init(FragmentManager fragmentManager, ViewPagerItem... items)
    {
        this.items = Arrays.asList(items);
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments != null && fragments.size() == items.length)
        {
            for (int i = 0; i < fragments.size(); i++)
            {
                this.items.get(i).fragment = fragments.get(i);
            }
        }
        this.fragmentManager = fragmentManager;
        ignoreViewSet = new HashSet<>();
        // 初始化布局元素
        initItems();
        // 初始化ViewPager
        initPager();
        // 默认设置滚动时间
        setScroll(500);
    }

    /**
     * 初始化item元素事件属性
     */
    private void initItems()
    {
        for (int i = 0; i < items.size(); i++)
        {
            items.get(i).id = i;
            if (items.get(i).viewGroup != null)
            {
                items.get(i).viewGroup.setOnClickListener(this);
            }
            else
            {
                items.get(i).view.setOnClickListener(this);
            }
        }
    }

    /**
     * 初始化ViewPager及监听事件
     */
    private void initPager()
    {
        pagerAdapter = new FragAdapter(fragmentManager, items);
        setAdapter(pagerAdapter);
        // 设置切换效果
        //setPageTransformer(true, new RotatePageTransformer());
        // 设置缓存视图个数
        setOffscreenPageLimit(items.size() - 1);

        // 暴力反射获取mScroller属性
        try
        {
            mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            scroller = new SpeedScroller(this.getContext());
        } catch (Exception e)
        {
        }

        addOnPageChangeListener(new OnPageChangeListener()
        {
            @Override
            public void onPageSelected(int index)
            {
                setScroll(500);
                selectItem(index);
                LoeFragViewPager.this.index = index;
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2)
            {
            }

            @Override
            public void onPageScrollStateChanged(int arg0)
            {
            }
        });
        // 第一次启动时选中第0个Fragment
        selectItem(0);
    }

    public void addOnPageSelectedListener(final IndexCallBack callBack)
    {

        addOnPageChangeListener(new OnPageChangeListener()
        {
            @Override
            public void onPageSelected(int index)
            {
                callBack.logic(index);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2)
            {
            }

            @Override
            public void onPageScrollStateChanged(int arg0)
            {
            }
        });
    }

    /**
     * 根据view设置选中的Fragment页
     */
    @Override
    public void onClick(View view)
    {
        for (ViewPagerItem fitem : items)
        {
            if (view.equals(fitem.viewGroup) || view.equals(fitem.view))
            {
                // 设置静态切换方式，优化运行效率
                setScroll(0);
                // 更新选项卡
                selectItem(fitem.id);
                // 更新页面
                setCurrentItem(fitem.id);
            }
        }
    }

    /**
     * 选择页面
     */
    public void select(int i)
    {
        // 设置静态切换方式，优化运行效率
        setScroll(0);
        // 更新选项卡
        selectItem(i);
        // 更新页面
        setCurrentItem(i);
    }

    /**
     * 建立单个选中状态
     */
    private void selectItem(int index)
    {
        // 清除所有选择状态
        for (ViewPagerItem item : items)
        {
            item.selectMove();
        }
        // 添加单个选择状态
        items.get(index).selectIn();
    }

    /**
     * 设置viewPager切换时间
     */
    private void setScroll(int duration)
    {
        try
        {
            scroller.setDuration(duration);
            mScroller.set(this, scroller);
        } catch (Exception e)
        {
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev)
    {
        if (canMove)
        {
            return super.onInterceptTouchEvent(ev);
        }
        else
        {
            return false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev)
    {
        if (canMove)
        {
            return super.onTouchEvent(ev);
        }
        else
        {
            return false;
        }
    }

    private HashSet<View> ignoreViewSet;

    public void addIgnoreView(View v)
    {
        ignoreViewSet.add(v);
    }

    public void clearIgnoreView()
    {
        ignoreViewSet.clear();
    }

    @Override
    protected boolean canScroll(View v, boolean checkV, int dx, int x, int y)
    {
        if(ignoreViewSet.contains(v))
        {
            return true;
        }
        return super.canScroll(v, checkV, dx, x, y);
    }

    /**
     * 设置是否可滑动
     */
    public void setCanMove(boolean canMove)
    {
        this.canMove = canMove;
    }

    static class SpeedScroller extends Scroller
    {
        private int mDuration = 0;

        public SpeedScroller(Context context)
        {
            super(context);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration)
        {
            super.startScroll(startX, startY, dx, dy, mDuration);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy)
        {
            super.startScroll(startX, startY, dx, dy, mDuration);
        }
        public void setDuration(int duration)
        {
            mDuration = duration;
        }
    }
}
