package com.threebeardsmobile.fitquest.FitBitApi;

import android.util.Log;

import com.threebeardsmobile.fitquest.FitBitApi.JSON.Activity;
import com.threebeardsmobile.fitquest.FitBitApi.JSON.User;
import com.threebeardsmobile.fitquest.oauth2.ServiceGenerator;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Jesse on 5/28/2016.
 */
public class FitBitUser {
    // GET https://api.fitbit.com/1/user/[user-id]/profile.json

    private String token;
    private String userId;

    private String displayName;

    private int mTodaysSteps;
    private int mYesterdaysSteps;
    private Date mLastUpdated;

    private User profile;
    private Activity activities;

    public FitBitUser(String token, String userId){
        this.token = token;
        this.userId = userId;
    }

    public void refresh() {
        FitBitApiService fitBitApiService = ServiceGenerator.createService(FitBitServiceGenerator.USER_BASE_URL, FitBitApiService.class, token);
        Call<User> call = fitBitApiService.getUserProfile(userId);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    profile = response.body();
                } else {
                    // error response, no access to resource?
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // something went completely south (like no internet connection)
                Log.d("Error", t.getMessage());
            }
        });
    }
}
