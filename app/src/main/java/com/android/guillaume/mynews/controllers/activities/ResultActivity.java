package com.android.guillaume.mynews.controllers.activities;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;

import com.android.guillaume.mynews.R;
import com.android.guillaume.mynews.controllers.fragments.ResultFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultActivity extends AppCompatActivity {

    @BindView(R.id.result_toolbar)
    Toolbar toolbar;

    private Fragment fragment;
    private String inputText;
    private List<String> filterQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        ButterKnife.bind(this);
        this.getExtraIntentValues();
        this.configureToolBar();
        this.configureAndShowFragment();
    }

    /********************* UI CONTROLS ****************/
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

    private void configureToolBar() {
        setSupportActionBar(this.toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert inputText != null;
        actionBar.setTitle(inputText);
    }


    private void configureAndShowFragment() {
        Log.d("TAG", "configureAndShowFragment: ");
        this.fragment = getSupportFragmentManager().findFragmentById(R.id.result_frame_layout);

        if (this.fragment == null || !this.fragment.isVisible()) {

            this.fragment = ResultFragment.newInstance(queryBuilder());

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.result_activity_framelayout, this.fragment)
                    .commit();
        }
    }

    private void getExtraIntentValues(){
        Intent intent = getIntent();

        //Get Input query text if not null
        if (intent.getStringExtra("EXTRA_TEXT") != null) {
            inputText = intent.getStringExtra("EXTRA_TEXT");
        }

        //Get checkboxes values if not null
        if (intent.getStringArrayListExtra("EXTRA_BOXES") != null) {
            this.filterQuery = new ArrayList<>();
            this.filterQuery = intent.getStringArrayListExtra("EXTRA_BOXES");
        }
    }

    // Build Params to set to api Request
    private HashMap<String,String> queryBuilder() {

        HashMap<String,String> map = new HashMap<>();
        // the input search value param
        String query = inputText;
        // category params
        StringBuilder categoryFilter = new StringBuilder();
        String filterSeparator = "%20";
        // Sort param
        String sortType = "relevance";

        // Build filter query params
        if(this.filterQuery.size() > 1){
            for (String str : this.filterQuery) {
                categoryFilter.append(str);
                categoryFilter.append(filterSeparator);
            }
        }
        else if(!this.filterQuery.get(0).isEmpty()){
            categoryFilter.append(this.filterQuery.get(0));
        }

        // Add all query params in HashMap
        map.put("q",query);
        map.put("fq",categoryFilter.toString());
        map.put("sort", sortType);

        return map;
    }
}
