package com.background;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.util.Constant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import alpha.com.newhelllo.activity.AddOutcomeActivity;


/**
 * Created by alpha on 5/16/2017.
 */

public class GetOutcome extends AsyncTask<Void, Void, String> {

    Context ctx;

    @Override
    protected String doInBackground(Void... voids) {
        try {
            URL url = new URL(Constant.Url.getOutcome);
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

    public GetOutcome(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    protected void onPostExecute(String result) {
        Intent intent = new Intent(ctx, AddOutcomeActivity.class);
        intent.putExtra(Constant.IntentExtraKey.outcomeJson, result);
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
