package com.freelance.razak.classportal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import com.google.android.gms.ads.MobileAds;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Razak on 1/28/2017.
 */

public class MainActivity extends AppCompatActivity{
    protected Button btnLogout,profileBtn,lecturersBtn,subjectsBtn,examBtn;
    protected FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
//        loadAds();
        firebaseMethod();
        profileClick();
        subjectsClick();
        lecturersClick();
        examClick();
        logout();

    }

    public void loadAds(){
        MobileAds.initialize(getApplicationContext(),"ca-app-pub-4240490864472000~8989634173");
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest
                .Builder()
                .addTestDevice("844126F6D820EA050520E0CD3EA3E270")
                .build();
        mAdView.loadAd(adRequest);
    }

    public void firebaseMethod(){
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dbref = database.getReference();
    }

    public void profileClick(){
        profileBtn = (Button) findViewById(R.id.profileBot);
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Profile", Toast.LENGTH_SHORT).show();
                ProfileFragment profile = new ProfileFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.mainFrame, profile);
                fragmentTransaction.commit();
            }
        });
    }

    public void subjectsClick(){
        subjectsBtn = (Button) findViewById(R.id.subjectBot);
        subjectsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Subjects", Toast.LENGTH_SHORT).show();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                SubjectsFragment subjects = new SubjectsFragment();
                fragmentTransaction.replace(R.id.mainFrame, subjects);
                fragmentTransaction.commit();
            }
        });
    }

    public void lecturersClick(){
        lecturersBtn = (Button) findViewById(R.id.lecturerBot);
        lecturersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Lecturers", Toast.LENGTH_SHORT).show();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                LecturersFragment lecturers = new LecturersFragment();
                fragmentTransaction.replace(R.id.mainFrame,lecturers);
                fragmentTransaction.commit();
            }
        });
    }

    public void examClick(){
        examBtn = (Button) findViewById(R.id.examBot);
        examBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Exam", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void logout(){
        btnLogout = (Button) findViewById(R.id.logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });
    }

}
