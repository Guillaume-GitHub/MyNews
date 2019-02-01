package com.android.guillaume.mynews.views;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.android.guillaume.mynews.controllers.fragments.MainFragment;
import com.android.guillaume.mynews.controllers.fragments.MostPopularFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    //Array of title's Tabs
    private String[] tabsTitle = new String[]{"Top Stories","Most Popular", "Health", "Sports", "Business"};

    //Default Constructor
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return this.tabsTitle[position];
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return MainFragment.newInstance("home");
            case 1:
                return MostPopularFragment.newInstance();
            case 2:
                return MainFragment.newInstance("health");
            case 3:
                return MainFragment.newInstance("sports");
            case 4:
                return MainFragment.newInstance("business");
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 5;
    }
}
