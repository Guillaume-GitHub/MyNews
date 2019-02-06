package com.android.guillaume.mynews.views;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import com.android.guillaume.mynews.controllers.fragments.MainFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    //Number of page in viewpager
    private static int NUM_ITEMS = 5;

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

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public Fragment getItem(int position) {
        Log.w("TAG", "getItem: ");

        switch (position) {
            case 0:
                return MainFragment.newInstance("home");
            case 1:
                return MainFragment.newInstance("viewed");
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

    //Return Total number of pages
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }
}
