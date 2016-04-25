package com.threebeardsmobile.taskapp.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by DUB on 4/16/16.
 */
public class Task extends ToDoItem {

    String assignedTo;


    public Task(JSONObject projectJson) throws JSONException {
        super(projectJson);
        assignedTo = (String) projectJson.get("assignedTo");
    }
}
