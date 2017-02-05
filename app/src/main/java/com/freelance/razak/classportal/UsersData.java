package com.freelance.razak.classportal;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Razak on 1/30/2017.
 */

public class UsersData {
    public String email,role;
    public UsersData(String email,String role){
        this.email = email;
        this.role = role;
    }

}
