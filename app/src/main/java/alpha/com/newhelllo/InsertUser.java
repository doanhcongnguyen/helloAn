package alpha.com.newhelllo;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class InsertUser extends AppCompatActivity {

    EditText etName, etEmail, etContact, etPassword;
    String name, email, contact, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_user);

        etName = (EditText) findViewById(R.id.name);
        etEmail = (EditText) findViewById(R.id.email);
        etContact = (EditText) findViewById(R.id.contact);
        etPassword = (EditText) findViewById(R.id.password);
    }

    public void insertUser(View view) {
        name = etName.getText().toString();
        email = etEmail.getText().toString();
        contact = etContact.getText().toString();
        password = etPassword.getText().toString();

        String method = "insertUser";
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(method, name, email, contact, password);
        finish();

    }

    class BackgroundTask extends AsyncTask<String, Void, String> {
        Context ctx;

        BackgroundTask(Context ctx) {
            this.ctx = ctx;
        }

        protected String doInBackground(String... params) {
            String reg_url = "https://doanh.000webhostapp.com/insertUsers.php";
            String method = params[0];
            if ("insertUser".equals(method)) {
                String name = params[1];
                String email = params[2];
                String contact = params[3];
                String password = params[4];
                try {
                    URL url = new URL(reg_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream os = httpURLConnection.getOutputStream();

                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

                    String data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" +
                            URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&" +
                            URLEncoder.encode("contact", "UTF-8") + "=" + URLEncoder.encode(contact, "UTF-8") + "&" +
                            URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    os.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    inputStream.close();
                    return "Registration success";

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
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
