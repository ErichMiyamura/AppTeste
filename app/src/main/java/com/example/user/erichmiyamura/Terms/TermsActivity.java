package com.example.user.erichmiyamura.Terms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.user.erichmiyamura.Login.LoginActivity;
import com.example.user.erichmiyamura.R;

public class TermsActivity extends AppCompatActivity {

    private Button btnContinuar;
    private CheckBox checkBoxAgree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);

        initViews();
        accept();
    }

    private void accept() {
        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkBoxAgree.isChecked()) {
                    Toast.makeText(getApplicationContext(), "Você não concorda com o Termo", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                }
            }
        });
    }

    private void initViews() {
        btnContinuar = findViewById(R.id.btn_continuar);
        checkBoxAgree = findViewById(R.id.termo_aceito);
    }
}
