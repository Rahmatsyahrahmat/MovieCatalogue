package com.rahmatsyah.moviecatalogue.view.ui.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.rahmatsyah.moviecatalogue.view.ui.fragment.FavoriteMovieFragment;
import com.rahmatsyah.moviecatalogue.view.ui.fragment.FavoriteTvShowFragment;

public class FavoritePagerAdapter extends FragmentStatePagerAdapter {

    private int tabCount;

    public FavoritePagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;

    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FavoriteMovieFragment();
            case 1:
                return new FavoriteTvShowFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }

}
