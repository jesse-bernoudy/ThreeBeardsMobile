package com.threebeardsmobile.fitquest.FitBitApi;

import android.util.Log;

import com.threebeardsmobile.fitquest.FitBitApi.JSON.UserActivity;
import com.threebeardsmobile.fitquest.FitBitApi.JSON.UserProfile;
import com.threebeardsmobile.fitquest.oauth2.ServiceGenerator;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Jesse on 5/28/2016.
 */
public class FitBitUser {
    // GET https://api.fitbit.com/1/user/[user-id]/profile.json

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private String token;
    private String userId;

    private String displayName;

    private int mTodaysSteps;
    private int mYesterdaysSteps;
    private Date mLastUpdated;

    private UserProfile profile;
    private UserActivity activity;

    public FitBitUser(String token, String userId){
        this.token = token;
        this.userId = userId;
    }

    public void refresh() {
        FitBitApiService fitBitApiService = ServiceGenerator.createService(FitBitServiceGenerator.USER_BASE_URL, FitBitApiService.class, token);
        Call<UserProfile> refreshUserProfile = fitBitApiService.getUserProfile(userId);
        refreshUserProfile.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                if (response.isSuccessful()) {
                    profile = response.body();
                } else {
                    // error response, no access to resource?
                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {
                // something went completely south (like no internet connection)
                Log.d("Error", t.getMessage());
            }
        });

        Call<UserActivity> refreshActivity = fitBitApiService.getUserActivity(userId, dateFormat.format(new Date()));
        refreshActivity.enqueue(new Callback<UserActivity>() {
            @Override
            public void onResponse(Call<UserActivity> call, Response<UserActivity> response) {
                if (response.isSuccessful()) {
                    activity = response.body();
                } else {
                    // error response, no access to resource?
                }
            }

            @Override
            public void onFailure(Call<UserActivity> call, Throwable t) {
                // something went completely south (like no internet connection)
                Log.d("Error", t.getMessage());
            }
        });
    }
}
