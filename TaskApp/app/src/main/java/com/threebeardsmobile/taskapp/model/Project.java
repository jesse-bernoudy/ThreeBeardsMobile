package com.threebeardsmobile.taskapp.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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
