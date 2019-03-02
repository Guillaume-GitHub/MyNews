package com.android.guillaume.mynews.controllers.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.android.guillaume.mynews.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    @BindView(R.id.web_view)
    WebView webView;

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);

        this.configureWebView();
        return view;
    }

    private void configureWebView() {

        String url = getArguments().getString("URL_KEY");
        //FIXME: show message when empty
        assert url != null;
        if (!url.equals("")) {
            this.webView.loadUrl(url);
        }
    }
}
