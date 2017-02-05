package com.freelance.razak.classportal;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Razak on 1/28/2017.
 */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnRegister;
    private EditText emailField,passwordField;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private TextView textViewLogin;
    private FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        btnRegister = (Button) findViewById(R.id.buttonRegister);
        emailField = (EditText) findViewById(R.id.editTextEmail);
        passwordField = (EditText) findViewById(R.id.editTextPassword);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        textViewLogin = (TextView) findViewById(R.id.toLogin);
        progressDialog = new ProgressDialog(this);

        btnRegister.setOnClickListener(this);
        textViewLogin.setOnClickListener(this);
    }

    public void registerUser(){
        final String email = emailField.getText().toString();
        String password = passwordField.getText().toString();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(getApplicationContext(),"Please Enter Email",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(getApplicationContext(),"Please Enter Password",Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Registering ..");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    DatabaseReference dbref = FirebaseDatabase.getInstance().getReference();
                    FirebaseUser user = firebaseAuth.getCurrentUser();

                    UsersData usersData = new UsersData(email,"s");
                    dbref.child("userroles").child(user.getUid()).setValue(usersData);
                    Toast.makeText(getApplicationContext(),"Registered Successfully!",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }else{
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),"Fail To Register!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // All OnClick Listener
    @Override
    public void onClick(View v) {
        if(v == btnRegister){
            registerUser();
        }

        if(v == textViewLogin){
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }
    }
    // All OnClick Listener END
}
