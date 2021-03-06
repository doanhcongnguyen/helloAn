package com.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import alpha.com.newhelllo.R;

/**
 * Created by synergix18 on 5/3/2017.
 */

public class MyArticleFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.article_fragment, container, false);
        return view;
    }

    public void updateArticleView(int position){
        View v = getView();
        TextView article = (TextView) v.findViewById(R.id.article);
        article.setText(Data.Articles[position]);
    }
}
