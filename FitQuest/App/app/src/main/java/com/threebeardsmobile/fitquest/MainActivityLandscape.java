package com.threebeards.ad340.fitquestlayouts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivityLandscape extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_landscape);
        WalkingSurfaceView wsv = (WalkingSurfaceView) findViewById(R.id.walkMap);
        wsv.run();
    }
}
