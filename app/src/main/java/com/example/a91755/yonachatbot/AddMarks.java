package com.example.a91755.yonachatbot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AddMarks extends AppCompatActivity {

    private FirebaseAuth auth;
    DatabaseReference databaseUsers;
    ArrayList<String> arrayList;
    Spinner spinner;
    Button btnAttendance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_marks);
        spinner = (Spinner) findViewById(R.id.attendance_spinner);
        btnAttendance = (Button) findViewById(R.id.addAttendanceBtn);
        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();
        databaseUsers = FirebaseDatabase.getInstance().getReference("users");
        Query query = databaseUsers.orderByChild("email");
        arrayList = new ArrayList<>();
        arrayList.add("Select a Student Email");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot : dataSnapshot.getChildren())
                {
//                    arrayList.add(snapshot.getValue(Student.class).getEmail() + " " + "-" + "  " + "Name: " +snapshot.getValue(Student.class).getName());
                    arrayList.add(snapshot.getValue(Student.class).getEmail());
//                    arrayList.add(snapshot.getValue());
                }

                ArrayAdapter<String> numAdapter = new ArrayAdapter<String>(AddMarks.this,
                        android.R.layout.simple_spinner_dropdown_item, arrayList);
                spinner.setAdapter(numAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String studentEmail = spinner.getSelectedItem().toString();
                Intent i = new Intent(AddMarks.this,PostMarks.class);
//                i.putExtra("studentInfo",studentInfo);
                i.putExtra("studentEmail",studentEmail);
                startActivity(i);
            }
        });

    }
}
