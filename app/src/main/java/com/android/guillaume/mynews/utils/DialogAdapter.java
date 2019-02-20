package com.android.guillaume.mynews.utils;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.guillaume.mynews.R;
import com.android.guillaume.mynews.controllers.fragments.DialogFragment;


public class DialogAdapter extends android.support.v4.app.DialogFragment implements CloseDialogListener {

    public String TAG;
    private Fragment searchDialogFrag;

    public DialogAdapter() {
    }

    public static DialogAdapter display(FragmentManager fragmentManager, String TAG) {
        DialogAdapter dialog = new DialogAdapter();
        Bundle args = new Bundle();
        args.putString("TYPE_DIALOG",TAG);
        dialog.setArguments(args);
        dialog.show(fragmentManager,TAG);
        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;
        this.TAG = getArguments().getString("TYPE_DIALOG");
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
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setLayout(width, height);
            dialog.getWindow().setGravity(Gravity.TOP);
        }
    }

    //Configure fragment to Show in Dialog
    private void configureFragment() {

        if (this.searchDialogFrag == null || !this.searchDialogFrag.isVisible()) {

            this.searchDialogFrag = DialogFragment.newInstance(this.TAG);
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
