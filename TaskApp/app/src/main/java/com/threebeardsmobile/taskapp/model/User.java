package com.threebeardsmobile.taskapp.model;

/**
 * Created by bob on 4/24/16.
 */
public class User {

    Project rootProject;
    String userName;
    long userID;

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
