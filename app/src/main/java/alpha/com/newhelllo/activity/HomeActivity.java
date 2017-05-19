package alpha.com.newhelllo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.background.GetOutcomeType;
import com.background.GetOutcomeTypeToCreateOutcome;

import alpha.com.newhelllo.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_outcome_type:
                outcomeType();
                return true;
            case R.id.action_outcome:
                outcome();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void outcomeType() {
        new GetOutcomeType(this).execute();
        finish();
    }

    public void outcome() {
        new GetOutcomeTypeToCreateOutcome(this).execute();
        finish();
    }
}
