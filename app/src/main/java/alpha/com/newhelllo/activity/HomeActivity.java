package alpha.com.newhelllo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import alpha.com.newhelllo.R;

public class HomeActivity extends AppCompatActivity {

    public static final String OUTCOME_TYPE_JSON = "OUTCOME_TYPE_JSON";

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

    public class GetOutcomeType extends AsyncTask<Void, Void, String> {
        Context ctx;
        String jsonUrl = "https://doanh.000webhostapp.com/getOutcomeType.php";

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(jsonUrl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String jsonString;
                while ((jsonString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(jsonString + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        public GetOutcomeType(Context ctx) {
            this.ctx = ctx;
        }

        @Override
        protected void onPostExecute(String result) {
            Intent intent = new Intent(ctx, AddOutcomeTypeActivity.class);
            intent.putExtra(OUTCOME_TYPE_JSON, result);
            startActivity(intent);
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
