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

public class Feedback_view extends AppCompatActivity {

    ListView feedlist;

    ArrayList<String> list;
    ArrayAdapter<String> adapter;

    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_view);

        firebaseDatabase = FirebaseDatabase.getInstance();

        feedlist = (ListView) findViewById(R.id.userlist);

        list = new ArrayList<>();


        DatabaseReference databaseReference = firebaseDatabase.getReference().child("feedback");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    try {
                        list.add(snapshot.child("name").getValue().toString()+"\n"+snapshot.child("email").getValue().toString()+"\n"+snapshot.child("subject").getValue().toString()+"\n"+snapshot.child("message").getValue().toString());
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
        feedlist.setAdapter(adapter);
    }
}
