package com.example.medicloud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class Add_patient extends AppCompatActivity {

    Button addPatientBtn;
    TextView userIdShow;
    EditText fname,lname,gender,height,weight,birth,contactNo,emegency,address,email,blood,diseasesnow,prescriptions,allergy,operation,medications,alcohol,special,nic;
    private int userID = 0;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        firebaseDatabase = FirebaseDatabase.getInstance();
//      firebaseDatabase.getReference().child("patient").child(""+1).child("userID").setValue(1);
//      firebaseDatabase.getReference().child("patient").child(""+1).child("fname").setValue("nimal");

        addPatientBtn = (Button) findViewById(R.id.button17);
        userIdShow = (TextView) findViewById(R.id.textView61);
        fname = (EditText) findViewById(R.id.editText3);
        lname = (EditText) findViewById(R.id.editText4);
        gender = (EditText) findViewById(R.id.editText5);
        height = (EditText) findViewById(R.id.editText6);
        weight = (EditText) findViewById(R.id.editText7);
        nic = (EditText) findViewById(R.id.editText8);
        birth = (EditText) findViewById(R.id.editText27);
        contactNo = (EditText) findViewById(R.id.editText24);
        emegency = (EditText) findViewById(R.id.editText25);
        address = (EditText) findViewById(R.id.editText26);
        email = (EditText) findViewById(R.id.editText23);
        blood = (EditText) findViewById(R.id.editText13);
        diseasesnow = (EditText) findViewById(R.id.editText15);
        prescriptions = (EditText) findViewById(R.id.editText16);
        allergy = (EditText) findViewById(R.id.editText17);
        operation = (EditText) findViewById(R.id.editText18);
        medications = (EditText) findViewById(R.id.editText19);
        alcohol = (EditText) findViewById(R.id.editText20);
        special = (EditText) findViewById(R.id.editText21);

        email.addTextChangedListener(loginTextWatcher);
        height.addTextChangedListener(loginTextWatcher);
        fname.addTextChangedListener(loginTextWatcher);
        lname.addTextChangedListener(loginTextWatcher);
        gender.addTextChangedListener(loginTextWatcher);
        weight.addTextChangedListener(loginTextWatcher);
        nic.addTextChangedListener(loginTextWatcher);
        address.addTextChangedListener(loginTextWatcher);
        birth.addTextChangedListener(loginTextWatcher);
        blood.addTextChangedListener(loginTextWatcher);
        contactNo.addTextChangedListener(loginTextWatcher);
        emegency.addTextChangedListener(loginTextWatcher);


        buildUserID();

        addPatientBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseDatabase.getReference().child("patient").child(String.valueOf(userID)).child("userID").setValue(userID);
                firebaseDatabase.getReference().child("patient").child(String.valueOf(userID)).child("firstname").setValue(fname.getText().toString());
                firebaseDatabase.getReference().child("patient").child(String.valueOf(userID)).child("lastname").setValue(lname.getText().toString());
                firebaseDatabase.getReference().child("patient").child(String.valueOf(userID)).child("gender").setValue(gender.getText().toString());
                firebaseDatabase.getReference().child("patient").child(String.valueOf(userID)).child("height").setValue(height.getText().toString());
                firebaseDatabase.getReference().child("patient").child(String.valueOf(userID)).child("weight").setValue(weight.getText().toString());
                firebaseDatabase.getReference().child("patient").child(String.valueOf(userID)).child("NIC").setValue(nic.getText().toString());
                firebaseDatabase.getReference().child("patient").child(String.valueOf(userID)).child("birthday").setValue(birth.getText().toString());
                firebaseDatabase.getReference().child("patient").child(String.valueOf(userID)).child("contact").setValue(contactNo.getText().toString());
                firebaseDatabase.getReference().child("patient").child(String.valueOf(userID)).child("emegencyNo").setValue(emegency.getText().toString());
                firebaseDatabase.getReference().child("patient").child(String.valueOf(userID)).child("homeaddress").setValue(address.getText().toString());
                firebaseDatabase.getReference().child("patient").child(String.valueOf(userID)).child("email").setValue(email.getText().toString());
                firebaseDatabase.getReference().child("patient").child(String.valueOf(userID)).child("bloodgroup").setValue(blood.getText().toString());
                firebaseDatabase.getReference().child("patient").child(String.valueOf(userID)).child("diseases").setValue(diseasesnow.getText().toString());
                firebaseDatabase.getReference().child("patient").child(String.valueOf(userID)).child("prescriptions").setValue(prescriptions.getText().toString());
                firebaseDatabase.getReference().child("patient").child(String.valueOf(userID)).child("allergy").setValue(allergy.getText().toString());
                firebaseDatabase.getReference().child("patient").child(String.valueOf(userID)).child("operations").setValue(operation.getText().toString());
                firebaseDatabase.getReference().child("patient").child(String.valueOf(userID)).child("medications").setValue(medications.getText().toString());
                firebaseDatabase.getReference().child("patient").child(String.valueOf(userID)).child("alcohol").setValue(alcohol.getText().toString());
                firebaseDatabase.getReference().child("patient").child(String.valueOf(userID)).child("specialnotes").setValue(special.getText().toString());

                Toast.makeText(Add_patient.this, "New patient added", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(Add_patient.this, Add_patient.class));
            }
        });
    }

    private void buildUserID() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference().child("patient");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int j;
                for (j = 1; dataSnapshot.hasChild(String.valueOf(j)); j++) {
                }
                userID = j;
                Log.d("mediCloud", "userId..... : " + userID);
                userIdShow.setText("Patient ID: " + userID);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String emailinput = email.getText().toString().trim();
            String weightinput = weight.getText().toString().trim();
            String fnameinput = fname.getText().toString().trim();
            String lnameinput = lname.getText().toString().trim();
            String genderinput = gender.getText().toString().trim();
            String heightinput = height.getText().toString().trim();
            String nicinput = nic.getText().toString().trim();
            String addressinput = address.getText().toString().trim();
            String birthinput = birth.getText().toString().trim();
            String bloodinput = blood.getText().toString().trim();
            String contactinput = contactNo.getText().toString().trim();
            String emegencyinput = emegency.getText().toString().trim();
            addPatientBtn.setEnabled(!emailinput.isEmpty() && !fnameinput.isEmpty() && !lnameinput.isEmpty() && !weightinput.isEmpty() && !genderinput.isEmpty() && !heightinput.isEmpty() && !contactinput.isEmpty() && !addressinput.isEmpty()&& !nicinput.isEmpty() && !birthinput.isEmpty() && !emegencyinput.isEmpty() && !bloodinput.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
