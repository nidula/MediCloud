package com.example.medicloud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPass extends AppCompatActivity {

    ProgressBar progressBar;
    EditText userEmail;
    Button userPass;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);

        progressBar = findViewById(R.id.progressBar3);
        userEmail = findViewById(R.id.etUserEmail);
        userPass = findViewById(R.id.btnForgotPass);
        firebaseAuth = FirebaseAuth.getInstance();

        userEmail.addTextChangedListener(loginTextWatcher);

        userPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                firebaseAuth.sendPasswordResetEmail(userEmail.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            progressBar.setVisibility(View.GONE);
                            if (task.isSuccessful()) {
                                Toast.makeText(ForgotPass.this,
                                        "Password send to your email", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(ForgotPass.this, "Enter a valid Email Address"
                                        , Toast.LENGTH_LONG).show();
                            }
                        }
                    });


            }
        });
    }

    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String emailinput = userEmail.getText().toString().trim();
            userPass.setEnabled(!emailinput.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}


