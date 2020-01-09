package com.mizes.chat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;



public class LoginActivity extends AppCompatActivity {
    EditText emailId, password;
    Button btnSignUp;
    TextView tvSignIn;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mFirebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.email);
        password = findViewById(R.id.password);
        password = findViewById(R.id.c_password);
        btnSignUp = findViewById(R.id.btn_sign);
        tvSignIn = findViewById(R.id.btn_regist);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailId.getText().toString();
                String pwd = password.getText().toString();
                if (email.isEmpty()) {
                    emailId.setError("Please enter email id");
                    emailId.requestFocus();
                } else if (pwd.isEmpty()) {
                    password.setError("Pleasse enter your password");
                    password.requestFocus();

                } else if (email.isEmpty() && pwd.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Fields Are Empty", Toast.LENGTH_SHORT);
                } else if (!(email.isEmpty() && pwd.isEmpty())) {
                    mFirebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "SignUp Unsuccessful, Please Try Again", Toast.LENGTH_SHORT);

                            } else {
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            }
                        }
                    });
                } else {
                    Toast.makeText(LoginActivity.this, "Error Occurred!", Toast.LENGTH_SHORT);
                }
            }
        });
        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}
