package com.example.medicloud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BMI_calculator extends AppCompatActivity {
    TextView result;
    EditText num1,num2;
    Button calculate,clear;

    float result_bmi,weight,height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_m_i_calculator);

        result=(TextView)findViewById(R.id.textResult);
        num1=(EditText)findViewById(R.id.editWeight);
        num2=(EditText)findViewById(R.id.editHeight);
        calculate=(Button)findViewById(R.id.calbmi);
        clear=(Button)findViewById(R.id.button10);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weight=Float.parseFloat(num1.getText().toString());
                height=Float.parseFloat(num2.getText().toString());
                result_bmi=weight/((height/100)*(height/100));
                result.setText(String.valueOf(result_bmi));
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1.setText("");
                num2.setText("");
                result.setText("");
            }
        });

    }
}
