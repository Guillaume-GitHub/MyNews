package com.android.guillaume.mynews.controllers.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.android.guillaume.mynews.R;
import com.android.guillaume.mynews.controllers.fragments.MainFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;
    @BindView(R.id.navigation_view) NavigationView navigationView;
    @BindView(R.id.toolbar) Toolbar toolbar;

    private Fragment mainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        this.configureToolBar();
        this.configureNavigationView();
        this.configureAndShowFragment();

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        // set item as selected to persist highlight
        menuItem.setChecked(true);
        // close drawer when item is tapped
        this.drawerLayout.closeDrawer(GravityCompat.START);

        // Add code here to update the UI based on the item selected
        // For example, swap UI fragments here

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
    }

    private void configureNavigationView() {
        this.navigationView.setNavigationItemSelectedListener(this);
    }


    //******** FRAGMENT *********
    private void configureAndShowFragment() {

        this.mainFragment = getSupportFragmentManager().findFragmentById(R.id.frame_layout);

        if (this.mainFragment == null) {
            this.mainFragment = new MainFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_layout, this.mainFragment)
                    .commit();
        }
    }

}
