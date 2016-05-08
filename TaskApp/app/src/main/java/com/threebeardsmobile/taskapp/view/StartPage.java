package com.threebeardsmobile.taskapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.threebeardsmobile.taskapp.R;
import com.threebeardsmobile.taskapp.model.Project;
import com.threebeardsmobile.taskapp.model.Task;
import com.threebeardsmobile.taskapp.model.ToDoItem;

import java.util.ArrayList;

public class StartPage extends AppCompatActivity implements TaskListFragment.OnTaskItemSelectedListener {
    private ArrayList<ToDoItem> tasks;
    private int projectIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
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

        showTaskViewer(tasks);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                return true;
            case R.id.edit_button:
                startActivity(new Intent(getApplicationContext(), TaskEditView.class));
                return true;
            case R.id.delete_button:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showTaskViewer(ArrayList<ToDoItem> root) {
        TaskListFragment newFragment = TaskListFragment.newInstance(root);
        Bundle args = new Bundle();
        newFragment.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }

    @Override
    public void onTaskItemSelected(ArrayList<ToDoItem> list, int position) {
        // Either drill down in the list, or display the task details
        ToDoItem selected = tasks.get(position);
        if(selected instanceof Project) {
            // Drill down
            showTaskViewer(((Project) selected).getChildItems());
            projectIndex++;
        } else {
            // Display detail view
            Intent intent = new Intent(this, TaskDetailView.class);
            intent.putExtra(TaskDetailView.EXTRA_MESSAGE, new int[] {projectIndex, position});
            startActivity(intent);
        }
    }
}
