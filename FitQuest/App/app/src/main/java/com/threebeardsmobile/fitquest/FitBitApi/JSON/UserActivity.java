package com.threebeardsmobile.fitquest.FitBitApi.JSON;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class UserActivity {

    @SerializedName("activities")
    @Expose
    private List<Activity> activities = new ArrayList<Activity>();
    @SerializedName("goals")
    @Expose
    private Goals goals;
    @SerializedName("summary")
    @Expose
    private Summary summary;

    /**
     *
     * @return
     * The activities
     */
    public List<Activity> getActivities() {
        return activities;
    }

    /**
     *
     * @param activities
     * The activities
     */
    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    /**
     *
     * @return
     * The goals
     */
    public Goals getGoals() {
        return goals;
    }

    /**
     *
     * @param goals
     * The goals
     */
    public void setGoals(Goals goals) {
        this.goals = goals;
    }

    /**
     *
     * @return
     * The summary
     */
    public Summary getSummary() {
        return summary;
    }

    /**
     *
     * @param summary
     * The summary
     */
    public void setSummary(Summary summary) {
        this.summary = summary;
    }

}