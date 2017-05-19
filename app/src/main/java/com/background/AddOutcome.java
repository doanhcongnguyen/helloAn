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
 * Created by alpha on 5/19/2017.
 */

public class AddOutcome extends AsyncTask<String, Void, String> {
    Context ctx;

    public AddOutcome(Context ctx) {
        this.ctx = ctx;
    }

    protected String doInBackground(String... params) {

        String day = params[0];
        String month = params[1];
        String year = params[2];
        String outcomeTypeId = params[3];
        String amount = params[4];
        String note = params[5];
        try {
            URL url = new URL(Constant.Url.insertOutcome);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            OutputStream os = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, Constant.utf8));
            String data = URLEncoder.encode(Constant.ServerResponse.Outcome.day, Constant.utf8) + "=" + URLEncoder.encode(day, Constant.utf8) + "&"
                    + URLEncoder.encode(Constant.ServerResponse.Outcome.month, Constant.utf8) + "=" + URLEncoder.encode(month, Constant.utf8) + "&"
                    + URLEncoder.encode(Constant.ServerResponse.Outcome.year, Constant.utf8) + "=" + URLEncoder.encode(year, Constant.utf8) + "&"
                    + URLEncoder.encode(Constant.ServerResponse.OutcomeType.id, Constant.utf8) + "=" + URLEncoder.encode(outcomeTypeId, Constant.utf8) + "&"
                    + URLEncoder.encode(Constant.ServerResponse.Outcome.amount, Constant.utf8) + "=" + URLEncoder.encode(amount, Constant.utf8) + "&"
                    + URLEncoder.encode(Constant.ServerResponse.Outcome.note, Constant.utf8) + "=" + URLEncoder.encode(note, Constant.utf8);
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            os.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            inputStream.close();
            return "Add outcome successfully!";

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
