package com.threebeardsmobile.taskapp.controller;

import com.threebeardsmobile.taskapp.model.Project;
import com.threebeardsmobile.taskapp.model.Task;
import com.threebeardsmobile.taskapp.model.ToDoItem;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by bob on 4/24/16.
 */
public class JSONController {

    private JSONController(){ /* No instantiaton */}

    public static JSONObject getJsonFromProject(Project project){
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

    private static JSONObject getJsonFromTask(Task task) throws JSONException {
        JSONObject taskJson = new JSONObject();

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

        return taskJson;

    }


}
