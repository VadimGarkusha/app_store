package com.example.onlinestoreapplication;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    private boolean isFormValid = true;
    DataBaseHelper db;
    Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        db = new DataBaseHelper(this);
        SharedPreferences appPrefs = getSharedPreferences( "appPrefenreces", MODE_PRIVATE);
        final String userName = String.valueOf(appPrefs.getString("userName","null"));
        client = db.getClient(userName);

        final EditText username = findViewById(R.id.username);
        final EditText email = findViewById(R.id.email);
        final EditText password = findViewById(R.id.password);
        final EditText firstName = findViewById(R.id.firstName);
        final EditText lastName = findViewById(R.id.lastName);
        final EditText phoneNumber = findViewById(R.id.phoneNumber);
        final EditText address = findViewById(R.id.address);
        final EditText city = findViewById(R.id.city);
        final EditText postal = findViewById(R.id.postalCode);
        final EditText cardNum = findViewById(R.id.card);
        final EditText expData = findViewById(R.id.expiryDate);

        username.setText(client.userName);
        email.setText(client.email);
        password.setText(client.password);
        firstName.setText(client.firstName);
        lastName.setText(client.lastName);
        phoneNumber.setText(client.phoneNumber);
        address.setText(client.address);
        city.setText(client.city);
        postal.setText(client.postalCode);
        cardNum.setText(client.card);
        expData.setText(client.expiryDate);

        Button updateButton = findViewById(R.id.update_button);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Client clientToUpdate = new Client();
                if (isValid(username))clientToUpdate.userName = getText(username);
                if (isValid(password)) clientToUpdate.password = getText(password);
                if (isValid(firstName)) clientToUpdate.firstName = getText(firstName);
                if (isValid(lastName)) clientToUpdate.lastName = getText(lastName);
                if (isValid(phoneNumber)) clientToUpdate.phoneNumber = getText(phoneNumber);
                if (isValid(address)) clientToUpdate.address = getText(address);
                if (isValid(city)) clientToUpdate.city = getText(city);
                if (isValid(postal)) clientToUpdate.postalCode = getText(postal);
                if (isValid(cardNum)) clientToUpdate.card = getText(cardNum);
                if (isValid(expData)) clientToUpdate.expiryDate = getText(expData);
                if (isValid(email)) clientToUpdate.email = getText(email);

                boolean isUpdated = db.updateClientInfo(clientToUpdate);
                if(isUpdated)
                    Toast.makeText(ProfileActivity.this, "You successfully updates your info!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean isValid(EditText et) {
        if (et.getText().toString().length() == 0) {
            et.setError("Required!");
            isFormValid = false;
            return false;
        } else {
            return true;
        }
    }

    public String getText(EditText et) {
        return et.getText().toString();
    }

}
