package com.threebeardsmobile.taskapp.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.threebeardsmobile.taskapp.R;
import com.threebeardsmobile.taskapp.model.ToDoItem;

public class TaskEditFragment extends Fragment {

    private OnTaskEditFragmentListener callback;

    public ToDoItem getTask() {
        return task;
    }

    private ToDoItem task;

    public TaskEditFragment() {
    }

    public static TaskEditFragment newInstance(ToDoItem task) {
        TaskEditFragment fragment = new TaskEditFragment();
        fragment.setCurrentTask(task);
        return fragment;
    }

    // Container Activity must implement this interface
    public interface OnTaskEditFragmentListener {
    }

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
            callback = (OnTaskEditFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnTaskItemFragmentListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_edit_view, container, false);

        return view;
    }

    private void setCurrentTask(ToDoItem task) {
        this.task = task;
    }
}
