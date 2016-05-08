package com.threebeardsmobile.taskapp.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.threebeardsmobile.taskapp.R;
import com.threebeardsmobile.taskapp.model.ToDoItem;

public class TaskDetailFragment extends Fragment {

    private OnTaskDetailCallback callback;
    private ToDoItem task;

    public TaskDetailFragment() {
    }

    public static TaskDetailFragment newInstance(ToDoItem task) {
        TaskDetailFragment fragment = new TaskDetailFragment();
        fragment.setCurrentTask(task);
        return fragment;
    }

    // Container Activity must implement this interface
    public interface OnTaskDetailCallback {
        public void onTaskDetailCallback();
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
            callback = (OnTaskDetailCallback) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnTaskItemSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_detail_view, container, false);

        return view;
    }

    private void setCurrentTask(ToDoItem task) {
        this.task = task;
    }

}
