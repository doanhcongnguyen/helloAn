package com.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.util.Constant;

import java.util.ArrayList;
import java.util.List;

import alpha.com.newhelllo.R;
import alpha.com.newhelllo.activity.EditOutcomeTypeActivity;

/**
 * Created by alpha on 4/22/2017.
 */

public class OutcomeTypeAdapter extends ArrayAdapter {

    private List<OutcomeType> list = new ArrayList<>();
    private Context context;

    public OutcomeTypeAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
        this.context = context;
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

        // Set onclick to edit
        outcomeTypeHolder.tvOutcomeName.setOnClickListener(editOutcomeTypeListener);
        outcomeTypeHolder.tvOutcomeName.setTag(position);

        return row;
    }

    View.OnClickListener editOutcomeTypeListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (context != null) {
                int position = (int) view.getTag();
                OutcomeType outcomeType = getItem(position);
                Intent intent = new Intent(context, EditOutcomeTypeActivity.class);
                intent.putExtra(Constant.EditOutcomeTypeValue.id, outcomeType.getOutcomeTypeId().toString());
                intent.putExtra(Constant.EditOutcomeTypeValue.name, outcomeType.getOutcomeTypeName());
                context.startActivity(intent);
            }
        }
    };

    static class OutcomeTypeHolder {
        TextView tvNo;
        TextView tvOutcomeName;
    }
}
