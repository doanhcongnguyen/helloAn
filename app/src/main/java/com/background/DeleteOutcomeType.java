package com.background;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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

import alpha.com.newhelllo.activity.HomeActivity;

/**
 * Created by alpha on 5/18/2017.
 */

public class DeleteOutcomeType extends AsyncTask<String, Void, String> {

    Context ctx;

    public DeleteOutcomeType(Context ctx) {
        this.ctx = ctx;
    }

    protected String doInBackground(String... params) {
        String outcomeTypeId = params[0];
        try {
            URL url = new URL(Constant.Url.deleteOutcomeType);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            OutputStream os = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, Constant.utf8));
            String data = URLEncoder.encode(Constant.ServerResponse.OutcomeType.id, Constant.utf8) + "=" + URLEncoder.encode(outcomeTypeId, Constant.utf8);
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            os.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            inputStream.close();
            return "Delete outcome type successfully!";
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(ctx, result, Toast.LENGTH_SHORT).show();
        // TODO: change to navigate to list outcome type screen
        Intent intent = new Intent(ctx, HomeActivity.class);
        ctx.startActivity(intent);
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
