package com.android.guillaume.mynews.controllers.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.guillaume.mynews.R;


public class NoResultFragment extends Fragment {


    public NoResultFragment() {
        // Required empty public constructor
    }

    public static NoResultFragment newInstance() {
        return new NoResultFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_no_result, container, false);
    }

}
