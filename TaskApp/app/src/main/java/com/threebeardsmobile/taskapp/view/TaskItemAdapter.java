package com.threebeardsmobile.taskapp.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.threebeardsmobile.taskapp.R;
import com.threebeardsmobile.taskapp.model.Project;
import com.threebeardsmobile.taskapp.model.ToDoItem;

import java.util.ArrayList;

/**
 * Created by Jesse on 4/23/2016.
 */
public class TaskItemAdapter extends ArrayAdapter<ToDoItem> {

    public TaskItemAdapter(Context context, ArrayList<ToDoItem> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ToDoItem task = getItem(position);

        // ToDo: convertView view should be recycled, but the logic to use the right layout is not in place yet
        if (task instanceof Project) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_project, parent, false);
        } else {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_task, parent, false);
        }

        // Lookup view for data population
        TextView taskName = (TextView) convertView.findViewById(R.id.taskName);
        TextView taskDesc = (TextView) convertView.findViewById(R.id.taskDesc);
        // Populate the data into the template view using the data object
        taskName.setText(task.getItemName());
        taskDesc.setText(task.getItemDescription());
        // Return the completed view to render on screen
        return convertView;
    }
}
