package com.threebeardsmobile.fitquest.FitBitApi.JSON;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Jesse on 5/30/2016.
 */
public class Features {

    @SerializedName("exerciseGoal")
    @Expose
    private Boolean exerciseGoal;

    /**
     * @return The exerciseGoal
     */
    public Boolean getExerciseGoal() {
        return exerciseGoal;
    }

    /**
     * @param exerciseGoal The exerciseGoal
     */
    public void setExerciseGoal(Boolean exerciseGoal) {
        this.exerciseGoal = exerciseGoal;
    }
}
