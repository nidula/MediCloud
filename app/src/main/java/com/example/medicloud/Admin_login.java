package com.example.medicloud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Admin_login extends AppCompatActivity {

    EditText username,password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        username = findViewById(R.id.editText40);
        password = findViewById(R.id.editText41);
        login = findViewById(R.id.button25);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }
    public void login(){
        Button btn9 = (Button) findViewById(R.id.button25);
        String user=username.getText().toString().trim();
        String pass=password.getText().toString().trim();
        if(user.equals("admin@gmail.com") && pass.equals("admin123"))
        {
            btn9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent int9 = new Intent(Admin_login.this, Admin.class);
                    startActivity(int9);
                }
            });
            Toast.makeText(this,"Welcome to MediCloud Admin Panel!", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this,"Username and Password incorrect", Toast.LENGTH_LONG).show();
        }
    }
}
