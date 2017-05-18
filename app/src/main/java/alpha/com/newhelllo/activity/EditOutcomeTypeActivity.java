package alpha.com.newhelllo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.background.UpdateOutcomeType;
import com.util.Constant;

import alpha.com.newhelllo.R;

public class EditOutcomeTypeActivity extends AppCompatActivity {

    private EditText ettOutcomeTypeName;
    private String outcomeTypeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_outcome_type_layout);

        ettOutcomeTypeName = (EditText) findViewById(R.id.ettEditOutcomeTypeName);

        Intent intent = getIntent();
        outcomeTypeId = intent.getStringExtra(Constant.EditOutcomeTypeValue.id);
        String outcomeTypeName = intent.getStringExtra(Constant.EditOutcomeTypeValue.name);
        ettOutcomeTypeName.setText(outcomeTypeName);

    }

    public void editOutcomeType(View view) {
        String outcomeTypeName = ettOutcomeTypeName.getText().toString();
        UpdateOutcomeType backgroundTask = new UpdateOutcomeType(this);
        backgroundTask.execute(outcomeTypeName, outcomeTypeId);
        finish();
    }
}
