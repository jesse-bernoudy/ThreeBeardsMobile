package com.threebeardsmobile.taskapp.model;

import com.threebeardsmobile.taskapp.controller.JSONController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by bob on 4/24/16.
 */
public class User {

    Project rootProject;
    String userName;
    long userID;



    public User(long id, String name){
        userID = id;
        userName = name;

        Date createdDate = new Date();

        rootProject = new Project(
                01,//rootProjectID
                "root", //itemName
                "", //itemDescription
                name, //createdBy
                "root", //category
                createdDate, //dateAdded
                null, //dueDate
                0, //Priority
                new ArrayList<ToDoItem>(),//childItems
                name, //projectOwner
                null //parentProject
                );
    }

    public User (JSONObject json){

    }

    public String getUserName() {
        return userName;
    }

    public long getUserID() {
        return userID;
    }

    public Project getRootProject() {
        return rootProject;
    }

}
