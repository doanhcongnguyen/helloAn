package alpha.com.newhelllo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import alpha.com.newhelllo.R;

public class AddOutcomeHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_outcome_home_layout);
    }

    public void addNewOutcomeType(View view) {
        Toast.makeText(getApplicationContext(), "Add outcome type", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, AddOutcomeTypeActivity.class);
        startActivity(intent);
    }
}
