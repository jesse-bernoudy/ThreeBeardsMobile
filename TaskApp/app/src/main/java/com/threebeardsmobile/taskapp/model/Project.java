package com.threebeardsmobile.taskapp.model;

import java.util.ArrayList;

/**
 * Created by DUB on 4/16/16.
 */
public class Project extends TaskBase {

    //Collection for child projects and tasks

    //Percent complete method

    //Project Owner field

    //Constructors

    private ArrayList<TaskBase> tasks;
    public ArrayList<TaskBase> getTasks() {
        return tasks;
    }

    public Project() {
        tasks = new ArrayList<>();
    }

    public void AddTask(TaskBase task) {
        tasks.add(task);
    }

}
