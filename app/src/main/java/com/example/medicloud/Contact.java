package com.example.medicloud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Contact extends AppCompatActivity {

    private static final int REQUEST_CALL = 1;
    private TextView callText,suwatext;
    private Button callbtn,suwa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        Button btn= (Button)findViewById(R.id.button41);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent= new Intent(Intent.ACTION_SEND);
                emailIntent.putExtra(Intent.EXTRA_EMAIL,new String[]{"medicloud@gmail.com"});
//                emailIntent.putExtra(Intent.EXTRA_CC,new String[]{"lmn@gmail.com"});
//                emailIntent.putExtra(Intent.EXTRA_SUBJECT,"subject");
//                emailIntent.putExtra(Intent.EXTRA_TEXT,"body");

                emailIntent.setType("message/rfc822");
                startActivity(Intent.createChooser(emailIntent,"Choose email client"));
            }
        });
        callText = findViewById(R.id.button39);
        callbtn = findViewById(R.id.button39);
        callbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                CallButton1();
            }
        });


    }

    private void CallButton1() {
        String number = callText.getText().toString();
        if (number.trim().length() > 0) {
            if (ContextCompat.checkSelfPermission(Contact.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Contact.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                CallButton1();
            } else {
                Toast.makeText(this, "permission_Denied", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void browser5 (View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://medi-cloud-e5843.web.app/"));
        startActivity(browserIntent);
    }

}
