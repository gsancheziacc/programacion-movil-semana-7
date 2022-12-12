package com.example.gabriel_sanchez_s7.ui.rutValidator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.gabriel_sanchez_s7.R;
import com.example.gabriel_sanchez_s7.data.api.Caller;

import java.net.MalformedURLException;
import java.net.URL;

public class RutValidatorActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rut_validator);

        EditText txtRut = findViewById(R.id.txtRut);
        txtRut.requestFocus();

        FrameLayout frameResult = findViewById(R.id.frameResult);
        frameResult.setVisibility(View.INVISIBLE);

        Button btnValidate = findViewById(R.id.btnValidate);
        btnValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frameResult.setVisibility(View.INVISIBLE);
                String rut = txtRut.getText().toString().trim();

                if(rut.isEmpty()) {
                    Toast.makeText(RutValidatorActivity.this, "Debe ingresar un rut", Toast.LENGTH_SHORT).show();
                    return;
                }

                TableLayout table = findViewById(R.id.tableLayout);
                TextView txtRutValidated = table.findViewWithTag("txtValidatedRutResult");
                txtRutValidated.setText(txtRut.getText().toString().trim());

                TextView txtStateRutResult = table.findViewWithTag("txtStateRutResult");

                try {
                    URL url = new URL("https://api.libreapi.cl/rut/validate?rut=" + rut);
                    new Caller(txtStateRutResult, frameResult).execute(url);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
