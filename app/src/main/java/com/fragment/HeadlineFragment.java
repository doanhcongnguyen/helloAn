package com.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import alpha.com.newhelllo.R;

/**
 * Created by synergix18 on 5/3/2017.
 */

public class HeadlineFragment extends ListFragment {

    OnHeadlineSelectedListener callback;

    public interface OnHeadlineSelectedListener {
        void onArticleSelected(int position);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        int layout = android.R.layout.simple_list_item_activated_1;
        String[] data = Data.Headlines;

        setListAdapter(new ArrayAdapter<String>(getActivity(), layout, data));
    }

    @Override
    public void onStart() {
        super.onStart();
        Fragment f = getFragmentManager().findFragmentById(R.id.article_fragment);
        ListView listView = getListView();
        if (f != null && listView != null) {
            listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        callback = (OnHeadlineSelectedListener) activity;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        callback.onArticleSelected(position);
        l.setItemChecked(position, true);
    }
}
