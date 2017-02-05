package com.freelance.razak.classportal;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.zip.Inflater;

/**
 * Created by Razak on 1/31/2017.
 */

public class LecturersFragment extends Fragment{
    protected TextView lectName;
    protected ListView listOfLect;
    protected FirebaseAuth firebaseAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.lecturers_fragment_layout,container,false);
        listOfLect = (ListView) v.findViewById(R.id.lecturerlist);
        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = firebaseDatabase.getReference();
        final ArrayList<String> lecturerlistarray = new ArrayList<String>();
        databaseReference.child("lecturers").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot lecturers: dataSnapshot.getChildren()){
                    String name = dataSnapshot.child(lecturers.getKey()).child("name").getValue().toString();
                    String lectid = lecturers.getKey().toString();
                    lecturerlistarray.add(name);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getBaseContext(),android.R.layout.simple_expandable_list_item_1,lecturerlistarray);
                listOfLect.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        listOfLect.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder dialogB = new AlertDialog.Builder(getActivity());
                dialogB.setTitle("Follow This Person?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        databaseReference.child("lecturersinverse").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                String name = (String) parent.getItemAtPosition(position);
                                final String lectid = dataSnapshot.child(name).child("id").getValue().toString();
                                final String uid = user.getUid().toString();
                                Toast.makeText(getActivity().getBaseContext(),"Successfully Added!",Toast.LENGTH_SHORT).show();
                                databaseReference.child("student_lecturers").child("StudentsId").child(uid).child(lectid).setValue(lectid);
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                    }
                    }).setNegativeButton("No",null).show();

            }
        });
        return v;
    }


}
