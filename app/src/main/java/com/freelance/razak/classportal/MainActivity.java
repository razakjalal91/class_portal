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
    protected Button btnLogout;
    protected FirebaseAuth firebaseAuth;
    protected TextView userText;
    protected String[] menuArray;
    private DrawerLayout layoutDrawer;
    private ListView listDrawer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        MobileAds.initialize(getApplicationContext(),"ca-app-pub-4240490864472000~8989634173");
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest
                .Builder()
                .addTestDevice("844126F6D820EA050520E0CD3EA3E270")
                .build();
        mAdView.loadAd(adRequest);

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dbref = database.getReference();
        menuArray = getResources().getStringArray(R.array.menu);
            layoutDrawer = (DrawerLayout) findViewById(R.id.drawerLayout);


            listDrawer = (ListView) findViewById(R.id.drawerList);
            listDrawer.bringToFront();
            listDrawer.requestLayout();
            listDrawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (position == 0) {
                        Toast.makeText(getApplicationContext(), "Profile", Toast.LENGTH_SHORT).show();
                        ProfileFragment profile = new ProfileFragment();
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.mainFrame, profile);
                        fragmentTransaction.commit();
                    }

                    if (position == 1) {
                        Toast.makeText(getApplicationContext(), "Subjects", Toast.LENGTH_SHORT).show();
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        SubjectsFragment subjects = new SubjectsFragment();
                        fragmentTransaction.replace(R.id.mainFrame, subjects);
                        fragmentTransaction.commit();
                    }

                    if (position == 2) {
                        Toast.makeText(getApplicationContext(), "Lecturers", Toast.LENGTH_SHORT).show();
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        LecturersFragment lecturers = new LecturersFragment();
                        fragmentTransaction.replace(R.id.mainFrame,lecturers);
                        fragmentTransaction.commit();
                    }

                    if (position == 3) {
                        Toast.makeText(getApplicationContext(), "Exam", Toast.LENGTH_SHORT).show();
                    }

                    if (position == 4) {
                        firebaseAuth.signOut();
                        finish();
                        startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
                    }

                }
            });


    }



}
