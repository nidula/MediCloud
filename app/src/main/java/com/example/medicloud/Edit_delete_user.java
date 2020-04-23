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
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Edit_delete_user extends AppCompatActivity {

    Button updateBtn,deleteBtn,retriewBtn;
    EditText uid,name,NIC,gender,position,phone,address;

    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_delete_user);

        firebaseDatabase = FirebaseDatabase.getInstance();
        updateBtn = (Button) findViewById(R.id.button31);
        deleteBtn = (Button) findViewById(R.id.button30);
        retriewBtn = (Button) findViewById(R.id.button28);
        uid = (EditText) findViewById(R.id.editText54);
        name = (EditText) findViewById(R.id.editText55);
        NIC = (EditText) findViewById(R.id.editText56);
        gender = (EditText) findViewById(R.id.editText57);
        position = (EditText) findViewById(R.id.editText58);
        phone = (EditText) findViewById(R.id.editText59);
        address = (EditText) findViewById(R.id.editText60);

        uid.addTextChangedListener(loginTextWatcher);
        name.addTextChangedListener(loginTextWatcher);
        NIC.addTextChangedListener(loginTextWatcher);
        position.addTextChangedListener(loginTextWatcher);
        gender.addTextChangedListener(loginTextWatcher);
        phone.addTextChangedListener(loginTextWatcher);
        address.addTextChangedListener(loginTextWatcher);

        retriewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    DatabaseReference reference = firebaseDatabase.getReference().child("users");
                    reference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String userID = uid.getText().toString();
                            if(dataSnapshot.hasChild(userID))
                            {
                                name.setText(dataSnapshot.child(userID).child("name").getValue().toString());
                                NIC.setText(dataSnapshot.child(userID).child("nic").getValue().toString());
                                gender.setText(dataSnapshot.child(userID).child("gender").getValue().toString());
                                position.setText(dataSnapshot.child(userID).child("position").getValue().toString());
                                phone.setText(dataSnapshot.child(userID).child("phone").getValue().toString());
                                address.setText(dataSnapshot.child(userID).child("address").getValue().toString());
                            }
                            else
                            {
                                Toast.makeText(Edit_delete_user.this, "User not found", Toast.LENGTH_SHORT).show();
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
            public void onClick(View v) {
                String userID = uid.getText().toString();
                firebaseDatabase.getReference().child("users").child(String.valueOf(userID)).child("name").setValue(name.getText().toString());
                firebaseDatabase.getReference().child("users").child(String.valueOf(userID)).child("nic").setValue(NIC.getText().toString());
                firebaseDatabase.getReference().child("users").child(String.valueOf(userID)).child("gender").setValue(gender.getText().toString());
                firebaseDatabase.getReference().child("users").child(String.valueOf(userID)).child("position").setValue(position.getText().toString());
                firebaseDatabase.getReference().child("users").child(String.valueOf(userID)).child("phone").setValue(phone.getText().toString());
                firebaseDatabase.getReference().child("users").child(String.valueOf(userID)).child("address").setValue(address.getText().toString());

                Toast.makeText(Edit_delete_user.this, "User Updated", Toast.LENGTH_SHORT).show();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseDatabase.getReference().child("users").child(uid.getText().toString()).removeValue();

                Toast.makeText(Edit_delete_user.this, "User Deleted..", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            String nameinput = name.getText().toString().trim();
            String genderinput = gender.getText().toString().trim();
            String positioninput = position.getText().toString().trim();
            String nicinput = NIC.getText().toString().trim();
            String addressinput = address.getText().toString().trim();
            String phoneinput = phone.getText().toString().trim();
            String uidinput = uid.getText().toString().trim();
            updateBtn.setEnabled(!uidinput.isEmpty());
            deleteBtn.setEnabled(!uidinput.isEmpty());
            retriewBtn.setEnabled(!uidinput.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
