package com.freelance.razak.classportal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by Razak on 1/29/2017.
 */

public class SubjectsFragment extends Fragment {
    protected TextView subjects,lect;
    protected FirebaseAuth firebaseAuth;
    protected ListView listsubjects;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.subject_fragment_layout,container,false);
        subjects = (TextView) v.findViewById(R.id.subjectsText);
        firebaseAuth = FirebaseAuth.getInstance();
        listsubjects = (ListView) v.findViewById(R.id.subjectsList);
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = firebaseDatabase.getReference();
        final ArrayList<String> subjectsArray = new ArrayList<String>();
        databaseReference.child("student_lecturers").child("StudentsId").child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot lecturers:dataSnapshot.getChildren()){
                    String lectId = lecturers.getKey();
                    databaseReference.child("lecturers").child(lectId).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                                String name = dataSnapshot.child("name").getValue().toString();
                                subjectsArray.add(name);
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getBaseContext(),android.R.layout.simple_expandable_list_item_1,subjectsArray);
                                listsubjects.setAdapter(adapter);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

        listsubjects.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String name = (String) parent.getItemAtPosition(position);
                databaseReference.child("lecturersinverse").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String id = dataSnapshot.child(name).child("id").getValue().toString();
                        DownloadSubjects subj = new DownloadSubjects();
                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                        Bundle args = new Bundle();
                        args.putString("lectid",id);
                        subj.setArguments(args);
                        fragmentTransaction.replace(R.id.mainFrame, subj);
                        fragmentTransaction.commit();
//                        Toast.makeText(getActivity().getApplicationContext(),id,Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        listsubjects.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final String name = (String) parent.getItemAtPosition(position);
                AlertDialog.Builder dialogB = new AlertDialog.Builder(getActivity());
                dialogB.setTitle("Delete this person?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        databaseReference.child("lecturersinverse").child(name).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                String id = dataSnapshot.child("id").getValue().toString();
                                databaseReference.child("student_lecturers").child("StudentsId").child(user.getUid()).child(id).removeValue();

                                Toast.makeText(getActivity().getBaseContext(),"Removed Successfully!",Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                }).setNegativeButton("No",null).show();

                return true;
            }
        });

        return v;
    }
}
