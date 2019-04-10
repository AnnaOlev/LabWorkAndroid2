package com.example.androidlab2;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class JSONParser {

    public List<ListItem> getJSON() {

        List<ListItem> items = new ArrayList<>();

        URL url;
        StringBuilder response = new StringBuilder();
        try {
            url = new URL("https://raw.githubusercontent.com/wesleywerner/ancient-tech/02decf875616dd9692b31658d92e64a20d99f816/src/data/techs.ruleset.json");
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("invalid url");
        }

        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(false);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

            // handle the response
            int status = conn.getResponseCode();
            if (status != 200) {
                throw new IOException("Post failed with error code " + status);
            } else {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
            String jsonString= response.toString();
            Log.i(TAG, "Received JSON: " + jsonString);
            try {
                JSONArray jsonBody = new JSONArray(jsonString);
                parseItems(items, jsonBody);
            } catch (JSONException je){
                Log.e(TAG, "Failed to parse JSON", je);
            }

        }

        return items;
    }

    private void parseItems(List<ListItem> items, JSONArray jsonArray)
            throws JSONException {
        for (int i = 1; i < jsonArray.length(); i++) {
            JSONObject jsonObjectItem = jsonArray.getJSONObject(i);
            ListItem item = new ListItem();
            item.setGraphics(jsonObjectItem.getString("graphic"));
            item.setName(jsonObjectItem.getString("name"));
            if (jsonObjectItem.has("helptext")) {
                item.setHelptext(jsonObjectItem.getString("helptext"));
            }
            items.add(item);
        }
    }
}
