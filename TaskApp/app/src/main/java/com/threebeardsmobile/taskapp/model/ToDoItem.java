package com.threebeardsmobile.taskapp.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * This is an abstract class that you can use to create either a task or
 * a project from.  Projects can hold other projects or other tasks or both.
 * @author Bob
 * @version 1.0
 */
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

    public ToDoItem() {

    }

    /**
     * Constructor for making a ToDoItem.  Sets all of the ToDoItems
     * values based on the information from the JSON file.
     * @param projectJson
     * @throws JSONException
     */
    public ToDoItem(JSONObject projectJson) throws JSONException {
        itemID = (long) projectJson.get("itemID");
        itemName = (String) projectJson.get("itemName");
        itemDescription = (String) projectJson.get("itemDescription");
        createdBy = (String) projectJson.get("createdBy");
        dateAdded = (Date) projectJson.get("dateAdded");
        dueDate = (Date) projectJson.get("dueDate");
        priority = (int) projectJson.get("priority");
    }

    /**
     * Overloaded constructor for explicit creation of a task giving
     * the parameters directly.
     * @param itemName
     * @param itemDescription
     * @param createdBy
     * @param category
     * @param dateAdded
     * @param dueDate
     * @param priority
     */
    public ToDoItem( String itemName, String itemDescription, String createdBy,
                    String category, Date dateAdded, Date dueDate, int priority)
    {
        this.itemID = 1;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.createdBy = createdBy;
        this.category = category;
        this.dateAdded = dateAdded;
        this.dueDate = dueDate;
        this.priority = priority;
    }

    /**
     * Call to return the unique item id of this project or task
     * @return itemID
     */
    public long getItemID() {
        return itemID;
    }

//    public void setItemID(long itemID) {
//        this.itemID = itemID;
//    }

    /**
     * Returns the name of this item.
     * @return itemName
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Sets the itemName to the given value
     * @param itemName
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * Returns the itemDescription of the object
     * @return
     */
    public String getItemDescription() {
        return itemDescription;
    }

    /**
     * Sets the itemDescription
     * @param itemDescription
     */
    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    /**
     * Returns createdBy
     * @return String createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

//    public void setCreatedBy(String createdBy) {
//        this.createdBy = createdBy;
//    }

    /**
     * returns the category
     * @return String category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Set the category
     * @param category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Get the dateAdded
     * @return dateAdded;
     */
    public Date getDateAdded() {
        return dateAdded;
    }

//    public void setDateAdded(Date dateAdded) {
//        this.dateAdded = dateAdded;
//    }

    /**
     * return dueDate
     * @return dueDate
     */
    public Date getDueDate() {
        return dueDate;
    }

    /**
     * Set dueDate
     * @param dueDate
     */
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Returns the priority
     * @return priority
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Sets the priority
     * @param priority
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }
}