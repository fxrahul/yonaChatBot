package com.example.a91755.yonachatbot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class PostStudentGeneralDetails extends AppCompatActivity {
    private FirebaseAuth auth;
    DatabaseReference databaseMarks,databaseUsers;
    private String studentEmail,studentEmailHyphen;
    EditText admission_type,age,branch,contact_number,fees_payment_method,hsc_marks,ssc_marks;
    TextView inputStudentInfo;
    Button btnAttendance;
    private String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_student_general_details);
        studentEmailHyphen = getIntent().getExtras().getString("studentEmail");
        String[] studentemailarray = studentEmailHyphen.split("-");
        studentEmail = studentemailarray[0];
        auth = FirebaseAuth.getInstance();
        databaseMarks = FirebaseDatabase.getInstance().getReference("student_details");
        databaseUsers = FirebaseDatabase.getInstance().getReference("users");
        admission_type = findViewById(R.id.input_admission_type);
        age = findViewById(R.id.input_age);
        branch = findViewById(R.id.input_branch);
        contact_number = findViewById(R.id.input_contact_number);
        fees_payment_method = findViewById(R.id.input_fees_payment_method);
        hsc_marks = findViewById(R.id.input_hsc_marks);
        ssc_marks =findViewById(R.id.input_ssc_marks);
            btnAttendance = findViewById(R.id.btn_attendance);
        inputStudentInfo = findViewById(R.id.input_student_info);

        inputStudentInfo.setText(studentEmailHyphen);




        btnAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                addMarks(admission_type.getText().toString(),age.getText().toString(),branch.getText().toString(),contact_number.getText().toString(), fees_payment_method.getText().toString(),hsc_marks.getText().toString(),ssc_marks.getText().toString(),studentEmail);

            }
        });
    }

    public void addMarks(String a,String b,String c,String d,String e ,String f ,String g, String Email){

        id = databaseMarks.push().getKey();

        StudentGeneralDetail attendance = new StudentGeneralDetail(id, a,b,c,d,e,f,g,Email);

        databaseMarks.child(id).setValue(attendance);

        //for adding attendance key to users database using email condition

//        Query query = databaseUsers.orderByChild("email").equalTo(studentEmail);
//
//        query.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
//                    String userID = childSnapshot.getKey();
//                    MarksUserCopy user = childSnapshot.getValue(MarksUserCopy.class);
//                    String email =user.getEmail();
//                    String a_id = user.getMarksID();
//                    String m_id = user.getAttendanceID();
//                    String u_id= user.getId();
//                    String name = user.getName();
//                    String password = user.getPassword();
//                    String rollno = user.getRollNo();
//                    String uniquekey= user.getUniquekey();
//                    String username = user.getUsername();
//                    StudentGeneralDetailsCopyUsers userCopy = new StudentGeneralDetailsCopyUsers(id,m_id,a_id,u_id,email,name,password,rollno,uniquekey,username);
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

        Toast.makeText(PostStudentGeneralDetails.this,"Student Details Successfully Added",Toast.LENGTH_LONG).show();
        startActivity(new Intent(PostStudentGeneralDetails.this,AdminDashboardActivity.class));
        finish();
    }
}
