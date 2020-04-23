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

public class Add_feeedback extends AppCompatActivity {

    Button submitbtn;
    TextView name,email,subject,message;
    private int feedId = 0;

    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_feeedback);

        firebaseDatabase = FirebaseDatabase.getInstance();
//        firebaseDatabase.getReference().child("feedback").child(""+1).child("fid").setValue(1);
//        firebaseDatabase.getReference().child("feedback").child(""+1).child("name").setValue("nimal");

        submitbtn = (Button) findViewById(R.id.button2);
        name = (EditText) findViewById(R.id.editText48);
        email = (EditText) findViewById(R.id.editText49);
        subject= (EditText) findViewById(R.id.editText51);
        message = (EditText) findViewById(R.id.editText50);

        email.addTextChangedListener(loginTextWatcher);
        name.addTextChangedListener(loginTextWatcher);
        subject.addTextChangedListener(loginTextWatcher);
        message.addTextChangedListener(loginTextWatcher);


        buildfeedId();
        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseDatabase.getReference().child("feedback").child(String.valueOf(feedId)).child("fid").setValue(feedId);
                firebaseDatabase.getReference().child("feedback").child(String.valueOf(feedId)).child("name").setValue(name.getText().toString());
                firebaseDatabase.getReference().child("feedback").child(String.valueOf(feedId)).child("email").setValue(email.getText().toString());
                firebaseDatabase.getReference().child("feedback").child(String.valueOf(feedId)).child("subject").setValue(subject.getText().toString());
                firebaseDatabase.getReference().child("feedback").child(String.valueOf(feedId)).child("message").setValue(message.getText().toString());

                Toast.makeText(Add_feeedback.this, "Thank you for your feedback!", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(Add_feeedback.this, Main_page.class));
            }
        });
    }
    private void buildfeedId() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference().child("feedback");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int j;
                for (j = 1; dataSnapshot.hasChild(String.valueOf(j)); j++) {
                }
                feedId = j;
                Log.d("firebaseCrud", "userId..... : " + feedId);
//                useridShow.setText("UserId: " + feedId);
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
            String nameinput = name.getText().toString().trim();
            String subjectinput = subject.getText().toString().trim();
            String messageinput = message.getText().toString().trim();
            submitbtn.setEnabled(!emailinput.isEmpty() && !nameinput.isEmpty() && !subjectinput.isEmpty() && !messageinput.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
