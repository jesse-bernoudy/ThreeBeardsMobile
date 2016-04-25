package com.threebeardsmobile.taskapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.threebeardsmobile.taskapp.R;
import com.threebeardsmobile.taskapp.test.JSONTest;

public class TaskViewer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_viewer);

        TextView tv = (TextView)findViewById(R.id.textView);

        tv.setText(JSONTest.jsonTestBuild());





    }
}
