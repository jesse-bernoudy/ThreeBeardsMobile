package com.threebeardsmobile.taskapp.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.threebeardsmobile.taskapp.R;
import com.threebeardsmobile.taskapp.model.Project;
import com.threebeardsmobile.taskapp.model.Task;
import com.threebeardsmobile.taskapp.model.ToDoItem;
import com.threebeardsmobile.taskapp.model.User;

import java.util.ArrayList;

public class StartPage extends AppCompatActivity
        implements TaskListFragment.OnTaskItemFragmentListener,
            TaskDetailFragment.OnTaskDetailCallback,
            ProjectDetailFragment.OnProjectDetailCallback {

    private User user;
    private TaskListFragment currentTaskListFragment;
    private TaskDetailFragment currentTaskDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        //ToDo: Replace with root TaskBase list instance from Controller
        user = new User(0, "Test User");
        Project rootProject = user.getRootProject();
        ArrayList<ToDoItem> task = rootProject.getChildItems();
        Project p = new Project();
        p.setItemName("Test Project");
        p.setItemDescription("This is a test project");
        for (int i = 0; i < 25; i++) {
            Task t = new Task();
            t.setItemName("Test Task" + i);
            t.setItemDescription("This is a test task");
            p.getChildItems().add(t);
        }
        task.add(p);
        for (int i = 0; i < 25; i++) {
            Task t = new Task();
            t.setItemName("Test Task" + i);
            t.setItemDescription("This is a test task");
            task.add(t);
        }
        showTaskViewer(rootProject, true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.newProjectButton:
                return true;
            case R.id.newTaskButton:
                return true;
//            case R.id.edit_button:
//                startActivity(new Intent(getApplicationContext(), TaskEditFragment.class));
//                return true;
//            case R.id.delete_button:
//                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showTaskViewer(Project project, boolean isRoot) {
        TaskListFragment newFragment = currentTaskListFragment = TaskListFragment.newInstance(project, isRoot);
        Bundle args = new Bundle();
        newFragment.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.fragment_container, newFragment);
        if(!isRoot) {
            transaction.addToBackStack(null);
        }

        // Commit the transaction
        transaction.commit();
    }

    private void showTaskDetails(ToDoItem task) {
        TaskDetailFragment newFragment = TaskDetailFragment.newInstance(task);
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


    private void showProjectDetails(Project project) {
        ProjectDetailFragment newFragment = ProjectDetailFragment.newInstance(project);
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
        ToDoItem selected = list.get(position);
        if (selected instanceof Project) {
            // Drill down
            showTaskViewer((Project) selected, false);
        } else {
            // Display detail view
            showTaskDetails(list.get(position));
        }

        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public void onProjectDetailSelected(Project project) {
        showProjectDetails(project);
    }


    @Override
    public void onTaskDetailCallback() {
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        int backStackDepth = getSupportFragmentManager().getBackStackEntryCount();

        if (backStackDepth == 0) {
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                getSupportActionBar().setDisplayShowHomeEnabled(false);
            }
        }

    }

    @Override
    public void onProjectDetailCallback() {

    }
}
