package alpha.com.newhelllo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.util.Constant;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

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
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(outcomeTypeName);
        finish();
    }

    class BackgroundTask extends AsyncTask<String, Void, String> {
        Context ctx;

        BackgroundTask(Context ctx) {
            this.ctx = ctx;
        }

        protected String doInBackground(String... params) {
            String outcomeTypeName = params[0];
            try {
                URL url = new URL(Constant.Url.updateOutcomeType);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                String data = URLEncoder.encode("outcomeTypeId", "UTF-8") + "=" + URLEncoder.encode(outcomeTypeId, "UTF-8") + "&" +
                        URLEncoder.encode("outcomeTypeName", "UTF-8") + "=" + URLEncoder.encode(outcomeTypeName, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                inputStream.close();
                return "Edit outcome type successfully!";

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ctx, HomeActivity.class);
            startActivity(intent);

//            Intent intent = new Intent(ctx, AddOutcomeTypeActivity.class);
//            startActivity(intent);

//            GetOutcomeType get = new GetOutcomeType(ctx);
//            get.execute();
//            finish();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}
