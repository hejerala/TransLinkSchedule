package com.example.pg08hector.pg08arturohector_md2_final.DataManagement;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;

/**
 * Created by pg08hector on 13/12/2016.
 */

public class ConnectionManager extends AsyncTask<String , Void ,String> {

    public AsyncResponse delegate = null;

    public ConnectionManager(AsyncResponse delegate){
        this.delegate = delegate;
    }

    @Override
    protected String doInBackground(String... params) {
        InputStream inputStream = null;

        HttpURLConnection urlConnection = null;

        //String parameters;

        String result = "";
        try {
                /* forming th java.net.URL object */
            URL url = new URL(params[0]);
            //parameters = params[1];

            urlConnection = (HttpURLConnection) url.openConnection();

                             /* optional request header */
            urlConnection.setRequestProperty("Content-Type", "application/json");

                /* optional request header */
            urlConnection.setRequestProperty("Accept", "application/json");

                /* for Post request */
            urlConnection.setRequestMethod("GET");

            //urlConnection.setDoOutput(true); // Set Http method to POST
//            byte[] postDataBytes = parameters.getBytes("UTF-8");
//            urlConnection.setChunkedStreamingMode(postDataBytes.length); // Use default chunk size

            // Write serialized JSON data to output stream.
//            OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
//            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
//            writer.write(parameters);

            // Close streams and disconnect.
//            writer.close();
//            out.close();
            //urlConnection.disconnect();

            int statusCode = urlConnection.getResponseCode();

                /* 200 represents HTTP OK */
            if (statusCode ==  200) {

                inputStream = new BufferedInputStream(urlConnection.getInputStream());

                String response = convertInputStreamToString(inputStream);

                //result = parseResult(response);

                result = response; // Successful

            }else{
                inputStream = urlConnection.getErrorStream();
                result = "0"; //"Failed to fetch data!";
            }

        } catch (Exception e) {
            //Log.d(TAG, e.getLocalizedMessage());
            e.printStackTrace();
        }

        return result; //"Failed to fetch data!";
    }

    @Override
    protected void onPostExecute(String result) {
        delegate.processFinish(result);
    }

    private String convertInputStreamToString(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }

}
