package com.example.a91755.yonachatbot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UnknownList extends AppCompatActivity {
ArrayList<String> mobileArray;
    DatabaseReference databaseQuestions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unknown_list);
        databaseQuestions = FirebaseDatabase.getInstance().getReference("unknown_questions");
        Query query = databaseQuestions.orderByChild("questions");
        mobileArray = new ArrayList<>();
        mobileArray.add("List of Unknown Questions asked by user:");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren())
                {
//                    Log.d("valuess",snapshot.getValue().toString());
                        mobileArray.add(snapshot.getValue(UnknownQuestions.class).getQuestions());

                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(UnknownList.this,
                        R.layout.activity_listview, mobileArray);

                ListView listView = (ListView) findViewById(R.id.questionsList);
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });


//        mobileArray.add("Apple");
//        mobileArray.add("Apple");
//        mobileArray.add("Apple");
//        mobileArray.add("Apple");







    }
}
