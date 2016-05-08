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
import com.threebeardsmobile.taskapp.model.ToDoItem;

import java.util.ArrayList;

import static android.widget.AdapterView.OnItemClickListener;
import static android.widget.AdapterView.OnItemLongClickListener;

public class TaskListFragment extends Fragment {
    public static String ARG_TASK_LIST = "ARG_TASK_LIST";

    private OnTaskItemSelectedListener callback;

    private ArrayList<ToDoItem> tasks;
    private TaskItemAdapter adapter;

    public TaskListFragment() {
    }

    public static TaskListFragment newInstance(ArrayList<ToDoItem> tasks) {
        TaskListFragment fragment = new TaskListFragment();
        fragment.setTasksRoot(tasks);
        return fragment;
    }

    // Container Activity must implement this interface
    public interface OnTaskItemSelectedListener {
        public void onTaskItemSelected(ArrayList<ToDoItem> list, int position);
    }

    public OnItemClickListener itemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            callback.onTaskItemSelected(tasks, position);
        }
    };

    public OnItemLongClickListener itemLongClickListener = new OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            return false;
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            callback = (OnTaskItemSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnTaskItemSelectedListener");
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
        list.setOnItemLongClickListener(itemLongClickListener);

        Button detailsButton = (Button) view.findViewById(R.id.details_button);
        detailsButton.setText("Test");

        return view;
    }

    private void setTasksRoot(ArrayList<ToDoItem> tasksRoot) {
        tasks = tasksRoot;
    }
}