package com.freelance.razak.classportal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

/**
 * Created by Razak on 1/28/2017.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    protected EditText emailText,passwordText;
    protected Button loginBtn;
    protected TextView register;
    protected ProgressDialog progressDialog;
    protected FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        progressDialog = new ProgressDialog(this);
        emailText = (EditText) findViewById(R.id.editTextEmail);
        passwordText = (EditText) findViewById(R.id.editTextPassword);
        loginBtn = (Button) findViewById(R.id.buttonLogin);
        register = (TextView) findViewById(R.id.toRegister);
        firebaseAuth = FirebaseAuth.getInstance();
        loginBtn.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    public void login(){
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(getApplicationContext(),"Please Enter Email",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(getApplicationContext(),"Please Enter Password",Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Please Wait..");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            progressDialog.dismiss();
                            finish();
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                        }else{
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(),"Wrong Email or Password!",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        if(v == loginBtn){
            login();
        }

        if(v == register){
            finish();
            startActivity(new Intent(this,RegisterActivity.class));
        }
    }
}
