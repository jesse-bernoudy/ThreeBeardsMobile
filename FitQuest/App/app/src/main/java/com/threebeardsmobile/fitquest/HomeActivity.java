package com.threebeardsmobile.fitquest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // ToDo: Check to see if the user is authenticated and if not, send them to the login page

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
