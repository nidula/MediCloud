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
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditDeletePatient extends AppCompatActivity {

    Button updateBtn,deleteBtn,retriewBtn;
    EditText uid,fname,lname,gender,height,weight,birth,contactNo,emegency,address,email,blood,diseasesnow,prescriptions,allergy,operation,medications,alcohol,special,nic;

    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_delete_patient);

        firebaseDatabase = FirebaseDatabase.getInstance();
        updateBtn = (Button) findViewById(R.id.button23);
        deleteBtn = (Button) findViewById(R.id.button24);
        retriewBtn = (Button) findViewById(R.id.button22);
        uid = (EditText) findViewById(R.id.editText);
        fname = (EditText) findViewById(R.id.editText2);
        lname = (EditText) findViewById(R.id.editText9);
        gender = (EditText) findViewById(R.id.editText10);
        height = (EditText) findViewById(R.id.editText11);
        weight = (EditText) findViewById(R.id.editText12);
        nic = (EditText) findViewById(R.id.editText14);
        birth = (EditText) findViewById(R.id.editText22);
        contactNo = (EditText) findViewById(R.id.editText28);
        emegency = (EditText) findViewById(R.id.editText29);
        address = (EditText) findViewById(R.id.editText30);
        email = (EditText) findViewById(R.id.editText31);
        blood = (EditText) findViewById(R.id.editText32);
        diseasesnow = (EditText) findViewById(R.id.editText33);
        prescriptions = (EditText) findViewById(R.id.editText34);
        allergy = (EditText) findViewById(R.id.editText35);
        operation = (EditText) findViewById(R.id.editText36);
        medications = (EditText) findViewById(R.id.editText37);
        alcohol = (EditText) findViewById(R.id.editText38);
        special = (EditText) findViewById(R.id.editText39);

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
                            if(dataSnapshot.hasChild(userID))
                            {
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
                            else
                            {
                                Toast.makeText(EditDeletePatient.this, "Patient not found", Toast.LENGTH_SHORT).show();
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

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userID = uid.getText().toString();
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

                Toast.makeText(EditDeletePatient.this, "Patient Updated", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(EditDeletePatient.this, Doctor_main.class));
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseDatabase.getReference().child("patient").child(uid.getText().toString()).removeValue();

                Toast.makeText(EditDeletePatient.this, "Patient Deleted..", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(EditDeletePatient.this, MainActivity.class));
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
            String uidinput = uid.getText().toString().trim();
            updateBtn.setEnabled(!uidinput.isEmpty() && !emailinput.isEmpty() && !fnameinput.isEmpty() && !lnameinput.isEmpty() && !weightinput.isEmpty() && !genderinput.isEmpty() && !heightinput.isEmpty() && !contactinput.isEmpty() && !addressinput.isEmpty()&& !nicinput.isEmpty() && !birthinput.isEmpty() && !emegencyinput.isEmpty() && !bloodinput.isEmpty());
            deleteBtn.setEnabled(!uidinput.isEmpty());
            retriewBtn.setEnabled(!uidinput.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
