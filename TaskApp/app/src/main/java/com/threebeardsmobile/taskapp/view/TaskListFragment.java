package com.threebeardsmobile.taskapp.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.threebeardsmobile.taskapp.R;
import com.threebeardsmobile.taskapp.model.Project;
import com.threebeardsmobile.taskapp.model.ToDoItem;

import java.util.ArrayList;

import static android.widget.AdapterView.OnItemClickListener;

public class TaskListFragment extends Fragment {

    private OnTaskItemFragmentListener callback;

    private Project project;
    public Project getProject() {
        return project;
    }

    private boolean showProjectDetailsButton;
    public void setShowProjectDetailsButton(boolean value) {
        this.showProjectDetailsButton = value;
    }

    private ArrayList<ToDoItem> tasks;
    private TaskItemAdapter adapter;

    public TaskListFragment() {
    }

    public static TaskListFragment newInstance(Project project, boolean isRoot) {
        TaskListFragment fragment = new TaskListFragment();
        fragment.setTasksRoot(project);
        fragment.setShowProjectDetailsButton(!isRoot);
        return fragment;
    }

    // Container Activity must implement this interface
    public interface OnTaskItemFragmentListener {
        void onTaskItemSelected(ArrayList<ToDoItem> list, int position);
        void onProjectDetailSelected(Project project);
    }

    public OnItemClickListener itemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            callback.onTaskItemSelected(tasks, position);
        }
    };

    public View.OnClickListener projectDetailsButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            callback.onProjectDetailSelected(project);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            callback = (OnTaskItemFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnTaskItemFragmentListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_list, container, false);
        ListView list = (ListView) view.findViewById(R.id.task_list);

        // Create the adapter
        adapter = new TaskItemAdapter(getContext(), tasks);
        list.setAdapter(adapter);

        // Register click listeners for when an item is clicked or long clicked
        list.setOnItemClickListener(itemClickListener);

        Button detailsButton = (Button) view.findViewById(R.id.details_button);
        detailsButton.setOnClickListener(projectDetailsButtonListener);
        if(showProjectDetailsButton) {
            detailsButton.setText(project.getItemName());
            detailsButton.setVisibility(View.VISIBLE);
        } else {
            detailsButton.setVisibility(View.GONE);
        }

        return view;
    }

    private void setTasksRoot(Project project) {
        this.project = project;
        tasks = project.getChildItems();
    }
}
