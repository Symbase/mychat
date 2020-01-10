package com.mizes.chat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {
    private EditText userMail, userPassword;
    private TextView textView;
    private Button btnLogin;
    private ProgressBar loginProgress;
    private FirebaseAuth mAuth;
    private Intent MainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userMail = findViewById(R.id.login_mail);
        userPassword = findViewById(R.id.login_password);
        btnLogin = findViewById(R.id.loginBtn);
        loginProgress = findViewById(R.id.loginProgress);
        textView = findViewById(R.id.textViewLogin);
        mAuth = FirebaseAuth.getInstance();
        MainActivity = new Intent(this,MainActivity.class);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final String mail = userMail.getText().toString();
                final String password = userPassword.getText().toString();

                if (mail.isEmpty() || password.isEmpty()) {
                    showMessage("Пожалуйста исправьте все ошибки");
                    userMail.setError("Ведите Ваш email");
                    userPassword.setError("Ведите Ваш пароль");

                }
                else
                {
                    signIn(mail,password);
                }

            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();

            }
        });


    }



    private void signIn(String mail, String password) {
        mAuth.signInWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    loginProgress.setVisibility(View.VISIBLE);
                    btnLogin.setVisibility(View.GONE);
                    updateUI();
                }
                else
                    showMessage(task.getException().getMessage());

            }
        });

        }



    private void updateUI() {
        startActivity(MainActivity);
        finish();


    }

    private void showMessage(String text) {
        Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            updateUI();

        }

    }


}
