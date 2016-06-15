package com.threebeardsmobile.fitquest.FitBitApi.JSON;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StepHistory {

    @SerializedName("activities-steps")
    @Expose
    private List<ActivitiesStep> activitiesSteps = new ArrayList<ActivitiesStep>();

    /**
     * @return The activitiesSteps
     */
    public List<ActivitiesStep> getActivitiesSteps() {
        return activitiesSteps;
    }

    /**
     * @param activitiesSteps The activities-steps
     */
    public void setActivitiesSteps(List<ActivitiesStep> activitiesSteps) {
        this.activitiesSteps = activitiesSteps;
    }

}
