package com.freelance.razak.classportal;

import android.content.Intent;
import android.net.Uri;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.URI;
import java.util.ArrayList;

import static android.provider.CalendarContract.CalendarCache.URI;

/**
 * Created by Razak on 2/3/2017.
 */

public class DownloadSubjects extends Fragment {
    protected TextView lectId;
    protected ListView subjectslist,logicgate;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.download_subject_layout,container,false);
        logicgate(v);
        subjectlist(v);

        return v;
    }

    public void logicgate(View v){
        final ArrayList<String> logicarray = new ArrayList<>();
        logicarray.add("Logic Gate Calculator");
        logicgate = (ListView) v.findViewById(R.id.logicGate);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity().getBaseContext(),android.R.layout.simple_list_item_1,logicarray);
        logicgate.setAdapter(adapter2);
        logicgate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LogicGateCalcActivity logicFragment = new LogicGateCalcActivity();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.mainFrame, logicFragment);
                fragmentTransaction.commit();
                Toast.makeText(getActivity().getBaseContext(),"Logic Gate",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void subjectlist(View v){
        final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = firebaseDatabase.getReference();
        final ArrayList<String> subjectsarray = new ArrayList<>();
        subjectslist = (ListView) v.findViewById(R.id.subjectList);
        String parseId = getArguments().getString("lectid");
        databaseReference.child("lecturers_subject").child(parseId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot subjects:dataSnapshot.getChildren()){
                    String subjectname = subjects.getKey();
                    subjectsarray.add(subjectname);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getBaseContext(),android.R.layout.simple_list_item_1,subjectsarray);
                subjectslist.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        subjectslist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String subjectname = (String) parent.getItemAtPosition(position);
                final String parseid = getArguments().getString("lectid");
                databaseReference.child("lecturers_subject").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        final String url = dataSnapshot.child(parseid).child(subjectname).getValue().toString();
                        Bundle bundle = new Bundle();
                        bundle.putString("parse",url);

                        WebviewActivity webviewActivity = new WebviewActivity();
                        webviewActivity.setArguments(bundle);
                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.mainFrame, webviewActivity);
                        fragmentTransaction.commit();
                        Toast.makeText(getActivity().getBaseContext(),"Web View",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });

    }

}
