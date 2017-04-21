package alpha.com.newhelllo;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    String jsonString;
    String jsonStringParse;

    public static final String JSON_DATA = "JSON_DATA";
    public static final String EXTRA_MESSAGE = "extraMessage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getJsonData(View view) {
        new BackgroundTask().execute();
    }

    public void parseJsonData(View view) {
        Intent intent = new Intent(this, DisplayListView.class);
        intent.putExtra(JSON_DATA, jsonStringParse);
        startActivity(intent);
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void insertUser(View view) {
        Intent intent = new Intent(this, InsertUser.class);
        startActivity(intent);
    }

    class BackgroundTask extends AsyncTask<Void, Void, String> {

        String jsonUrl = "https://doanh.000webhostapp.com/getUsersData.php";

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(jsonUrl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();

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

        public BackgroundTask() {
            super();
        }

        @Override
        protected void onPostExecute(String result) {
            TextView textView = (TextView) findViewById(R.id.jsonTextView);
            textView.setText(result);
            jsonStringParse = result;
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
