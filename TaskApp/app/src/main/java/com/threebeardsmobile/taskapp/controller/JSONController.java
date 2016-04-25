package com.threebeardsmobile.taskapp.controller;

import com.threebeardsmobile.taskapp.model.Project;
import com.threebeardsmobile.taskapp.model.Task;
import com.threebeardsmobile.taskapp.model.ToDoItem;
import com.threebeardsmobile.taskapp.model.User;

import org.json.JSONArray;
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
//        User user = new User();
        return null;
    }


}
