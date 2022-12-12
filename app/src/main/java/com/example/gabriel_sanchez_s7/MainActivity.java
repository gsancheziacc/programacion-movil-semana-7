package com.example.gabriel_sanchez_s7;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gabriel_sanchez_s7.ui.login.LoginActivity;
import com.example.gabriel_sanchez_s7.ui.rutValidator.RutValidatorActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstances) {
        super.onCreate(savedInstances);

        SharedPreferences preferences = getSharedPreferences("global-preferences", Context.MODE_PRIVATE);
        boolean isLogged = preferences.getBoolean("isLogged", false);

        Intent myIntent;
        if(isLogged) {
            myIntent = new Intent(this, RutValidatorActivity.class);
        } else {
            myIntent = new Intent(this, LoginActivity.class);
        }
        startActivity(myIntent);

    }
}
