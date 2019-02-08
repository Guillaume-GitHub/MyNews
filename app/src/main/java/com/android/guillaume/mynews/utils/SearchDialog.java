package com.android.guillaume.mynews.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;
import android.widget.SearchView;

import com.android.guillaume.mynews.R;
import com.android.guillaume.mynews.controllers.activities.SearchableActivity;
import com.android.guillaume.mynews.controllers.fragments.DetailFragment;
import com.android.guillaume.mynews.controllers.fragments.MainFragment;
import com.android.guillaume.mynews.controllers.fragments.SearchDialogFragment;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SearchDialog extends DialogFragment {

    @BindView(R.id.search_toolbar)
    Toolbar toolbar;

    private Fragment searchDialogFrag;

    public SearchDialog() {
    }

    public static final String TAG = "Dialog Search";

    public static SearchDialog display(FragmentManager fragmentManager) {
        SearchDialog dialog = new SearchDialog();
        dialog.show(fragmentManager, TAG);
        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = getLayoutInflater().inflate(R.layout.search_dialog,container,false);

        ButterKnife.bind(this,view);

        this.configureFragment();

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    //Select item option
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                this.dismiss();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    //Configure fragment to Show in Dialog
    private void configureFragment() {

        this.searchDialogFrag = getChildFragmentManager().findFragmentById(R.id.detail_activity_framelayout);

        if (this.searchDialogFrag == null || !this.searchDialogFrag.isVisible()) {

            this.searchDialogFrag = SearchDialogFragment.newInstance();
            getChildFragmentManager().beginTransaction()
                    .add(R.id.dialog_framelayout, this.searchDialogFrag)
                    .commit();
        }
    }
}
