package com.example.gabriel_sanchez_s7.data.api;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class Caller extends AsyncTask<URL, Void, String> {

    @SuppressLint("StaticFieldLeak")
    TextView txtStateRutResult;
    @SuppressLint("StaticFieldLeak")
    FrameLayout frameResult;

    public Caller(TextView txtStateRutResult, FrameLayout frameResult) {
        this.txtStateRutResult = txtStateRutResult;
        this.frameResult = frameResult;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        frameResult.setVisibility(View.INVISIBLE);
    }

    @Override
    protected String doInBackground(URL... urls) {
        String result = "";

        try {
            HttpURLConnection con = (HttpURLConnection)urls[0].openConnection();
            int statusCode = con.getResponseCode();

            if (statusCode == 200) {
                result = "Rut Válido";
            }
            else {
                result = "Rut Inválido";
            }

        } catch (IOException e) {
            result = "Error interno";
        }

        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        txtStateRutResult.setText(result);
        frameResult.setVisibility(View.VISIBLE);
    }
}
