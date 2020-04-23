package com.example.medicloud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Online_pharmacy extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    EditText name;
    TextView aaa;
    EditText id;
    EditText number;
    EditText email;
    EditText address;
    EditText medicalID;
    EditText details;
    Button mButtonUpload;

    private int userID = 0;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_pharmacy);

        firebaseDatabase = FirebaseDatabase.getInstance();
//      firebaseDatabase.getReference().child("Online-order").child(""+1).child("userID").setValue(1);
//      firebaseDatabase.getReference().child("Online_order").child(""+1).child("name").setValue("nimal");

        name =(EditText)findViewById(R.id.editText_name);
        aaa = (TextView) findViewById(R.id.textView141);
        id =(EditText)findViewById(R.id.editText_id);
        number =(EditText) findViewById(R.id.editText_number);
        email =(EditText) findViewById(R.id.editText_email);
        address =(EditText) findViewById(R.id.editText_address);
        medicalID =(EditText) findViewById(R.id.editText_medical);
        details = (EditText) findViewById(R.id.editText61);
        mButtonUpload = (Button) findViewById(R.id.button_upload);

        buildUserID();

        mButtonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseDatabase.getReference().child("Online_order").child(String.valueOf(userID)).child("userID").setValue(userID);
                firebaseDatabase.getReference().child("Online_order").child(String.valueOf(userID)).child("name").setValue(name.getText().toString());
                firebaseDatabase.getReference().child("Online_order").child(String.valueOf(userID)).child("NIC").setValue(id.getText().toString());
                firebaseDatabase.getReference().child("Online_order").child(String.valueOf(userID)).child("phone number").setValue(number.getText().toString());
                firebaseDatabase.getReference().child("Online_order").child(String.valueOf(userID)).child("email").setValue(email.getText().toString());
                firebaseDatabase.getReference().child("Online_order").child(String.valueOf(userID)).child("address").setValue(address.getText().toString());
                firebaseDatabase.getReference().child("Online_order").child(String.valueOf(userID)).child("mediID").setValue(medicalID.getText().toString());
                firebaseDatabase.getReference().child("Online_order").child(String.valueOf(userID)).child("details").setValue(details.getText().toString());

                Toast.makeText(Online_pharmacy.this, "Order Accepted!", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(Add_patient.this, Add_patient.class));
            }
        });

    }

    private void buildUserID() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = firebaseDatabase.getReference().child("Online_order");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int j;
                for (j = 1; dataSnapshot.hasChild(String.valueOf(j)); j++) {
                }
                userID = j;
                Log.d("mediCloud", "userId..... : " + userID);
                aaa.setText("Order ID: " + userID);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

    }
    public void browser3 (View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/open?id=1yhme_dsGtHH7-8-fz-QhLq5DE2HQ4FkY"));
        startActivity(browserIntent);
    }
}
