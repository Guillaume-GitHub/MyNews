package com.android.guillaume.mynews.controllers.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.android.guillaume.mynews.R;
import com.android.guillaume.mynews.controllers.fragments.DialogFragment;
import com.android.guillaume.mynews.utils.DialogAdapter;
import com.android.guillaume.mynews.views.ViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.navigation_view)
    NavigationView navigationView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.search_button)
    ImageButton searchButton;
    @BindView(R.id.notif_button)
    ImageButton notifButton;


    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MainActivity", "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        this.configureToolBar();
        this.configureNavigationView();
        this.configureViewPager();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        // set item as selected to persist highlight
        menuItem.setChecked(true);
        // close drawer when item is tapped
        this.drawerLayout.closeDrawer(GravityCompat.START);

        // Add code here to update the UI based on the item selected
        Log.d("TAG", "onNavigationItemSelected: " + String.valueOf(menuItem.getItemId()));

        switch (menuItem.getItemId()) {
            case R.id.menu_home_item:
                viewPager.setCurrentItem(0,true);
                break;

            case R.id.menu_most_popular_item:
                this.viewPager.setCurrentItem(1,true);
                break;

            case R.id.menu_health_item:
                viewPager.setCurrentItem(2,true);
                break;

            case R.id.menu_sports_item:
                viewPager.setCurrentItem(3,true);
                break;

            case R.id.menu_business_item:
                viewPager.setCurrentItem(4,true);
                break;

            //TODO: Profile, Settings
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // To close navigation drawer
    @Override
    public void onBackPressed() {
        //Handle back click to close menu
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //******** CONFIGURATION **********
    private void configureToolBar() {
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();

        // Set OnClickListener on Search Icon
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "onClick: ");
                DialogAdapter.display(getSupportFragmentManager(),DialogFragment.SEARCH_ARTICLE);
            }
        });

        // Set OnClickListener on Notification Icon
        notifButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "onClick: ");
                DialogAdapter.display(getSupportFragmentManager(),DialogFragment.NOTIFICATION);
            }
        });

    }

    private void configureNavigationView() {
        this.navigationView.setNavigationItemSelectedListener(this);
    }

    private void configureViewPager() {
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setOffscreenPageLimit(4);
        viewPager.setCurrentItem(0);
    }
}
