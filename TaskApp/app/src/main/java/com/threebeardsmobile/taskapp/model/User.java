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
    long lastProjectId; // Superfluous



    public User(long id, String name){
        userID = id;
        userName = name;
        lastProjectId = 1;

        Date createdDate = new Date();

        rootProject = new Project(
                "root", //itemName
                "", //itemDescription
                name, //createdBy
                "root", //category
                createdDate, //dateAdded
                null, //dueDate
                0, //Priority
                name, //projectOwner
                null //parentProject
                );
    }

    public User (JSONObject json){
        // // TODO: 5/1/16
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
