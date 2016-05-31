package com.threebeardsmobile.fitquest.FitBitApi;

import com.threebeardsmobile.fitquest.FitBitApi.JSON.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Jesse on 5/28/2016.
 */
public interface FitBitApiService {

    @GET("{userId}/profile.json")
    Call<User> getUserProfile(@Path("userId") String userId);
}
