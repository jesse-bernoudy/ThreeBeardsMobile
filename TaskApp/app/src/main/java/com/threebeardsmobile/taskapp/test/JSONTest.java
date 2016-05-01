package com.threebeardsmobile.taskapp.test;

import com.threebeardsmobile.taskapp.controller.JSONController;
import com.threebeardsmobile.taskapp.model.Project;
import com.threebeardsmobile.taskapp.model.Task;
import com.threebeardsmobile.taskapp.model.ToDoItem;
import com.threebeardsmobile.taskapp.model.User;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by DUB on 4/24/16.
 */
public class JSONTest {

    public static String jsonTestBuild (){

        User testUser = new User(999, "Bob");
        ArrayList<ToDoItem> list =  testUser.getRootProject().getChildItems();


        Project testProject = new Project(
                        100, "Test Project", "this is a test project", testUser.getUserName(),
                        "Test", new Date(), new Date(), 3, new ArrayList<ToDoItem>(),
                        testUser.getUserName(), testUser.getRootProject());



        Task testTask = new Task(101, "Test Task", "this is a test Task", "bob", "Test", new Date(), new Date(),
                3, "Bob M" );

        Task testTask2 = new Task(101, "Test Task2", "this is a nested test Task", "bob", "Test", new Date(), new Date(),
                3, "Bob M" );

        list.add(testProject);
        list.add(testTask);

        testProject.getChildItems().add(testTask2);

        JSONObject testJson = JSONController.getJsonFromUser(testUser);

        return testJson.toString();


    }

}
