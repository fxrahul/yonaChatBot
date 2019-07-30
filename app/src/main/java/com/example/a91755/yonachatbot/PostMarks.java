package com.example.a91755.yonachatbot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class PostMarks extends AppCompatActivity {

    private FirebaseAuth auth;
    DatabaseReference databaseMarks,databaseUsers;
    private String studentEmail,studentEmailHyphen,marksId;
    EditText inputMaths,inputSem1;
    TextView inputStudentInfo;
    Button btnAttendance;
    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_marks);
        studentEmailHyphen = getIntent().getExtras().getString("studentEmail");
        String[] studentemailarray = studentEmailHyphen.split("-");
        studentEmail = studentemailarray[0];
        auth = FirebaseAuth.getInstance();
        databaseMarks = FirebaseDatabase.getInstance().getReference("attendance");
        databaseUsers = FirebaseDatabase.getInstance().getReference("users");
        btnAttendance = findViewById(R.id.btn_attendance);
        inputStudentInfo = findViewById(R.id.input_student_info);
        inputMaths = findViewById(R.id.input_cummulative_attendance);
        inputSem1 = findViewById(R.id.input_monthly_attendance);
        inputStudentInfo.setText(studentEmailHyphen);




        btnAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mathsMarks = inputMaths.getText().toString().trim()+ "%";
                String Sem1Marks = inputSem1.getText().toString().trim()+ "%";

                if (TextUtils.isEmpty(mathsMarks)) {
                    Toast.makeText(getApplicationContext(), "Enter Cummulative Attendance!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(Sem1Marks)) {
                    Toast.makeText(getApplicationContext(), "Enter  Monthly attendance!", Toast.LENGTH_SHORT).show();
                    return;
                }
                addMarks(mathsMarks,Sem1Marks,studentEmail);

            }
        });
    }

    public void addMarks(String mathsMarks,String Sem1Marks,String Email){
        final String maths = mathsMarks;
        String sem1 = Sem1Marks;
        String email = Email;
        Query query = databaseMarks.orderByChild("email").equalTo(email);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                   Marks marks1 = childSnapshot.getValue(Marks.class);
                   marksId = marks1.getId();

                }
                Log.d("marksId",marksId);
                databaseMarks.child(marksId).removeValue();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
        id = databaseMarks.push().getKey();

        Marks attendance = new Marks(id, maths,sem1,email);

        databaseMarks.child(id).setValue(attendance);

        //for adding attendance key to users database using email condition

//        Query query = databaseUsers.orderByChild("email").equalTo(studentEmail);
//
//        query.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
//                    String userID = childSnapshot.getKey();
//                    UserCopy user = childSnapshot.getValue(UserCopy.class);
//                    String email =user.getEmail();
//                    String a_id = user.getMarksID();
//                    String u_id= user.getId();
//                    String name = user.getName();
//                    String password = user.getPassword();
//                    String rollno = user.getRollNo();
//                    String uniquekey= user.getUniquekey();
//                    String username = user.getUsername();
//                    MarksUserCopy userCopy = new MarksUserCopy(id,a_id,u_id,email,name,password,rollno,uniquekey,username);
//
//                    databaseUsers.child(userID).setValue(userCopy);
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
        Toast.makeText(PostMarks.this,"Marks Successfully Added",Toast.LENGTH_LONG).show();
        startActivity(new Intent(PostMarks.this,AdminDashboardActivity.class));
        finish();

    }
}
