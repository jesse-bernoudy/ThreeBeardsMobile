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

        projectOwner = projectJSON.getString("AssignedTo");

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

    public String getPercentComplete() {
        int[] completeAndTotalTasks = getTaskCount(this);

        double percentage = (double)completeAndTotalTasks[0]/completeAndTotalTasks[1]*100;

        return String.format("%.0f%%",percentage);

    }

    private int[] getTaskCount(Project p){
        //count tasks throughout project and all sub projects and return
        // completed tasks / total tasks formatted as XX.X%

        // index 0 for tasks completed, index 1 for total tasks
        int[] taskCount = {0,0};
        for (ToDoItem tdi : p.getChildItems()){
            if (tdi instanceof Task){
                taskCount[1]++;
                if ( ((Task)tdi).isComplete() ) {
                    taskCount[0]++;
                }
            } else {
                int[] childCount = getTaskCount((Project)tdi);
                taskCount[0] += childCount[0];
                taskCount[1] += childCount[1];
            }
        }
        return taskCount;
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
