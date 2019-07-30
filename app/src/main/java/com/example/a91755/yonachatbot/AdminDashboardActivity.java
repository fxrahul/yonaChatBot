package com.example.a91755.yonachatbot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class AdminDashboardActivity extends AppCompatActivity {
    private Spinner operation_spinner;
    private TextView  adminLogout;
    private Button btnSubmit;
    private String spinner_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        operation_spinner =  (Spinner) findViewById(R.id.spinner_operation);

        adminLogout = (TextView) findViewById(R.id.adminLogout);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        adminLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // session destroy
                startActivity(new Intent(AdminDashboardActivity.this, MainscreenActivity.class));
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                spinner_value = operation_spinner.getSelectedItem().toString();
//                if (spinner_value.equals("Add Student")) {
//                    startActivity(new Intent(AdminDashboardActivity.this,RegisterActivity.class));
//                } else if(spinner_value.equals("Add Attendance")){
//                    startActivity(new Intent(AdminDashboardActivity.this,MainActivity.class));
//                } else{
//                    startActivity(new Intent(AdminDashboardActivity.this,MainActivity.class));
//
//                }

                switch(spinner_value){

                    case "Add Student":
                        startActivity(new Intent(AdminDashboardActivity.this,RegisterActivity.class));
                        break;

                    case "Add Attendance":
                        startActivity(new Intent(AdminDashboardActivity.this,AddMarks.class));
                        break;

                    case "Add Marks":
                        startActivity(new Intent(AdminDashboardActivity.this,AddAttendance.class));
                        break;

                    case "Add Student General Details":
                        startActivity(new Intent(AdminDashboardActivity.this,StudentGeneralDetails.class));
                        break;

                    case "Unknown List":
                        startActivity(new Intent(AdminDashboardActivity.this,UnknownList.class));
                        break;
                }


            }
        });
    }
}
