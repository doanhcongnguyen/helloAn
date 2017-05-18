package alpha.com.newhelllo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.adapter.OutcomeType;
import com.adapter.OutcomeTypeAdapter;
import com.util.Constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import alpha.com.newhelllo.R;

public class OutcomeTypeActivity extends AppCompatActivity {

    String JSON_STRING;
    JSONObject jsonObject;
    JSONArray jsonArray;
    OutcomeTypeAdapter outcomeTypeAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.outcome_type_layout);

        outcomeTypeAdapter = new OutcomeTypeAdapter(this, R.layout.outcome_type_row_layout);
        listView = (ListView) findViewById(R.id.addOutcomeTypeList);
        listView.setAdapter(outcomeTypeAdapter);
        JSON_STRING = getIntent().getExtras().getString(Constant.IntentExtraKey.outcomeTypeJson);
        try {
            jsonObject = new JSONObject(JSON_STRING);
            int count = 0;
            jsonArray = jsonObject.getJSONArray(Constant.ServerResponse.outcomeType);
            String outcomeTypeName;
            String outcomeTypeId;
            Long no = 1L;
            while (count < jsonArray.length()) {
                JSONObject obj = jsonArray.getJSONObject(count);
                outcomeTypeName = obj.getString(Constant.ServerResponse.OutcomeType.name);
                outcomeTypeId = obj.getString(Constant.ServerResponse.OutcomeType.id);
                OutcomeType outcomeType = new OutcomeType();
                outcomeType.setNo(no++);
                outcomeType.setOutcomeTypeName(outcomeTypeName);
                outcomeType.setOutcomeTypeId(Long.valueOf(outcomeTypeId));
                outcomeTypeAdapter.add(outcomeType);
                count++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void addOutcomeType(View view) {
        Intent intent = new Intent(this, AddOutcomeTypeActivity.class);
        startActivity(intent);
    }
}
