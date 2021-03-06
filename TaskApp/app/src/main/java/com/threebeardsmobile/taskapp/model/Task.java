package com.threebeardsmobile.taskapp.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by DUB on 4/16/16.
 */
public class Task extends ToDoItem {

    String assignedTo;

    boolean isComplete;

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public Task() {

    }

    public Task(JSONObject projectJson) throws JSONException {
        super(projectJson);
        assignedTo = (String) projectJson.get("assignedTo");
        isComplete = projectJson.getBoolean("isComplete");
    }

    public Task( String itemName, String itemDescription, String createdBy,
                String category, Date dateAdded, Date dueDate, int priority, String assignedTo)
    {
        super( itemName, itemDescription, createdBy, category, dateAdded, dueDate, priority);
        this.assignedTo = assignedTo;
        isComplete = false;
    }


    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }


}
