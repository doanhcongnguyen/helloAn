package com.fragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import alpha.com.newhelllo.R;

public class FragmentActivity extends AppCompatActivity implements HeadlineFragment.OnHeadlineSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        if (findViewById(R.id.container) != null) {
            if (savedInstanceState != null) {
                return;
            }
            HeadlineFragment headlineFragment = new HeadlineFragment();
            headlineFragment.setArguments(getIntent().getExtras());

            getFragmentManager().beginTransaction()
                    .add(R.id.container, headlineFragment).commit();
        }
    }

    @Override
    public void onArticleSelected(int position) {
        ArticleFragment articleFragment = (ArticleFragment) getFragmentManager().findFragmentById(R.id.article_fragment);
        if (articleFragment != null) {
            articleFragment.updateArticleView(position);
        } else {
            ArticleFragment swapFragment = new ArticleFragment();
            Bundle args = new Bundle();
            args.putInt(ArticleFragment.ARG_POSITION, position);
            swapFragment.setArguments(args);

            getFragmentManager().beginTransaction()
                    .replace(R.id.container, swapFragment).commit();
        }

    }

}
