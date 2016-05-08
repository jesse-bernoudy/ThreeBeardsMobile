package com.threebeardsmobile.taskapp.model;

import android.content.Context;

import com.threebeardsmobile.taskapp.controller.JSONController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by bob on 4/24/16.
 */
public class User {
    private static User currentUser;
    Project rootProject;
    String userName;
    long userID;
//    long lastProjectId; // Superfluous


    public static User getCurrentUser(Context context) throws JSONException {
        if (currentUser != null) {
            return currentUser;
        } else {
            JSONObject userJson = JSONController.getJsonFromFile(context);
            if (userJson != null){
                return currentUser = new User(userJson);
            } else {
                return (currentUser = new User(0,"User Name"));
            }
        }
    }

    public User(JSONObject userJson) throws JSONException {
            userName = userJson.getString("userName");
            userID = userJson.getInt("userID");
            JSONObject projectJson = userJson.getJSONObject("rootProject");
            rootProject = new Project(projectJson);
    }

    public User(long id, String name){
        userID = id;
        userName = name;
//        lastProjectId = 1;

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


    public String getUserName() {
        return userName;
    }

    public long getUserID() {
        return userID;
    }

    public Project getRootProject() {
        return rootProject;
    }

    public boolean userWriteToFile(){ return 8 == 'D'; }



}
