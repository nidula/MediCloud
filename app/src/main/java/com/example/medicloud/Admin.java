package com.example.medicloud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Admin extends AppCompatActivity {

    ProgressBar progressBar;
    EditText email,name,NIC,gender,position,phone,address;
    EditText password;
    TextView userIdShow;
    Button signup,clear;
    FirebaseAuth firebaseAuth;
    private int userID = 0;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        firebaseDatabase = FirebaseDatabase.getInstance();
//        firebaseDatabase.getReference().child("users").child(""+1).child("userID").setValue(1);
//        firebaseDatabase.getReference().child("users").child(""+1).child("name").setValue("nimal");

        Button btn1 = (Button) findViewById(R.id.button29);
        Button btn2 = (Button) findViewById(R.id.button5);
        Button btn3 = (Button) findViewById(R.id.button26);
        Button btn4 = (Button) findViewById(R.id.button43);

        progressBar = findViewById(R.id.progressBar);
        userIdShow = (TextView) findViewById(R.id.textView92);
        name = findViewById(R.id.editText42);
        NIC = findViewById(R.id.editText43);
        gender = findViewById(R.id.editText47);
        position = findViewById(R.id.editText52);
        phone = findViewById(R.id.editText53);
        address = findViewById(R.id.editText46);
        email = findViewById(R.id.editTextemail);
        password = findViewById(R.id.editTextpass);
        signup = findViewById(R.id.buttonsign);
        clear = (Button) findViewById(R.id.button44);

        firebaseAuth = FirebaseAuth.getInstance();


        email.addTextChangedListener(loginTextWatcher);
        password.addTextChangedListener(loginTextWatcher);
        name.addTextChangedListener(loginTextWatcher);
        NIC.addTextChangedListener(loginTextWatcher);
        gender.addTextChangedListener(loginTextWatcher);
        position.addTextChangedListener(loginTextWatcher);
        phone.addTextChangedListener(loginTextWatcher);
        address.addTextChangedListener(loginTextWatcher);

        buildUserID();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);

                firebaseDatabase.getReference().child("users").child(String.valueOf(userID)).child("userID").setValue(userID);
                firebaseDatabase.getReference().child("users").child(String.valueOf(userID)).child("name").setValue(name.getText().toString());
                firebaseDatabase.getReference().child("users").child(String.valueOf(userID)).child("nic").setValue(NIC.getText().toString());
                firebaseDatabase.getReference().child("users").child(String.valueOf(userID)).child("gender").setValue(gender.getText().toString());
                firebaseDatabase.getReference().child("users").child(String.valueOf(userID)).child("position").setValue(position.getText().toString());
                firebaseDatabase.getReference().child("users").child(String.valueOf(userID)).child("phone").setValue(phone.getText().toString());
                firebaseDatabase.getReference().child("users").child(String.valueOf(userID)).child("address").setValue(address.getText().toString());
                firebaseDatabase.getReference().child("users").child(String.valueOf(userID)).child("email").setValue(email.getText().toString());
//                firebaseDatabase.getReference().child("users").child(String.valueOf(userID)).child("password").setValue(password.getText().toString());

                Toast.makeText(Admin.this, "New user added", Toast.LENGTH_SHORT).show();

                firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(),
                    password.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.GONE);
                            if (task.isSuccessful()) {
                                firebaseAuth.getCurrentUser().sendEmailVerification()
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful()){
                                                    Toast.makeText(Admin.this, "Registered successfully. Please check your email for verification",
                                                            Toast.LENGTH_LONG).show();
                                                    email.setText("");
                                                    password.setText("");
                                                }else{
                                                    Toast.makeText(Admin.this,  task.getException().getMessage(),
                                                            Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        });
                            } else {
                                Toast.makeText(Admin.this, task.getException().getMessage(),
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    });

            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int1 = new Intent(Admin.this, Feedback_view.class);
                startActivity(int1);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int2 = new Intent(Admin.this, MainActivity.class);
                startActivity(int2);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int3 = new Intent(Admin.this, Edit_delete_user.class);
                startActivity(int3);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int4 = new Intent(Admin.this, Userlog_view.class);
                startActivity(int4);
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setText("");
                NIC.setText("");
                gender.setText("");
                position.setText("");
                phone.setText("");
                address.setText("");
                email.setText("");
                password.setText("");
            }
        });


    }

    private void buildUserID() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference().child("users");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int j;
                for (j = 1; dataSnapshot.hasChild(String.valueOf(j)); j++) {
                }
                userID = j;
                Log.d("mediCloud", "userId..... : " + userID);
                userIdShow.setText("User ID: " + userID);
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
            String passinput = password.getText().toString().trim();
            String nameinput = name.getText().toString().trim();
            String nicinput = NIC.getText().toString().trim();
            String genderinput = gender.getText().toString().trim();
            String positioninput = position.getText().toString().trim();
            String phoneinput = phone.getText().toString().trim();
            String addressinput = address.getText().toString().trim();
            signup.setEnabled(!emailinput.isEmpty() && !passinput.isEmpty() && !nameinput.isEmpty() && !nicinput.isEmpty() && !genderinput.isEmpty() && !positioninput.isEmpty() && !phoneinput.isEmpty() && !addressinput.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
