package com.freelance.razak.classportal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Razak on 1/29/2017.
 */

public class ProfileFragment extends Fragment implements ValueEventListener{
    protected TextView profile,email,emailTittle,roleTittle,role;
    protected FirebaseAuth firebaseAuth;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.profile_fragment_layout,container,false);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        role = (TextView) v.findViewById(R.id.userRole);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dbref = database.getReference();
        dbref.child("userroles").child(user.getUid()).child("role").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String roleKey = dataSnapshot.getValue().toString();
                if(roleKey.equals("l")) {
                    role.setText("Lecturer");
                }

                if(roleKey.equals("s")){
                    role.setText("Students");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        profile = (TextView) v.findViewById(R.id.profileText);
        email = (TextView) v.findViewById(R.id.userEmail);
        email.setText(user.getEmail());
        emailTittle = (TextView) v.findViewById(R.id.emailText);
        roleTittle = (TextView) v.findViewById(R.id.roleText);


        return v;
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}
