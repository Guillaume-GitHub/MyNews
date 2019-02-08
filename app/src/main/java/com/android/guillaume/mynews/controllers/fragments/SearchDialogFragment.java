package com.android.guillaume.mynews.controllers.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SearchView;

import com.android.guillaume.mynews.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchDialogFragment extends Fragment {

    @BindView(R.id.search_bar)
    SearchView searchView;
    @BindView(R.id.checkBox)
    CheckBox checkBox;
    @BindView(R.id.checkBox2)
    CheckBox checkBox2;
    @BindView(R.id.checkBox3)
    CheckBox checkBox3;
    @BindView(R.id.checkBox4)
    CheckBox checkBox4;
    @BindView(R.id.checkBox5)
    CheckBox checkBox5;
    @BindView(R.id.checkBox6)
    CheckBox checkBox6;
    @BindView(R.id.search_btn)
    Button btn;
    @BindView(R.id.begin_dateText)
    EditText beginDateText;
    @BindView(R.id.end_dateText)
    EditText endDateText;

    public SearchDialogFragment() {
        // Required empty public constructor
    }
    public static SearchDialogFragment newInstance() {
        return new SearchDialogFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.fragment_search_dialog,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // For Submit btn
        this.configBtn();

        //
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //TODO: Submit test +  set values + start Activity
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //TODO: ......
                return true;
            }
        });
    }

    //Add OnClickListener on submit btn
    private void configBtn(){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Submit test +  set values + start Activity
            }
        });
    }
}
