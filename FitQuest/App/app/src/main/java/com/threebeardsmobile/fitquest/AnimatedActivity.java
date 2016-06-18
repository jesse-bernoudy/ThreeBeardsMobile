package com.threebeardsmobile.fitquest;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.threebeardsmobile.fitquest.view.WalkingView;

public class AnimatedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_animated_layout);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        WalkingView wv = new WalkingView(this, 10000);
        setContentView(wv);

        wv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("!!BOB!!", "onClick: ");
            }
        });
    }
}
