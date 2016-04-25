package com.threebeardsmobile.taskapp.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by DUB on 4/16/16.
 */
public class Project extends ToDoItem {

    //Collection for child projects and tasks
    public ArrayList<ToDoItem> childItems;
    //Project Owner field
    public String projectOwner;
    private Project parentProject;


    //Constructors
    public Project(JSONObject projectJSON) throws JSONException {
        super(projectJSON);
    }

    public Project(long itemID, String itemName, String itemDescription, String createdBy,
                   String category, Date dateAdded, Date dueDate, int priority,
                   ArrayList<ToDoItem> childItems, String projectOwner, Project parentProject)
    {
        super(itemID, itemName, itemDescription, createdBy, category, dateAdded, dueDate, priority);
        this.childItems = childItems;
        this.projectOwner = projectOwner;
        this.parentProject = parentProject;
    }

    //Percent complete method
    public double getPercentComplete(){
        //stub // TODO: 4/24/16  
        return 0.0;
    }

    public Project getParentProject(){
        return parentProject;
    }


    public ArrayList<ToDoItem> getChildItems() {
        return childItems;
    }
}
