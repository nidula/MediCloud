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

public class Show_patient_details extends AppCompatActivity {

    Button retriewBtn;
    EditText uid;
    TextView fname,lname,gender,height,weight,birth,contactNo,emegency,address,email,blood,diseasesnow,prescriptions,allergy,operation,medications,alcohol,special,nic;

    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_patient_details);

        firebaseDatabase = FirebaseDatabase.getInstance();
        retriewBtn = (Button) findViewById(R.id.button32);
        uid = (EditText) findViewById(R.id.editText62);
        fname = (TextView) findViewById(R.id.textView104);
        lname = (TextView) findViewById(R.id.textView106);
        gender = (TextView) findViewById(R.id.textView108);
        height = (TextView) findViewById(R.id.textView110);
        weight = (TextView) findViewById(R.id.textView112);
        nic = (TextView) findViewById(R.id.textView114);
        birth = (TextView) findViewById(R.id.textView116);
        contactNo = (TextView) findViewById(R.id.textView118);
        emegency = (TextView) findViewById(R.id.textView120);
        address = (TextView) findViewById(R.id.textView122);
        email = (TextView) findViewById(R.id.textView124);
        blood = (TextView) findViewById(R.id.textView126);
        diseasesnow = (TextView) findViewById(R.id.textView128);
        prescriptions = (TextView) findViewById(R.id.textView130);
        allergy = (TextView) findViewById(R.id.textView132);
        operation = (TextView) findViewById(R.id.textView134);
        medications = (TextView) findViewById(R.id.textView136);
        alcohol = (TextView) findViewById(R.id.textView138);
        special = (TextView) findViewById(R.id.textView140);

        uid.addTextChangedListener(loginTextWatcher);

        retriewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    DatabaseReference reference = firebaseDatabase.getReference().child("patient");
                    reference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String userID = uid.getText().toString();
                            if(dataSnapshot.hasChild(userID)) {
                                fname.setText(dataSnapshot.child(userID).child("firstname").getValue().toString());
                                lname.setText(dataSnapshot.child(userID).child("lastname").getValue().toString());
                                gender.setText(dataSnapshot.child(userID).child("gender").getValue().toString());
                                height.setText(dataSnapshot.child(userID).child("height").getValue().toString());
                                weight.setText(dataSnapshot.child(userID).child("weight").getValue().toString());
                                nic.setText(dataSnapshot.child(userID).child("NIC").getValue().toString());
                                birth.setText(dataSnapshot.child(userID).child("birthday").getValue().toString());
                                contactNo.setText(dataSnapshot.child(userID).child("contact").getValue().toString());
                                emegency.setText(dataSnapshot.child(userID).child("emegencyNo").getValue().toString());
                                address.setText(dataSnapshot.child(userID).child("homeaddress").getValue().toString());
                                email.setText(dataSnapshot.child(userID).child("email").getValue().toString());
                                blood.setText(dataSnapshot.child(userID).child("bloodgroup").getValue().toString());
                                diseasesnow.setText(dataSnapshot.child(userID).child("diseases").getValue().toString());
                                prescriptions.setText(dataSnapshot.child(userID).child("prescriptions").getValue().toString());
                                allergy.setText(dataSnapshot.child(userID).child("allergy").getValue().toString());
                                operation.setText(dataSnapshot.child(userID).child("operations").getValue().toString());
                                medications.setText(dataSnapshot.child(userID).child("medications").getValue().toString());
                                alcohol.setText(dataSnapshot.child(userID).child("alcohol").getValue().toString());
                                special.setText(dataSnapshot.child(userID).child("specialnotes").getValue().toString());
                            }
                            else{
                                Toast.makeText(Show_patient_details.this, "Patient not found", Toast.LENGTH_SHORT).show();
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
    }

    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            String medicalinput = uid.getText().toString().trim();
            retriewBtn.setEnabled(!medicalinput.isEmpty());


        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
