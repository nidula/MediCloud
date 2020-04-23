package com.example.medicloud;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class Main_page extends AppCompatActivity {

    ViewFlipper v_flipper;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        int images[] = {R.drawable.bg_2,R.drawable.bg_1,R.drawable.image_1,R.drawable.image_4};

        v_flipper = findViewById(R.id.v_flipper);

        for (int image: images){
            flipperImages(image);
        }
    }
    public void flipperImages(int image){
        ImageView imageView=new ImageView(this);
        imageView.setImageResource(image);

        v_flipper.addView(imageView);
        v_flipper.setFlipInterval(2000);//3s
        v_flipper.setAutoStart(true);

        //animation
        v_flipper.setInAnimation(this,android.R.anim.slide_in_left);
        v_flipper.setOutAnimation(this,android.R.anim.slide_out_right);

        Button btn1 = (Button) findViewById(R.id.button6);
        Button btn2 = (Button) findViewById(R.id.button4);
        Button btn3 = (Button) findViewById(R.id.button13);
        Button btn5 = (Button) findViewById(R.id.button8);
        Button btn6 = (Button) findViewById(R.id.button);
        Button btn7 = (Button) findViewById(R.id.button7);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int1 = new Intent(Main_page.this, BMI_calculator.class);
                startActivity(int1);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int2 = new Intent(Main_page.this, Health_tips.class);
                startActivity(int2);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int3 = new Intent(Main_page.this, Online_pharmacy.class);
                startActivity(int3);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int5 = new Intent(Main_page.this, Add_feeedback.class);
                startActivity(int5);
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int6 = new Intent(Main_page.this, Show_patient_details.class);
                startActivity(int6);
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int7= new Intent(Main_page.this, Contact.class);
                startActivity(int7);
            }
        });
    }
    public void browser1 (View view){
        Intent browserIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.echannelling.com/Echannelling/index"));
        startActivity(browserIntent);
    }

}
