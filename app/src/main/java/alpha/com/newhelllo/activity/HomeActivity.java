package alpha.com.newhelllo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.background.GetOutcome;
import com.background.GetOutcomeType;

import alpha.com.newhelllo.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
    }

    public void addOutcomeType(View view) {
        new GetOutcomeType(this).execute();
        finish();
    }

    public void addOutcome(View view) {
//        Intent intent = new Intent(this, AddOutcomeActivity.class);
//        startActivity(intent);
        new GetOutcome(this).execute();
        finish();
    }

    public void viewOutcomeList(View view) {
        Intent intent = new Intent(this, ViewOutcomeListActivity.class);
        startActivity(intent);
    }
}
