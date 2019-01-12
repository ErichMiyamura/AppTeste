package com.example.user.erichmiyamura.Login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.erichmiyamura.Home.HomeActivity;
import com.example.user.erichmiyamura.R;
import com.example.user.erichmiyamura.Register.RegisterActivity;
import com.example.user.erichmiyamura.Terms.TermsActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private int cont;
    private Button btnEntrar;
    private TextInputEditText email;
    private TextInputEditText senha;
    private TextView linkEsqueciSenha;
    private TextView linkCadastro;
    private TextView linkTermos;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null){
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        initViews();
        /*loginEmailSenha();*/

        String text = "Esqueceu a senha? Clique Aqui";
        SpannableString ss = new SpannableString(text);

        linkEsqueciSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
            }
        });

        linkCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        linkTermos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, TermsActivity.class));
            }
        });

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailValue = email.getText().toString();
                String passwordValue = senha.getText().toString();

                /*if (cont >= 3) {
                    Toast.makeText(getApplicationContext(), "Ultrapassou o limite de tentativas", Toast.LENGTH_SHORT).show();
                    cont++;
                }*/

                if (!emailValue.isEmpty() && !passwordValue.isEmpty()) {
                    firebaseAuth.signInWithEmailAndPassword(emailValue, passwordValue)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                                        finish();
                                    } else {
                                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                } if (cont >= 2) {
                    Toast.makeText(getApplicationContext(), "Ultrapassou o limite de tentativas", Toast.LENGTH_SHORT).show();
                } else {
                    cont++;
                }
            }
        });
    }

    private void initViews() {
        btnEntrar = findViewById(R.id.btn_entrar);
        email = findViewById(R.id.email);
        senha = findViewById(R.id.password);
        linkEsqueciSenha = findViewById(R.id.esqueci_a_senha);
        linkCadastro = findViewById(R.id.cadastro);
        linkTermos = findViewById(R.id.termos);
    }
}
