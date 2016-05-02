package com.threebeardsmobile.taskapp.controller;

import android.os.Environment;
import android.util.Log;

import com.threebeardsmobile.taskapp.model.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Created by bob on 4/24/16.
 */
public class JSONController {
    static final String mFile =
            Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOCUMENTS) + "/user_tasks.json";

    private JSONController(){ /* No instantiaton */}

    public static JSONObject getJsonFromUser(User user){
        JSONObject userJson = new JSONObject();

        try {
            userJson.put("userID", user.getUserID());
            userJson.put("userName", user.getUserName());
            userJson.put("rootProject", getJsonFromProject(user.getRootProject()));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return userJson;
    }

    private static JSONObject getJsonFromProject(Project project){
        JSONObject projectJson = new JSONObject();
        JSONArray items = new JSONArray();
        try{
            projectJson.put("parentProject", project.getParentProject());
            projectJson.put("itemID", project.getItemID());
            projectJson.put("itemType", "Project");
            projectJson.put("itemName", project.getItemName());
            projectJson.put("itemDescription", project.getItemDescription());
            projectJson.put("createdBy", project.getCreatedBy());
            projectJson.put("category", project.getCategory());
            projectJson.put("dateCreated", project.getDateAdded());
            projectJson.put("dueDate", project.getDueDate());
            projectJson.put("assignedTo", project.getProjectOwner());
            projectJson.put("priority", project.getPriority());
            projectJson.put("items", items);


            for (ToDoItem item : project.getChildItems()){
                if (item instanceof Project){
                    JSONObject subproject = getJsonFromProject((Project)item);
                    items.put(subproject);
                } else {
                    items.put(getJsonFromTask((Task)item));
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return projectJson;
    }

    private static JSONObject getJsonFromTask(Task task) {
        JSONObject taskJson = new JSONObject();

        try {
            taskJson.put("itemID", task.getItemID());
            taskJson.put("itemType", "Task" );
            taskJson.put("itemName", task.getItemName());
            taskJson.put("itemDescription", task.getItemDescription());
            taskJson.put("createdBy", task.getCreatedBy());
            taskJson.put("category", task.getCategory());
            taskJson.put("dateCreated", task.getDateAdded());
            taskJson.put("dueDate", task.getDueDate());
            taskJson.put("assignedTo", task.getAssignedTo());
            taskJson.put("priority", task.getPriority());
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return taskJson;

    }

    public static User buildUserFromJson(JSONObject json){
        Log.d("", "buildUserFromJson: ");
        return  new User(json);
    }

    public static JSONObject getJsonFromFile() throws JSONException {
        // Check if file exists
        File jsonFile = new File(mFile);
        StringBuffer json = new StringBuffer();
        try {
            //Normal operation - Scan JSON file from disk to string,
            // return new JSONObject
            Scanner scan = new Scanner(jsonFile);
            while (scan.hasNext()){
                json.append(scan.next());
            }
            return new JSONObject(String.valueOf(json));

        } catch (FileNotFoundException e) {
            json.delete(0, json.length());
            json.append("{}");
            return new JSONObject(String.valueOf(json));
        }
    }

    public static boolean writeJsonToFile(JSONObject userJson){
        try {
            FileWriter jsonWrite = new FileWriter(mFile, false);
            jsonWrite.write(String.valueOf(userJson));
            jsonWrite.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


}
