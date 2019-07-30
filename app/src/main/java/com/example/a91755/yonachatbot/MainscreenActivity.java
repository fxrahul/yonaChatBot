package com.example.a91755.yonachatbot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.OnClick;

public class MainscreenActivity extends AppCompatActivity {
    private Button adminButton,userButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainscreen);
        adminButton = (Button) findViewById(R.id.admin_login);
        userButton = (Button) findViewById(R.id.user_login);
        adminButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainscreenActivity.this, BeforeAdminLoginActivity.class));
            }
        });

        userButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainscreenActivity.this, UserLoginActivity.class));
            }
        });

    }
}
