package com.loe.view.viewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * ViewPager选项卡基类
 *
 * @author zls
 * @version 1.0
 * @time 2016-4-16下午3:33:04
 */
public abstract class ViewPagerItem
{
    public View viewGroup;
    public View view;
    public Bundle args;
    public Fragment fragment;
    public int id = 0;

    public abstract void selectIn();

    public abstract void selectMove();

    public ViewPagerItem(Class cls)
    {
        try
        {
            this.fragment = (Fragment) cls.newInstance();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public ViewPagerItem addArg(String key, int value)
    {
        if (args == null)
        {
            args = new Bundle();
        }
        args.putInt(key, value);
        return this;
    }

    public ViewPagerItem addArg(String key, String value)
    {
        if (args == null)
        {
            args = new Bundle();
        }
        args.putString(key, value);
        return this;
    }

    public ViewPagerItem addArg(String key, boolean value)
    {
        if (args == null)
        {
            args = new Bundle();
        }
        args.putBoolean(key, value);
        return this;
    }

    public ViewPagerItem addArg(String key, double value)
    {
        if (args == null)
        {
            args = new Bundle();
        }
        args.putDouble(key, value);
        return this;
    }
}