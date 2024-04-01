package org.vedy.mypolicies.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import org.vedy.mypolicies.R;

public class BaseActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    public String TAG = "VEDY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();

        getWindow().setStatusBarColor(getResources().getColor(R.color.white));
    }
}