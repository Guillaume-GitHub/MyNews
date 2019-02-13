package com.android.guillaume.mynews.utils;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.guillaume.mynews.R;
import com.android.guillaume.mynews.controllers.fragments.SearchDialogFragment;

import butterknife.ButterKnife;


public class SearchDialogAdapter extends DialogFragment implements CloseDialogListener {


    private Fragment searchDialogFrag;

    public SearchDialogAdapter() {
    }

    public static final String TAG = "Dialog Search";

    public static SearchDialogAdapter display(FragmentManager fragmentManager) {
        SearchDialogAdapter dialog = new SearchDialogAdapter();
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

        this.configureFragment();

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();

        //Set Params to Dialog Box
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
            dialog.getWindow().setGravity(Gravity.TOP);
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


    @Override
    public void buttonClicked(Boolean valid) {
        Log.d(TAG, "buttonClicked: ");
        if (valid) {
            dismiss();
        }
    }
}
