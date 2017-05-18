package com.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.background.DeleteOutcomeType;
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

    public OutcomeTypeAdapter(Context context, int resource) {
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

    @Override
    public OutcomeType getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        OutcomeTypeHolder outcomeTypeHolder;
        if (row != null) {
            outcomeTypeHolder = (OutcomeTypeHolder) row.getTag();
        } else {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.outcome_type_row_layout, parent, false);
            outcomeTypeHolder = new OutcomeTypeHolder();
            outcomeTypeHolder.tvNo = (TextView) row.findViewById(R.id.tvNo);
            outcomeTypeHolder.tvOutcomeTypeName = (TextView) row.findViewById(R.id.tvOutcomeTypeName);
            outcomeTypeHolder.tvDeleteOutcomeType = (TextView) row.findViewById(R.id.tvDeleteOutcomeType);
            row.setTag(outcomeTypeHolder);
        }
        OutcomeType outcomeType = this.getItem(position);
        outcomeTypeHolder.tvNo.setText(outcomeType.getNo().toString());
        outcomeTypeHolder.tvOutcomeTypeName.setText(outcomeType.getOutcomeTypeName());
        outcomeTypeHolder.tvDeleteOutcomeType.setText("x");

        this.doSetEditOnclick(outcomeTypeHolder, position);
        this.doSetDeleteOnclick(outcomeTypeHolder, position);
        return row;
    }


    private void doSetEditOnclick(OutcomeTypeHolder outcomeTypeHolder, int position) {
        outcomeTypeHolder.tvOutcomeTypeName.setOnClickListener(editOutcomeTypeListener);
        outcomeTypeHolder.tvOutcomeTypeName.setTag(position);
    }

    private void doSetDeleteOnclick(OutcomeTypeHolder outcomeTypeHolder, int position) {
        outcomeTypeHolder.tvDeleteOutcomeType.setOnClickListener(deleteOutcomeTypeListener);
        outcomeTypeHolder.tvDeleteOutcomeType.setTag(position);
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

    View.OnClickListener deleteOutcomeTypeListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (context != null) {
                int position = (int) view.getTag();
                OutcomeType outcomeType = getItem(position);
                DeleteOutcomeType backgroundTask = new DeleteOutcomeType(context);
                backgroundTask.execute(outcomeType.getOutcomeTypeId().toString());
                ((Activity) context).finish();
            }
        }
    };

    static class OutcomeTypeHolder {
        TextView tvNo;
        TextView tvOutcomeTypeName;
        TextView tvDeleteOutcomeType;
    }
}
