package com.example.medicloud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Pharmacist extends AppCompatActivity {

    Button btnpre,btnonline,clear1,clear2;
    EditText medicalID,orderID;
    TextView NIC1,presciption,NIC2,orders,address1,address2;

    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacist);

        firebaseDatabase = FirebaseDatabase.getInstance();

        btnpre = (Button) findViewById(R.id.button33);
        btnonline = (Button) findViewById(R.id.button34);
        medicalID = (EditText) findViewById(R.id.editText65);
        orderID = (EditText) findViewById(R.id.editText66);
        NIC1 = (TextView) findViewById(R.id.textView149);
        address1 = (TextView) findViewById(R.id.textView151);
        presciption = (TextView) findViewById(R.id.textView147);
        clear1 = (Button) findViewById(R.id.button37);
        NIC2 = (TextView) findViewById(R.id.textView150);
        address2 = (TextView) findViewById(R.id.textView152);
        orders = (TextView) findViewById(R.id.textView148);
        clear2 = (Button) findViewById(R.id.button38);

        medicalID.addTextChangedListener(loginTextWatcher);
        orderID.addTextChangedListener(loginTextWatcher);


        btnpre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    DatabaseReference reference = firebaseDatabase.getReference().child("patient");
                    reference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String userID = medicalID.getText().toString();
                            if(dataSnapshot.hasChild(userID)) {
                                NIC1.setText(dataSnapshot.child(userID).child("NIC").getValue().toString());
                                address1.setText(dataSnapshot.child(userID).child("homeaddress").getValue().toString());
                                presciption.setText(dataSnapshot.child(userID).child("prescriptions").getValue().toString());
                            }
                            else{
                                Toast.makeText(Pharmacist.this, "Patient not found", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });
                }
                catch (Exception e)
                {
                    Log.d("medicloud", "error : " + e.toString());
                }
            }
        });

        btnonline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    DatabaseReference reference = firebaseDatabase.getReference().child("Online_order");
                    reference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String userID = orderID.getText().toString();
                            if(dataSnapshot.hasChild(userID)) {
                                NIC2.setText(dataSnapshot.child(userID).child("NIC").getValue().toString());
                                address2.setText(dataSnapshot.child(userID).child("address").getValue().toString());
                                orders.setText(dataSnapshot.child(userID).child("details").getValue().toString());
                            }
                            else{
                                Toast.makeText(Pharmacist.this, "Patient not found", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });
                }
                catch (Exception e)
                {
                    Log.d("medicloud", "error : " + e.toString());
                }
            }
        });

        clear1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medicalID.setText("");
                address1.setText("");
                presciption.setText("");
                NIC1.setText("");
            }
        });

        clear2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderID.setText("");
                address2.setText("");
                orders.setText("");
                NIC2.setText("");
            }
        });
    }

    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            String medicalinput = medicalID.getText().toString().trim();
            String orderinput = orderID.getText().toString().trim();
            btnpre.setEnabled(!medicalinput.isEmpty());
            btnonline.setEnabled(!orderinput.isEmpty());


        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
