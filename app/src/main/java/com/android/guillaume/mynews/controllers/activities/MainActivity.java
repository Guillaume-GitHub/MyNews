package com.android.guillaume.mynews.controllers.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.android.guillaume.mynews.R;
import com.android.guillaume.mynews.controllers.fragments.MainFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.navigation_view)
    NavigationView navigationView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;


    private Fragment mainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        this.configureToolBar();
        this.configureNavigationView();
        this.configureAndShowFragment("home");

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
                this.configureAndShowFragment("home");
                break;

            case R.id.menu_health_item:
                this.configureAndShowFragment("health");
                break;

            case R.id.menu_sports_item:
                this.configureAndShowFragment("sports");
                break;

            case R.id.menu_business_item:
                this.configureAndShowFragment("business");
                break;

            //TO DO : MostPopular, Profile, Settings
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
    }

    private void configureNavigationView() {
        this.navigationView.setNavigationItemSelectedListener(this);
    }


    //******** FRAGMENT *********
    private void configureAndShowFragment(String category) {

        this.mainFragment = getSupportFragmentManager().findFragmentById(R.id.frame_layout);

        if (this.mainFragment == null || !this.mainFragment.isVisible()) {
            this.mainFragment = MainFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.frame_layout, this.mainFragment).commit();
            ((MainFragment) this.mainFragment).getArticleFromHttpRequest(category);
        }

        ((MainFragment) this.mainFragment).getArticleFromHttpRequest(category);

    }
}
