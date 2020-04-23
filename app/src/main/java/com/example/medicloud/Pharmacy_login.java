package com.example.medicloud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Pharmacy_login extends AppCompatActivity {

    EditText username,password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy_login);

        username = findViewById(R.id.editText44);
        password = findViewById(R.id.editText45);
        login = findViewById(R.id.button27);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }
    public void login(){
        Button btn9 = (Button) findViewById(R.id.button27);
        String user=username.getText().toString().trim();
        String pass=password.getText().toString().trim();
        if(user.equals("pharmacy@gmail.com") && pass.equals("pharmacy123"))
        {


            btn9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent int9 = new Intent(Pharmacy_login.this, Pharmacist.class);
                    startActivity(int9);
                }
            });
            Toast.makeText(this,"Welcome to MediCloud Pharmacy System!", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this,"Username and Password incorrect", Toast.LENGTH_LONG).show();
        }
    }
}
