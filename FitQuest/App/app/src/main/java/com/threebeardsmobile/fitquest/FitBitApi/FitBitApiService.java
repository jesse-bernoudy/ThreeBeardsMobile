package com.threebeardsmobile.fitquest.FitBitApi;

import com.threebeardsmobile.fitquest.FitBitApi.JSON.UserActivity;
import com.threebeardsmobile.fitquest.FitBitApi.JSON.UserProfile;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Jesse on 5/28/2016.
 */
public interface FitBitApiService {

    @GET("{userId}/profile.json")
    Call<UserProfile> getUserProfile(@Path("userId") String userId);


    @GET("{userId}/activities/date/{date}.json")
    Call<UserActivity> getUserActivity(@Path("userId") String userId, @Path("date") String date);
}
