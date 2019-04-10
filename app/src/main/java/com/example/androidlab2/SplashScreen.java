package com.example.androidlab2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new FetchItemsTask().execute();

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @SuppressLint("StaticFieldLeak")
    private class FetchItemsTask extends AsyncTask<Void,Void,List<ListItem>> {
        @Override
        protected List<ListItem> doInBackground(Void... params) {

            return  new JSONParser().getJSON();
        }

        @Override
        protected void onPostExecute(List<ListItem> items) {
            ArrayList<ListItem> mItems = (ArrayList<ListItem>) items;
            Intent i = new Intent(getBaseContext(), ListActivity.class);
            i.putParcelableArrayListExtra("itemsarray", mItems);
            startActivity(i);
            finish();
        }
    }
}
