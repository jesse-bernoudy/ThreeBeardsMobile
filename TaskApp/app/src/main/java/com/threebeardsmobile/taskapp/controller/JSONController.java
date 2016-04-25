package com.threebeardsmobile.taskapp.controller;

import com.threebeardsmobile.taskapp.model.Project;
import com.threebeardsmobile.taskapp.model.Task;
import com.threebeardsmobile.taskapp.model.ToDoItem;
import com.threebeardsmobile.taskapp.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by bob on 4/24/16.
 */
public class JSONController {

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

        try{
            projectJson.put("parentProject", project.getParentProject());
            projectJson.put("itemID", project.getItemID());
            projectJson.put("itemType", "Project");
            projectJson.put("itemName", project.getItemID());
            projectJson.put("itemDescription", project.getItemID());
            projectJson.put("createdBy", project.getItemID());
            projectJson.put("category", project.getItemID());
            projectJson.put("dateCreated", project.getItemID());
            projectJson.put("dueDate", project.getItemID());
            projectJson.put("assignedTo", project.getItemID());
            projectJson.put("priority", project.getPriority());

            for (ToDoItem item : project.getChildItems()){
                if (item instanceof Project){
                    JSONObject subproject = getJsonFromProject((Project)item);
                    projectJson.put("items", subproject);
                } else {
                    projectJson.put("items", getJsonFromTask((Task)item));
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
            taskJson.put("itemDescription", task.getItemID());
            taskJson.put("createdBy", task.getItemID());
            taskJson.put("category", task.getItemID());
            taskJson.put("dateCreated", task.getItemID());
            taskJson.put("dueDate", task.getItemID());
            taskJson.put("assignedTo", task.getItemID());
            taskJson.put("priority", task.getPriority());
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return taskJson;

    }

    public static User buildUserFromJson(JSONObject json){
        User user = new User();
        return null;
    }


}
