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

public class RegisterActivity extends AppCompatActivity {

    private EditText userEmail,userPassword,userPassword2,userName;
    private TextView textView;
    private ProgressBar loadingProgress;
    private Button regBtn;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userEmail = findViewById(R.id.email);
        userPassword = findViewById(R.id.password);
        userPassword2 = findViewById(R.id.c_password);
        userName = findViewById(R.id.name);
        loadingProgress = findViewById(R.id.loading);
        regBtn = findViewById(R.id.btn_regist);
        textView = findViewById(R.id.textViewReg);

        mAuth = FirebaseAuth.getInstance();



            regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String email = userEmail.getText().toString();
                final String password = userPassword.getText().toString();
                final String password2 = userPassword2.getText().toString();
                final String name = userName.getText().toString();

                if (email.isEmpty() || name.isEmpty() || password.isEmpty() || !password.equals(password2)) {
                    showMessage("Пожалуйста исправьте все ошибки");
                    userName.setError("Поле объязательна к заполнению");
                    userEmail.setError("Ведите Ваш email");
                    userPassword.setError("Ведите Ваш пароль");
                    userPassword2.setError("Повторите пароль");

                }
                else {
                    CreateUserAccount(email,name,password);
                }


            }
        });

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            });




    }

    private void CreateUserAccount(String email, String name, String password) {
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            showMessage("Account created");
                            regBtn.setVisibility(View.VISIBLE);
                            loadingProgress.setVisibility(View.GONE);

                        }
                        else
                        {
                         showMessage("Account creation failed" + task.getException().getMessage());
                         regBtn.setVisibility(View.VISIBLE);
                         loadingProgress.setVisibility(View.GONE);
                        }
                    }
                });


    }

    private void showMessage(String message) {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();

    }
}