package alpha.com.newhelllo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.background.GetOutcomeType;

import alpha.com.newhelllo.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
    }

    public void addOutcomeType(View view) {
        GetOutcomeType get = new GetOutcomeType(this);
        get.execute();
        finish();
    }

    public void addOutcome(View view) {
        Intent intent = new Intent(this, AddOutcomeActivity.class);
        startActivity(intent);
    }

    public void viewOutcomeList(View view) {
        Intent intent = new Intent(this, ViewOutcomeListActivity.class);
        startActivity(intent);
    }
}
