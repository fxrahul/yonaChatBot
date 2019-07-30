package com.example.a91755.yonachatbot;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class UserRegisterActivity extends AppCompatActivity {

    private EditText inputName, inputEmail , inputPassword, inputUsername, inputRollNo;
    private TextView btnSignIn;
    private Button btnSignUp, btnResetPassword;
    private ProgressBar progressBar;

    private FirebaseAuth auth;
    DatabaseReference databaseUsers;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();


        btnSignIn = (TextView) findViewById(R.id.link_login);
        btnSignUp = (Button) findViewById(R.id.btn_signup);
        inputRollNo = (EditText) findViewById(R.id.input_roll_no);
        inputUsername = (EditText) findViewById(R.id.input_username);
        inputName = (EditText) findViewById(R.id.input_name);
        inputEmail = (EditText) findViewById(R.id.input_email);
        inputPassword = (EditText) findViewById(R.id.input_password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        databaseUsers = FirebaseDatabase.getInstance().getReference("users");

//        btnResetPassword.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(Signu.this, ResetPasswordActivity.class));
//            }
//        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserRegisterActivity.this, UserLoginActivity.class));
                finish();

            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                //create user
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(UserRegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(UserRegisterActivity.this, "User Successfully Created", Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(UserRegisterActivity.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    String id1 = auth.getCurrentUser().getUid();
//                                    Toast.makeText(UserRegisterActivity.this, id1, Toast.LENGTH_LONG).show();
                                    addUser(id1);
//                                    startActivity(new Intent(UserRegisterActivity.this, UserLoginActivity.class));
//                                    finish();
                                }
                            }
                        });

            }
        });
    }

    private void addUser(String id) {
        //getting the values to save
        String username = inputUsername.getText().toString().trim();
        String name = inputName.getText().toString().trim();
        String email = inputEmail.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();
        String rollNo = inputRollNo.getText().toString().trim();
        String uniqueKey = email+password;



        //checking if the value is provided
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(email)  && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(username) ) {

            //getting a unique id using push().getKey() method
            //it will create a unique id and we will use it as the Primary Key for our Artist

            //creating an Artist Object
            User user = new User(id,username,uniqueKey, name, email, password, rollNo);

            //Saving the Artist
            databaseUsers.child(id).setValue(user);

            //setting edittext to blank again
//            inputName.setText("");

            //displaying a success toast
            Toast.makeText(this, "User added with Key:" + " " + id, Toast.LENGTH_LONG).show();
            startActivity(new Intent(UserRegisterActivity.this, UserLoginActivity.class));
            finish();
        } else {
            //if the value is not given displaying a toast
            Toast.makeText(this, "Please Fill all fields", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}
