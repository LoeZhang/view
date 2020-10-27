package com.loe.view.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragAdapter extends FragmentPagerAdapter
{
	private List<Fragment> fragments; // Fragment数组

	public FragAdapter(FragmentManager fragmentManager,
					   List<ViewPagerItem> items)
	{
		super(fragmentManager);
		fragments = new ArrayList<Fragment>();
		for (ViewPagerItem fitem : items)
		{
			if(fitem.args!=null)
			{
				fitem.fragment.setArguments(fitem.args);
			}
			fragments.add(fitem.fragment);
		}
	}

	@Override
	public Fragment getItem(int position)
	{
		return fragments.get(position);
	}

	@Override
	public int getCount()
	{
		return fragments.size();
	}
}
