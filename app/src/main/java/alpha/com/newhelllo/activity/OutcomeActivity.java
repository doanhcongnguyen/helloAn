package alpha.com.newhelllo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.adapter.Outcome;
import com.adapter.OutcomeAdapter;
import com.util.Constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import alpha.com.newhelllo.R;

public class OutcomeActivity extends AppCompatActivity {

    String JSON_STRING;
    JSONObject jsonObject;
    JSONArray jsonArray;
    OutcomeAdapter outcomeAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.outcome_layout);

        outcomeAdapter = new OutcomeAdapter(this, R.layout.outcome_row_layout);
        listView = (ListView) findViewById(R.id.addOutcomeList);
        listView.setAdapter(outcomeAdapter);
        JSON_STRING = getIntent().getExtras().getString(Constant.IntentExtraKey.outcomeJson);
        try {
            jsonObject = new JSONObject(JSON_STRING);
            int count = 0;
            jsonArray = jsonObject.getJSONArray("outcome");
            Long outcomeTypeId;
            String outcomeDate;
            Long day;
            Long month;
            Long year;
            Long no = 1L;
            while (count < jsonArray.length()) {
                JSONObject obj = jsonArray.getJSONObject(count);
                outcomeTypeId = obj.getLong("outcomeTypeId");
                outcomeDate = obj.getString("outcomeDate");
                day = obj.getLong("day");
                month = obj.getLong("month");
                year = obj.getLong("year");
                Outcome outcome = new Outcome();
                outcome.setNo(no++);
                outcome.setOutcomeTypeId(outcomeTypeId);
                outcome.setOutcomeDate(outcomeDate);
                outcome.setDay(day);
                outcome.setMonth(month);
                outcome.setYear(year);
                outcomeAdapter.add(outcome);
                count++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void addOutcome(View view) {
        Intent intent = new Intent(this, AddOutcomeActivity.class);
        startActivity(intent);
    }
}
