package com.example.a91755.yonachatbot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BeforeAdminLoginActivity extends AppCompatActivity {
private EditText inputCode;
private Button btnGo;
String inputCodeText ;
String secrectCode = "ADMIN1234";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before_admin_login);
        btnGo     = findViewById(R.id.btnGo);
        inputCode = (EditText) findViewById(R.id.inputCodeForCheck);
        inputCodeText = inputCode.getText().toString();


    }

    public void preLogin(View view){
        inputCodeText = inputCode.getText().toString();
                    if (secrectCode.equals(inputCodeText)) {
                Toast.makeText(BeforeAdminLoginActivity.this, "Admin Verified, You can successfully Login.", Toast.LENGTH_LONG).show();
                startActivity(new Intent(BeforeAdminLoginActivity.this, LoginActivity.class));
                finish();
            } else {
                Toast.makeText(BeforeAdminLoginActivity.this, "Admin is not verified, Please enter code given by Institution.", Toast.LENGTH_LONG).show();
                startActivity(new Intent(BeforeAdminLoginActivity.this, BeforeAdminLoginActivity.class));
                finish();
            }
    }

}
