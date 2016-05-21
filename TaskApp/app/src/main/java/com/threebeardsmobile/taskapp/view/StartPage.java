package com.threebeardsmobile.taskapp.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.threebeardsmobile.taskapp.R;
import com.threebeardsmobile.taskapp.model.Project;
import com.threebeardsmobile.taskapp.model.Task;
import com.threebeardsmobile.taskapp.model.ToDoItem;
import com.threebeardsmobile.taskapp.model.User;

import java.util.ArrayList;

public class StartPage extends AppCompatActivity
        implements TaskListFragment.OnTaskItemFragmentListener,
        TaskDetailFragment.OnTaskDetailFragmentListener,
        TaskEditFragment.OnTaskEditFragmentListener,
        ProjectDetailFragment.OnProjectDetailFragmentListener,
        ProjectEditFragment.OnProjectEditFragmentListener {

    private User user;
    private TaskListFragment currentTaskListFragment;
    private TaskDetailFragment currentTaskDetailFragment;
    private TaskEditFragment currentTaskEditFragment;
    private ProjectDetailFragment currentProjectDetailFragment;
    private ProjectEditFragment currentProjectEditFragment;

    private Fragment currentFragment;

    private MenuItem editButton;
    private MenuItem saveButton;
    private MenuItem deleteButton;
    private MenuItem cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        if (getSupportActionBar() != null) {
            getSupportActionBar().setLogo(R.mipmap.ic_launcher);
            getSupportActionBar().setDisplayUseLogoEnabled(true);
        }

        //ToDo: Call the static contructor, currently crashes.
        user = new User(0, "Beardy McBeardface");

        Project rootProject = user.getRootProject();
        ArrayList<ToDoItem> task = rootProject.getChildItems();
        Project p = new Project();
        p.setItemName("Task App UI");
        p.setItemDescription("Get the UI working");
        for (int i = 0; i < 25; i++) {
            Task t = new Task();
            t.setItemName("Add UI item " + i);
            t.setItemDescription("Add this cool UI item");
            p.getChildItems().add(t);
        }
        task.add(p);
        for (int i = 0; i < 25; i++) {
            Task t = new Task();
            t.setItemName("Test Task App feature " + i);
            t.setItemDescription("Make sure to test this feature");
            task.add(t);
        }
        showTaskViewer(rootProject, true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        editButton = menu.findItem(R.id.editButton);
        saveButton = menu.findItem(R.id.saveButton);
        deleteButton = menu.findItem(R.id.deleteButton);
        cancelButton = menu.findItem(R.id.cancelButton);

        editButton.setVisible(false);
        saveButton.setVisible(false);
        deleteButton.setVisible(false);
        cancelButton.setVisible(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.newProjectButton:
                showProjectEdit(new Project());
                return true;
            case R.id.newTaskButton:
                showTaskEdit(new Task());
                return true;
            case R.id.editButton: {
                Fragment f = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
                if (f instanceof TaskDetailFragment) {
                    showTaskEdit(currentTaskDetailFragment.getTask());
                } else {
                    showProjectEdit(currentProjectDetailFragment.getProject());
                }
                return true;
            }
            case R.id.saveButton: {
                Project updatedProject = currentTaskListFragment.getProject();

                Fragment f = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
                if (f instanceof TaskEditFragment) {
                    if (!updatedProject.childItems.contains(currentTaskDetailFragment.getTask())) {
                        updatedProject.childItems.add(currentTaskDetailFragment.getTask());
                    }
                } else {
                    if (!updatedProject.childItems.contains(currentProjectDetailFragment.getProject())) {
                        updatedProject.childItems.add(currentProjectDetailFragment.getProject());
                    }
                }
                onBackPressed();
                return true;
            }
            case R.id.deleteButton:
                onBackPressed();
                return true;
            case R.id.cancelButton:
                onBackPressed();
                return true;
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
        if (!isRoot) {
            transaction.addToBackStack(null);
        }

        // Commit the transaction
        transaction.commit();
    }

    private void showTaskDetails(ToDoItem task) {
        TaskDetailFragment newFragment = currentTaskDetailFragment = TaskDetailFragment.newInstance(task);
        Bundle args = new Bundle();
        newFragment.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);

        editButton.setVisible(true);
        saveButton.setVisible(false);
        deleteButton.setVisible(true);
        cancelButton.setVisible(false);

        // Commit the transaction
        transaction.commit();
    }


    private void showTaskEdit(ToDoItem task) {
        TaskEditFragment newFragment = currentTaskEditFragment = TaskEditFragment.newInstance(task);
        Bundle args = new Bundle();
        newFragment.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);

        editButton.setVisible(false);
        saveButton.setVisible(true);
        deleteButton.setVisible(false);
        cancelButton.setVisible(true);

        // Commit the transaction
        transaction.commit();
    }

    private void showProjectDetails(Project project) {
        ProjectDetailFragment newFragment = currentProjectDetailFragment = ProjectDetailFragment.newInstance(project);
        Bundle args = new Bundle();
        newFragment.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);

        editButton.setVisible(true);
        saveButton.setVisible(false);
        deleteButton.setVisible(true);
        cancelButton.setVisible(false);

        // Commit the transaction
        transaction.commit();
    }

    private void showProjectEdit(Project project) {
        ProjectEditFragment newFragment = currentProjectEditFragment = ProjectEditFragment.newInstance(project);
        Bundle args = new Bundle();
        newFragment.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);

        editButton.setVisible(false);
        saveButton.setVisible(true);
        deleteButton.setVisible(false);
        cancelButton.setVisible(true);

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
    public void onBackPressed() {
        super.onBackPressed();

        int backStackDepth = getSupportFragmentManager().getBackStackEntryCount();

        if (backStackDepth == 0) {
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                getSupportActionBar().setDisplayShowHomeEnabled(false);
            }
        }

        Fragment f = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (f instanceof TaskListFragment) {
            editButton.setVisible(false);
            saveButton.setVisible(false);
            deleteButton.setVisible(false);
            cancelButton.setVisible(false);
        } else if (f instanceof TaskDetailFragment || f instanceof ProjectDetailFragment) {
            editButton.setVisible(true);
            saveButton.setVisible(false);
            deleteButton.setVisible(true);
            cancelButton.setVisible(false);
        }
    }

}
