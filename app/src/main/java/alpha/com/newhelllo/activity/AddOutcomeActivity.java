package alpha.com.newhelllo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;

import com.adapter.OutcomeType;
import com.background.AddOutcome;
import com.util.Constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import alpha.com.newhelllo.R;

public class AddOutcomeActivity extends AppCompatActivity {

    String outcomeTypeJson;
    JSONObject jsonObject;
    JSONArray jsonArray;

    NumberPicker nprDay;
    NumberPicker nprMonth;
    NumberPicker nprYear;
    Spinner spnOutcomeType;
    EditText etAmount;
    EditText etNote;
    List<OutcomeType> outcomeTypeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_outcome_layout);

        spnOutcomeType = (Spinner) findViewById(R.id.spnOutcomeTypeId);

        outcomeTypeJson = getIntent().getExtras().getString(Constant.IntentExtraKey.outcomeTypeJson);
        outcomeTypeList = this.getOutcomeTypeList(outcomeTypeJson);
        String[] spinnerOutcomeTypeAdapterList = this.getSpinnerOutcomeTypeAdapterList(outcomeTypeList);

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerOutcomeTypeAdapterList);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnOutcomeType.setAdapter(spinnerArrayAdapter);

        nprDay = (NumberPicker) findViewById(R.id.nprDay);
        nprMonth = (NumberPicker) findViewById(R.id.nprMonth);
        nprYear = (NumberPicker) findViewById(R.id.nprYear);
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        nprDay.setMinValue(1);
        nprDay.setMaxValue(31);
        nprDay.setValue(day);
        nprMonth.setMinValue(1);
        nprMonth.setMaxValue(12);
        nprMonth.setValue(month == 12 ? 1 : month + 1);
        nprYear.setMinValue(year - 5);
        nprYear.setMaxValue(year + 5);
        nprYear.setValue(year);

        etAmount = (EditText) findViewById(R.id.etAmount);
        etNote = (EditText) findViewById(R.id.etNote);
    }

    private String[] getSpinnerOutcomeTypeAdapterList(List<OutcomeType> outcomeTypeList) {
        List<String> result = new ArrayList<>();
        for (OutcomeType outcomeType: outcomeTypeList) {
            result.add(outcomeType.getOutcomeTypeName());
        }
        return result.toArray(new String[result.size()]);
    }

    private List<OutcomeType> getOutcomeTypeList(String outcomeTypeJson) {
        List<OutcomeType> result = new ArrayList<>();
        try {
            jsonObject = new JSONObject(outcomeTypeJson);
            int count = 0;
            jsonArray = jsonObject.getJSONArray(Constant.ServerResponse.outcomeType);
            String outcomeTypeName;
            String outcomeTypeId;
            while (count < jsonArray.length()) {
                JSONObject obj = jsonArray.getJSONObject(count);
                outcomeTypeName = obj.getString(Constant.ServerResponse.OutcomeType.name);
                outcomeTypeId = obj.getString(Constant.ServerResponse.OutcomeType.id);
                OutcomeType outcomeType = new OutcomeType();
                outcomeType.setOutcomeTypeName(outcomeTypeName);
                outcomeType.setOutcomeTypeId(Long.valueOf(outcomeTypeId));
                result.add(outcomeType);
                count++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void addNewOutcomeType(View view) {
        int day = nprDay.getValue();
        int month = nprMonth.getValue();
        int year = nprYear.getValue();
        String outcomeTypeName = spnOutcomeType.getSelectedItem().toString();
        String outcomeTypeId = this.getOutcomeTypeIdFromName(outcomeTypeName);
        String amount = etAmount.getText().toString();
        String note = etNote.getText().toString();

        if (outcomeTypeId != null) {
            AddOutcome backgroundTask = new AddOutcome(this);
            backgroundTask.execute(String.valueOf(day), String.valueOf(month), String.valueOf(year), outcomeTypeId, amount, note);
            finish();
        }
    }

    private String getOutcomeTypeIdFromName(String outcomeTypeName) {
        for (OutcomeType outcomeType: outcomeTypeList) {
            if (outcomeTypeName.equals(outcomeType.getOutcomeTypeName())) {
                return outcomeType.getOutcomeTypeId().toString();
            }
        }
        return null;
    }
}
