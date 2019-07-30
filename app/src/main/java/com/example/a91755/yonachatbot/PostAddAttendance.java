package com.example.a91755.yonachatbot;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class PostAddAttendance extends AppCompatActivity {

    private FirebaseAuth auth;
    DatabaseReference databaseMarks,databaseUsers;
    private String studentEmailHyphen,studentEmail;
    EditText inputMaths,inputSem1,inputSem2,inputSem3,inputSem4,inputSem5,inputSem6;
    TextView inputStudentInfo;
    Button btnAttendance;
    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_add_attendance);
        studentEmailHyphen = getIntent().getExtras().getString("studentEmail");
        String[] studentemailarray = studentEmailHyphen.split("-");
        studentEmail = studentemailarray[0];
        auth = FirebaseAuth.getInstance();
        databaseMarks = FirebaseDatabase.getInstance().getReference("marks");
        databaseUsers = FirebaseDatabase.getInstance().getReference("users");
        btnAttendance = findViewById(R.id.btn_attendance);
        inputStudentInfo = findViewById(R.id.input_student_info);
        inputMaths = findViewById(R.id.input_maths_marks);
        inputSem1 = findViewById(R.id.input_sem_1);
        inputSem2 = findViewById(R.id.input_sem_2);
        inputSem3 = findViewById(R.id.input_sem_3);
        inputSem4 = findViewById(R.id.input_sem_4);
        inputSem5 = findViewById(R.id.input_sem_5 );
        inputSem6 = findViewById(R.id.input_sem_6);

        inputStudentInfo.setText(studentEmailHyphen);




        btnAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mathsMarks = inputMaths.getText().toString().trim()+ "/100";
                String Sem1Marks = inputSem1.getText().toString().trim()+ " " + "CGPA";
                String Sem2Marks = inputSem2.getText().toString().trim()+ " " + "CGPA";
                String Sem3Marks = inputSem3.getText().toString().trim()+ " " + "CGPA";
                String Sem4Marks = inputSem4.getText().toString().trim()+ " " + "CGPA";
                String Sem5Marks = inputSem5.getText().toString().trim()+ " " + "CGPA";
                String Sem6Marks = inputSem6.getText().toString().trim()+ " " + "CGPA";


                if (TextUtils.isEmpty(mathsMarks)) {
                    Toast.makeText(getApplicationContext(), "Enter Maths Marks!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(Sem1Marks)) {
                    Toast.makeText(getApplicationContext(), "Enter Sem 1 Marks!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Sem2Marks)) {
                    Toast.makeText(getApplicationContext(), "Enter Sem 2 Marks!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Sem3Marks)) {
                    Toast.makeText(getApplicationContext(), "Enter Sem 3 Marks!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Sem4Marks)) {
                    Toast.makeText(getApplicationContext(), "Enter Sem 4 Marks!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Sem5Marks)) {
                    Toast.makeText(getApplicationContext(), "Enter Sem 5 Marks!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(Sem6Marks)) {
                    Toast.makeText(getApplicationContext(), "Enter Sem 6 Marks!", Toast.LENGTH_SHORT).show();
                    return;
                }
                addMarks(mathsMarks,Sem1Marks,Sem2Marks,Sem3Marks,Sem4Marks,Sem5Marks,Sem6Marks);

            }
        });
    }

    public void addMarks(String mathsMarks,String Sem1Marks,String Sem2Marks,String Sem3Marks,String Sem4Marks,String Sem5Marks,String Sem6Marks){

        id = databaseMarks.push().getKey();

        Attendance attendance = new Attendance(id, studentEmail,mathsMarks,Sem1Marks,Sem2Marks,Sem3Marks,Sem4Marks,Sem5Marks,Sem6Marks);

        databaseMarks.child(id).setValue(attendance);

        //for adding attendance key to users database using email condition

//        Query query = databaseUsers.orderByChild("email").equalTo(studentEmail);
//
//        query.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
//                    String userID = childSnapshot.getKey();
//                    User user = childSnapshot.getValue(User.class);
//                    String email =user.getEmail();
//                    String u_id= user.getId();
//                    String name = user.getName();
//                    String password = user.getPassword();
//                    String rollno = user.getRollNo();
//                    String uniquekey= user.getUniquekey();
//                    String username = user.getUsername();
//                    UserCopy userCopy = new UserCopy(id,u_id,email,name,password,rollno,uniquekey,username);
//
//                databaseUsers.child(userID).setValue(userCopy);
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
                Toast.makeText(PostAddAttendance.this, "Marks Successfully Added",Toast.LENGTH_LONG).show();
                startActivity(new Intent(PostAddAttendance.this,AdminDashboardActivity.class));
                finish();

    }
}
