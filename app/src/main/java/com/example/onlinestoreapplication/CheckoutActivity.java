package com.example.onlinestoreapplication;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CheckoutActivity extends AppCompatActivity {
    DataBaseHelper db;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        db = new DataBaseHelper(this);

        SharedPreferences appPrefs = getSharedPreferences( "appPrefenreces", MODE_PRIVATE);
        userName = String.valueOf(appPrefs.getString("userName","null"));

        Client client = db.getClient(userName);


    }
}
