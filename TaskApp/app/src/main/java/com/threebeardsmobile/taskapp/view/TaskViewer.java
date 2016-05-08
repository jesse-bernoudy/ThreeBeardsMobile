package com.threebeardsmobile.taskapp.view;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import com.threebeardsmobile.taskapp.R;
import com.threebeardsmobile.taskapp.controller.JSONController;
import com.threebeardsmobile.taskapp.model.Project;
import com.threebeardsmobile.taskapp.model.Task;
import com.threebeardsmobile.taskapp.model.ToDoItem;
import com.threebeardsmobile.taskapp.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import static android.widget.AdapterView.OnItemClickListener;
import static android.widget.AdapterView.OnItemLongClickListener;

public class TaskViewer extends ListActivity {

    private ArrayList<ToDoItem> tasks;
    private TaskItemAdapter adapter;

    public OnItemClickListener itemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // Either drill down in the list, or display the task details
            ToDoItem selected = tasks.get(position);
            if(selected instanceof Project) {
                // Drill down
                Intent intent = new Intent(TaskViewer.this, TaskViewer.class);
                startActivity(intent);
            } else {
                // Display detail view
            }
        }
    };

    public OnItemLongClickListener itemLongClickListener = new OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_viewer);

        try {
            User u = new User(8675309, "User McUser");
            JSONObject jo2 = JSONController.getJsonFromUser(u);
            JSONController.writeJsonToFile(getApplicationContext(), jo2);
            JSONObject jo = JSONController.getJsonFromFile(getApplicationContext());
            Log.d("Bob - io test -------", jo.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //ToDo: Replace with root TaskBase list instance from Controller
        tasks = new ArrayList<>();
        Project p = new Project();
        p.setItemName("Test Project");
        p.setItemDescription("This is a test project");
        for(int i = 0; i < 25; i++) {
            Task t = new Task();
            t.setItemName("Test Task" + i);
            t.setItemDescription("This is a test task");
            p.getChildItems().add(t);
        }
        tasks.add(p);
        for(int i = 0; i < 25; i++) {
            Task t = new Task();
            t.setItemName("Test Task" + i);
            t.setItemDescription("This is a test task");
            tasks.add(t);
        }

        // Create the adapter
        adapter = new TaskItemAdapter(this, tasks);
        setListAdapter(adapter);

        // Register click listeners for when an item is clicked or long clicked
        getListView().setOnItemClickListener(itemClickListener);
        getListView().setOnItemLongClickListener(itemLongClickListener);

        Button detailsButton = (Button) findViewById(R.id.details_button);
        detailsButton.setText(p.getItemName());
    }

}
