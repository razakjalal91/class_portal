package com.freelance.razak.classportal;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Razak on 1/28/2017.
 */

public class SplashActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    protected Context context;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading..");
        progressDialog.show();
        if(firebaseAuth.getCurrentUser() == null){
            progressDialog.dismiss();
            finish();
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
        }else{
            progressDialog.dismiss();
            finish();
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }
    }

}
