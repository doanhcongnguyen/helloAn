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

public class OutcomeAdapter extends ArrayAdapter {

    List<Outcome> list = new ArrayList<>();

    public OutcomeAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    public void add(Outcome outcome) {
        super.add(outcome);
        list.add(outcome);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Outcome getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        OutcomeHolder outcomeHolder;
        if (row != null) {
            outcomeHolder = (OutcomeHolder) row.getTag();
        } else {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.outcome_row_layout, parent, false);
            outcomeHolder = new OutcomeHolder();
            outcomeHolder.tvNo = (TextView) row.findViewById(R.id.tvNo);
            outcomeHolder.tvOutcomeTypeId = (TextView) row.findViewById(R.id.tvOutcomeTypeId);
            outcomeHolder.tvOutcomeDate = (TextView) row.findViewById(R.id.tvOutcomeDate);
            outcomeHolder.tvDay = (TextView) row.findViewById(R.id.tvDay);
            outcomeHolder.tvMonth = (TextView) row.findViewById(R.id.tvMonth);
            outcomeHolder.tvYear = (TextView) row.findViewById(R.id.tvYear);
            row.setTag(outcomeHolder);
        }
        Outcome outcome = this.getItem(position);
        outcomeHolder.tvNo.setText(outcome.getNo().toString());
        outcomeHolder.tvOutcomeTypeId.setText(outcome.getOutcomeTypeId().toString());
        outcomeHolder.tvOutcomeDate.setText(outcome.getOutcomeDate().toString());
        outcomeHolder.tvDay.setText(outcome.getDay().toString());
        outcomeHolder.tvMonth.setText(outcome.getMonth().toString());
        outcomeHolder.tvYear.setText(outcome.getYear().toString());
        return row;
    }

    static class OutcomeHolder {
        TextView tvNo;
        TextView tvOutcomeName;
        TextView tvOutcomeTypeId;
        TextView tvOutcomeDate;
        TextView tvDay;
        TextView tvMonth;
        TextView tvYear;
    }
}
