package com.threebeardsmobile.fitquest.FitBitApi.JSON;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Jesse on 5/30/2016.
 */
public
class Distance {

    @SerializedName("activity")
    @Expose
    private String activity;
    @SerializedName("distance")
    @Expose
    private Integer distance;

    /**
     * @return The activity
     */
    public String getActivity() {
        return activity;
    }

    /**
     * @param activity The activity
     */
    public void setActivity(String activity) {
        this.activity = activity;
    }

    /**
     * @return The distance
     */
    public Integer getDistance() {
        return distance;
    }

    /**
     * @param distance The distance
     */
    public void setDistance(Integer distance) {
        this.distance = distance;
    }

}

