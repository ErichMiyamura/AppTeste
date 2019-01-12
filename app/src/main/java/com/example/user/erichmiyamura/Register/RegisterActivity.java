package com.example.user.erichmiyamura.Register;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.user.erichmiyamura.Login.LoginActivity;
import com.example.user.erichmiyamura.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private Button btnRegistrar;
    private FirebaseAuth firebaseAuth;
    private TextInputEditText textInputEditCadastroEmail;
    private TextInputEditText textInputEditCadastroSenha;
    private TextInputEditText textInputEditConfirmarSenha;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutSenha;
    private TextInputLayout textInputLayoutConfirmarSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initViews();
        initObjects();

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailValue = textInputLayoutEmail.getEditText().getText().toString();
                String passwordValue = textInputLayoutSenha.getEditText().getText().toString();
                String confirmPasswordValue = textInputLayoutConfirmarSenha.getEditText().getText().toString();

                if (TextUtils.isEmpty(emailValue)) {
                    Toast.makeText(getApplicationContext(), "Por favor, preencha os campos obrigatórios.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(passwordValue)) {
                    Toast.makeText(getApplicationContext(), "Por favor, preencha os campos obrigatórios.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(confirmPasswordValue)) {
                    Toast.makeText(getApplicationContext(), "Por favor, preencha os campos obrigatórios.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (passwordValue.length() < 6) {
                    Toast.makeText(getApplicationContext(), "A senha deve ter pelo menos 6 caracteres.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (confirmPasswordValue.equals(passwordValue)){
                    firebaseAuth.createUserWithEmailAndPassword(emailValue, passwordValue)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                        finish();
                                    } else {
                                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {
                    Toast.makeText(getApplicationContext(), "Desculpe, mas as senhas não combinam.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initObjects() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    private void initViews() {
        btnRegistrar = findViewById(R.id.btn_registrar);
        textInputEditCadastroEmail = findViewById(R.id.cadastro_email);
        textInputEditCadastroSenha = findViewById(R.id.cadastro_senha);
        textInputEditConfirmarSenha = findViewById(R.id.confirmar_senha);
        textInputLayoutEmail = findViewById(R.id.text_cadastro_email);
        textInputLayoutSenha = findViewById(R.id.text_cadastro_senha);
        textInputLayoutConfirmarSenha = findViewById(R.id.text_confirmar_senha);
    }
}
