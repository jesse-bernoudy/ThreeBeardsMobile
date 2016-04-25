package com.threebeardsmobile.taskapp.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by DUB on 4/16/16.
 */
public class Task extends ToDoItem {

    String assignedTo;

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public Task(JSONObject projectJson) throws JSONException {
        super(projectJson);
        assignedTo = (String) projectJson.get("assignedTo");
    }

    public Task(long itemID, String itemName, String itemDescription, String createdBy,
                String category, Date dateAdded, Date dueDate, int priority, String assignedTo)
    {
        super(itemID, itemName, itemDescription, createdBy, category, dateAdded, dueDate, priority);
        this.assignedTo = assignedTo;
    }
}
