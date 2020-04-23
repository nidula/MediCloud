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

public class Userlog_view extends AppCompatActivity {

    ListView userlist;

    ArrayList<String> list;
    ArrayAdapter<String> adapter;

    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlog_view);

        firebaseDatabase = FirebaseDatabase.getInstance();

        userlist = (ListView) findViewById(R.id.userlist2);

        list = new ArrayList<>();


        DatabaseReference databaseReference = firebaseDatabase.getReference().child("users");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    try {
                        list.add(snapshot.child("userID").getValue().toString()+"\n"+snapshot.child("name").getValue().toString()+"\n"+snapshot.child("phone").getValue().toString()+"\n"+snapshot.child("position").getValue().toString());
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
