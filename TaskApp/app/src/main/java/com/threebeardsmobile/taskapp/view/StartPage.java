package com.threebeardsmobile.taskapp.view;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.threebeardsmobile.taskapp.R;
import com.threebeardsmobile.taskapp.model.Project;
import com.threebeardsmobile.taskapp.model.Task;
import com.threebeardsmobile.taskapp.model.TaskBase;

import java.util.ArrayList;

public class StartPage extends ListActivity {
    private ArrayList<TaskBase> tasks;
    private TaskItemAdapter adapter;

    public AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // Either drill down in the list, or display the task details
            TaskBase selected = tasks.get(position);
            if(selected instanceof Project) {
                // Drill down
                Intent intent = new Intent(StartPage.this, TaskViewer.class);
                startActivity(intent);
            } else {
                // Display detail view
            }
        }
    };

    public AdapterView.OnItemLongClickListener itemLongClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);

        //ToDo: Replace with root TaskBase list instance from Controller
        tasks = new ArrayList<>();
        Project p = new Project();
        p.setTaskName("Test Project");
        p.setTaskDescription("This is a test project");
        for(int i = 0; i < 25; i++) {
            Task t = new Task();
            t.setTaskName("Test Task" + i);
            t.setTaskDescription("This is a test task");
            p.AddTask(t);
        }
        tasks.add(p);
        for(int i = 0; i < 25; i++) {
            Task t = new Task();
            t.setTaskName("Test Task" + i);
            t.setTaskDescription("This is a test task");
            tasks.add(t);
        }

        // Create the adapter
        adapter = new TaskItemAdapter(this, tasks);
        setListAdapter(adapter);

        // Register click listeners for when an item is clicked or long clicked
        getListView().setOnItemClickListener(itemClickListener);
        getListView().setOnItemLongClickListener(itemLongClickListener);
    }
}
