package com.rahmatsyah.favoritemoviecatalogue.view.ui.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.rahmatsyah.favoritemoviecatalogue.view.ui.fragment.MovieFragment;
import com.rahmatsyah.favoritemoviecatalogue.view.ui.fragment.TvShowFragment;

public class FragmentPagerAdapter extends FragmentStatePagerAdapter {

    private int tabCount;

    public FragmentPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;

    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new MovieFragment();
            case 1:
                return new TvShowFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }

}
