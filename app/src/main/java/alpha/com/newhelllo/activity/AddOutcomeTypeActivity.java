package alpha.com.newhelllo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.adapter.OutcomeType;
import com.adapter.OutcomeTypeAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import alpha.com.newhelllo.R;

public class AddOutcomeTypeActivity extends AppCompatActivity {

    String JSON_STRING;
    JSONObject jsonObject;
    JSONArray jsonArray;
    OutcomeTypeAdapter outcomeTypeAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_outcome_type_layout);

        outcomeTypeAdapter = new OutcomeTypeAdapter(this, R.layout.outcome_type_row_layout);
        listView = (ListView) findViewById(R.id.addOutcomeTypeList);
        listView.setAdapter(outcomeTypeAdapter);
        JSON_STRING = getIntent().getExtras().getString(HomeActivity.OUTCOME_TYPE_JSON);
        try {
            jsonObject = new JSONObject(JSON_STRING);
            int count = 0;
            jsonArray = jsonObject.getJSONArray("outcome_type");
            String outcomeTypeName;
            Long no = 1L;
            while (count < jsonArray.length()) {
                JSONObject obj = jsonArray.getJSONObject(count);
                outcomeTypeName = obj.getString("outcomeTypeName");
                OutcomeType outcomeType = new OutcomeType();
                outcomeType.setNo(no++);
                outcomeType.setOutcomeTypeName(outcomeTypeName);
                outcomeTypeAdapter.add(outcomeType);
                count++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void addOutcomeType(View view) {
        Intent intent = new Intent(this, AddOutcomeTypeHomeActivity.class);
        startActivity(intent);
    }
}
