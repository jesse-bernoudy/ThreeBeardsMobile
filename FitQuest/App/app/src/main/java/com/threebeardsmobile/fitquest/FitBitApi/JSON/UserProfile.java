package com.threebeardsmobile.fitquest.FitBitApi.JSON;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Jesse on 5/31/2016.
 */
public class UserProfile {
    @SerializedName("user")
    @Expose
    private User user;

    /**
     * @return The User
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user The User
     */
    public void setUser(User user) {
        this.user = user;
    }

}
