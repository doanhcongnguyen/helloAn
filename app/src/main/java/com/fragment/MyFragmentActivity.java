package com.fragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import alpha.com.newhelllo.R;

public class MyFragmentActivity extends AppCompatActivity implements MyHeadlineFragment.OnHeadlineSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fragment);
    }

    @Override
    public void onArticleSelected(int position) {
        MyArticleFragment articleFragment = (MyArticleFragment) getFragmentManager().findFragmentById(R.id.my_article_fragment);
        articleFragment.updateArticleView(position);
    }
}
