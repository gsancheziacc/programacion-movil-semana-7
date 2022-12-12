package com.example.gabriel_sanchez_s7.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.gabriel_sanchez_s7.R;
import com.example.gabriel_sanchez_s7.ui.rutValidator.RutValidatorActivity;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText txtUsername = findViewById(R.id.txtUsername);
        EditText txtPassword = findViewById(R.id.txtPassword);
        txtUsername.requestFocus();

        SharedPreferences preferences = getSharedPreferences("global-preferences", Context.MODE_PRIVATE);
        String savedUsername = preferences.getString("username", "");

        if(!savedUsername.isEmpty()) {
            txtUsername.setText(savedUsername);
            txtPassword.requestFocus();
        }

        Button btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validateUser(txtUsername.getText().toString(), txtPassword.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "Usuario o contraseña invalidos", Toast.LENGTH_SHORT).show();
                    return;
                }

                addUserNameGlobalPreferences(txtUsername.getText().toString());
                Intent myIntent = new Intent(LoginActivity.this, RutValidatorActivity.class);
                startActivity(myIntent);
            }
        });
    }

    private boolean validateUser(String username, String password) {
        String demoUserName = getString(R.string.user_account_test);
        String demoPassword = getString(R.string.password_account_test);

        //just validate specific user account for demo
        return username.trim().equals(demoUserName) && password.trim().equals(demoPassword);
    }

    private void addUserNameGlobalPreferences(String username) {
        SharedPreferences preferences = getSharedPreferences("global-preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("username", username.trim());
        editor.apply();
    }
}
