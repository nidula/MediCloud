package com.example.medicloud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Patient_view extends AppCompatActivity {

    ListView userlist;

    ArrayList<String> list;
    ArrayAdapter<String> adapter;

    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_view);

        firebaseDatabase = FirebaseDatabase.getInstance();

        userlist = (ListView) findViewById(R.id.userlist1);

        list = new ArrayList<>();


        DatabaseReference databaseReference = firebaseDatabase.getReference().child("patient");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    try {
                        list.add(snapshot.child("userID").getValue().toString()+"\n"+snapshot.child("firstname").getValue().toString()+"\n"+snapshot.child("contact").getValue().toString()+"\n"+snapshot.child("specialnotes").getValue().toString());
                        adapter.notifyDataSetChanged();
                    }catch (Exception e){
                        Log.d("mediCloud", "error : " + e.toString());
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        });



        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
        userlist.setAdapter(adapter);
    }
}
