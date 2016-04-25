package com.threebeardsmobile.taskapp.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public abstract class ToDoItem
{
    /**
     * @// TODO: 4/16/16 Add javadoc to ToDoItem methods
     */
    long itemID;
    String itemName;
    String itemDescription;
    String createdBy;
    String category; //Consider Enum for filtering
    Date dateAdded;
    Date dueDate;
    int priority;

    public ToDoItem(JSONObject projectJson) throws JSONException {
        itemID = (long) projectJson.get("itemID");
        itemName = (String) projectJson.get("itemName");
        itemDescription = (String) projectJson.get("itemDescription");
        createdBy = (String) projectJson.get("createdBy");
        dateAdded = (Date) projectJson.get("dateAdded");
        dueDate = (Date) projectJson.get("dueDate");
        priority = (int) projectJson.get("priority");
    }

    public long getItemID() {
        return itemID;
    }

    public void setItemID(long itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}