package com.android.guillaume.mynews.controllers.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.android.guillaume.mynews.R;
import com.android.guillaume.mynews.controllers.fragments.DetailFragment;
import com.android.guillaume.mynews.controllers.fragments.MainFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.detail_toolbar) Toolbar toolbar;

    private Fragment detailFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        this.configureToolBar();
        this.configureAndShowFragment();

    }

    private void configureToolBar(){
        setSupportActionBar(this.toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void configureAndShowFragment() {

        this.detailFragment = getSupportFragmentManager().findFragmentById(R.id.detail_activity_framelayout);

            Bundle bundle = new Bundle();
            bundle.putString("URL_KEY",getIntent().getStringExtra(MainFragment.EXTRA_URL));


        if (this.detailFragment == null) {

            this.detailFragment = new DetailFragment();
            this.detailFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.detail_activity_framelayout, this.detailFragment)
                    .commit();
        }
    }
}
