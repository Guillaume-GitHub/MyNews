package com.android.guillaume.mynews.controllers.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.android.guillaume.mynews.R;
import com.android.guillaume.mynews.controllers.fragments.DetailFragment;
import com.android.guillaume.mynews.controllers.fragments.MainFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.detail_toolbar)
    Toolbar toolbar;

    private Fragment detailFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        this.configureToolBar();
        this.configureAndShowFragment();

    }

    private void configureToolBar() {
        setSupportActionBar(this.toolbar);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void configureAndShowFragment() {

        this.detailFragment = getSupportFragmentManager().findFragmentById(R.id.detail_activity_framelayout);

        //Get Url from Bundle
        Bundle bundle = new Bundle();
        bundle.putString("URL_KEY", getIntent().getStringExtra(MainFragment.EXTRA_URL));

        if (this.detailFragment == null || !this.detailFragment.isVisible()) {

            this.detailFragment = new DetailFragment();
            this.detailFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.detail_activity_framelayout, this.detailFragment)
                    .commit();
        }
    }
}
