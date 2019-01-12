package com.example.user.erichmiyamura.Login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.user.erichmiyamura.Home.HomeActivity;
import com.example.user.erichmiyamura.R;
import com.example.user.erichmiyamura.Register.RegisterActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {

    private Button btnAvancar;
    private TextInputEditText textInputEditForgotPassword;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        initViews();

        firebaseAuth = FirebaseAuth.getInstance();

        btnAvancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailValue = textInputEditForgotPassword.getText().toString();

                if (TextUtils.isEmpty(emailValue)) {
                    Toast.makeText(getApplicationContext(), "Por favor, preencha o campo obrigatório.", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    firebaseAuth.sendPasswordResetEmail(emailValue).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(ForgotPasswordActivity.this, "Por favor, verifique o email",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(ForgotPasswordActivity.this, LoginActivity.class));
                            } else {
                                String message = task.getException().getMessage();
                                Toast.makeText(ForgotPasswordActivity.this, "Erro" + message,Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    private void initViews() {
        btnAvancar = findViewById(R.id.btn_avancar);
        textInputEditForgotPassword = findViewById(R.id.forgot_password);
    }
}
