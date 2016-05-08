package com.threebeardsmobile.taskapp.model;

import org.json.JSONArray;
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

    public Project() {
        childItems = new ArrayList<>();
    }

    public Project(JSONObject projectJSON) throws JSONException {
        super(projectJSON);

        projectOwner = projectJSON.getString("CreatedBy");

        JSONArray itemArray = new JSONArray(projectJSON.getJSONObject("items"));

        childItems = new ArrayList<>(itemArray.length());

        for (int i = 0; i < itemArray.length(); i++){
            JSONObject jo = itemArray.getJSONObject(i);
            if (jo.has("items")) {
                childItems.add(new Project(itemArray.getJSONObject(i)));
            } else {
                childItems.add(new Task(jo));
            }
        }
    }

    public Project(String itemName, String itemDescription, String createdBy,
                   String category, Date dateAdded, Date dueDate, int priority,
                    String projectOwner, Project parentProject) {
        super(itemName, itemDescription, createdBy, category, dateAdded, dueDate, priority);
        this.childItems = new ArrayList<>();
        this.projectOwner = projectOwner;
        this.parentProject = parentProject;
    }

    //Percent complete method
    public double getPercentComplete() {
        //stub // TODO: 4/24/16  Low Priority - get percentage complete
        return 0.0;
    }

    public Project getParentProject() {
        return parentProject;
    }

    public ArrayList<ToDoItem> getChildItems() {
        return childItems;
    }

    public String getProjectOwner() {
        return projectOwner;
    }

    public void setProjectOwner(String projectOwner) {
        this.projectOwner = projectOwner;
    }

//    public long getNextProjectId(){
//
//        return lastProjectId++;
//
//    }
}
