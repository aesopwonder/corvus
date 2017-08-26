package andrewnguyen.corvus.sync;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import andrewnguyen.corvus.Tab_Fragments.Global;


/**
 * Created by andrewnguyen on 10/23/16.
 */

public class Get_Count_Async extends AsyncTask<String, Void, String> {
    Context ctx;
    public static final String KEY_TOKEN = "count";

    public Get_Count_Async(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected String doInBackground(String... params) {
        //TODO change the ip when switching networks
        String url_s = "http://172.16.30.56:8888/get_count.php";
        String method = params[0];
        if (method.equals("get_count")) {
            String arbitrary = "";
            try {
                URL url = new URL(url_s);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data =
                        URLEncoder.encode("arbitrary", "UTF-8") + "=" + URLEncoder.encode(arbitrary, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(IS, "iso-8859-1"));
                String response = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                }
                if(response != null && !response.isEmpty()) {
                    getJSON(response);
                }
                bufferedReader.close();
                IS.close();
                httpURLConnection.disconnect();
                return response;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return "No connection";
    }

    private void getJSON(String response) {
        JSONArray jsonArray;
        try {
            JSONObject jsonObject = new JSONObject(response);
            jsonArray = jsonObject.getJSONArray(KEY_TOKEN);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject data = jsonArray.getJSONObject(0);
                String token = data.getString(KEY_TOKEN);
                if(!token.equals("0")){
                    Global global = new Global();
                    global.add_Wallet_total();
//                    global.addAudio_usage();
                    System.out.println("Token = " + token);

                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {

    }
}

