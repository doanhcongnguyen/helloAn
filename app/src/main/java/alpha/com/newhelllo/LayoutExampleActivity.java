package alpha.com.newhelllo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class LayoutExampleActivity extends AppCompatActivity {

    Button clickMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_example);

        clickMe = (Button) findViewById(R.id.myButton);
    }

    public void clickMe(View view) {
        clickMe.setText("Text changed!!");
    }
}
