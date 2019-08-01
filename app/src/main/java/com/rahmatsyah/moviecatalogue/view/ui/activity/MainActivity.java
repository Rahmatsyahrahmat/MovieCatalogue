package com.rahmatsyah.moviecatalogue.view.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.rahmatsyah.moviecatalogue.R;
import com.rahmatsyah.moviecatalogue.view.ui.fragment.FavoriteFragment;
import com.rahmatsyah.moviecatalogue.view.ui.fragment.MovieFragment;
import com.rahmatsyah.moviecatalogue.view.ui.fragment.TvShowFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private final static String FRAGMENT_TAG = "Fragment_Tag";


    private Fragment fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        if (savedInstanceState!=null){
            setFragment(getSupportFragmentManager().getFragment(savedInstanceState,FRAGMENT_TAG));
        }else {
            bottomNavigationView.setSelectedItemId(R.id.menuMovie);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.menuMovie:
                setFragment(new MovieFragment());
                return true;
            case R.id.menuTvShow:
                setFragment(new TvShowFragment());
                return true;
            case R.id.menuFavorite:
                setFragment(new FavoriteFragment());
                return true;
        }
        return false;
    }

    private void setFragment(Fragment fragment){
        this.fragment = fragment;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainFrame,fragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.setting_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent mIntent = new Intent(this,SettingActivity.class);
        startActivity(mIntent);
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState,FRAGMENT_TAG,fragment);
    }
}
