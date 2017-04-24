package com.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import alpha.com.newhelllo.R;

/**
 * Created by alpha on 4/22/2017.
 */

public class OutcomeTypeAdapter extends ArrayAdapter {

    List<OutcomeType> list = new ArrayList<>();

    public OutcomeTypeAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    public void add(OutcomeType outcomeType) {
        super.add(outcomeType);
        list.add(outcomeType);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public OutcomeType getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        OutcomeTypeHolder outcomeTypeHolder;
        if (row != null) {
            outcomeTypeHolder = (OutcomeTypeHolder) row.getTag();
        } else {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.outcome_type_row_layout, parent, false);
            outcomeTypeHolder = new OutcomeTypeHolder();
            outcomeTypeHolder.tvNo = (TextView) row.findViewById(R.id.tvNo);
            outcomeTypeHolder.tvOutcomeName = (TextView) row.findViewById(R.id.tvOutcomeTypeName);
            row.setTag(outcomeTypeHolder);
        }
        OutcomeType outcomeType = this.getItem(position);
        outcomeTypeHolder.tvNo.setText(outcomeType.getNo().toString());
        outcomeTypeHolder.tvOutcomeName.setText(outcomeType.getOutcomeTypeName());
        return row;
    }

    static class OutcomeTypeHolder {
        TextView tvNo;
        TextView tvOutcomeName;
    }
}
