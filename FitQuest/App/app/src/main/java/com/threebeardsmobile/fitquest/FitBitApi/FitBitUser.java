package com.threebeardsmobile.fitquest.FitBitApi;

import android.util.Log;

import com.threebeardsmobile.fitquest.FitBitApi.JSON.ActivitiesStep;
import com.threebeardsmobile.fitquest.FitBitApi.JSON.StepHistory;
import com.threebeardsmobile.fitquest.FitBitApi.JSON.UserActivity;
import com.threebeardsmobile.fitquest.FitBitApi.JSON.UserProfile;
import com.threebeardsmobile.fitquest.oauth2.ServiceGenerator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Jesse on 5/28/2016.
 */
public class FitBitUser {
    // GET https://api.fitbit.com/1/user/[user-id]/profile.json

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public String getToken() {
        return mToken;
    }

    public String getUserId() {
        return mUserId;
    }

    public String getDisplayName() {
        return mDisplayName;
    }

    public Integer getCurrentSteps() {
        return mCurrentSteps;
    }

    public Date getLastUpdated() {
        return mLastUpdated;
    }

    public Integer getDailyGoal() {
        return mDailyGoal;
    }

    public List<Integer> getStepCountHistory() {
        return mStepCountHistory;
    }

    private String mToken;
    private String mUserId;
    private String mDisplayName;
    private Integer mCurrentSteps;
    private Integer mDailyGoal;
    private Date mLastUpdated;
    private List<Integer> mStepCountHistory;

    private FitBitUserListener mListener;
    private FitBitApiService mFitBitApiService;

    public interface FitBitUserListener {
        void OnUserProfileUpdated();
        void OnUserActivityUpdated();
        void OnStepHistoryUpdated();
        void OnUserLoggedOut();
    }

    public FitBitUser(String token, String userId, FitBitUserListener listener) {
        mToken = token;
        mUserId = userId;
        mListener = listener;
        mFitBitApiService = ServiceGenerator.createService(FitBitServiceGenerator.USER_BASE_URL, FitBitApiService.class, mToken);
        refreshProfile();
    }

    private void refreshProfile() {
        Call<UserProfile> refreshUserProfile = mFitBitApiService.getUserProfile(mUserId);
        refreshUserProfile.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                if (response.isSuccessful()) {
                    UserProfile profile = response.body();
                    mDisplayName = profile.getUser().getDisplayName();
                    mListener.OnUserProfileUpdated();
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
    }

    public void refresh() {
        Call<UserActivity> refreshActivity = mFitBitApiService.getUserActivity(mUserId, dateFormat.format(new Date()));
        refreshActivity.enqueue(new Callback<UserActivity>() {
            @Override
            public void onResponse(Call<UserActivity> call, Response<UserActivity> response) {
                if (response.isSuccessful()) {
                    UserActivity activity = response.body();
                    mCurrentSteps = activity.getSummary().getSteps();
                    mLastUpdated = new Date();
                    mDailyGoal = activity.getGoals().getSteps();
                    mListener.OnUserActivityUpdated();

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

    public void refreshStepHistory() {
        Call<StepHistory> userStepHistory = mFitBitApiService.getUserStepHistory(mUserId);
        userStepHistory.enqueue(new Callback<StepHistory>() {
            @Override
            public void onResponse(Call<StepHistory> call, Response<StepHistory> response) {
                if (response.isSuccessful()) {
                    StepHistory stepHistory = response.body();
                    mStepCountHistory = new ArrayList<>();
                    List<ActivitiesStep> steps = stepHistory.getActivitiesSteps();
                    for (int i = steps.size() - 2; i > steps.size() - 5; i--) {
                        mStepCountHistory.add(steps.get(i).getValue());
                    }
                    mListener.OnStepHistoryUpdated();
                } else {
                    // error response, no access to resource?
                }
            }

            @Override
            public void onFailure(Call<StepHistory> call, Throwable t) {
                // something went completely south (like no internet connection)
                Log.d("Error", t.getMessage());
            }
        });
    }

    public void logout() {
        FitBitApiService fitBitApiService = ServiceGenerator.createService(FitBitServiceGenerator.API_LOGOUT_URL, FitBitApiService.class, mToken);
        Call<ResponseBody> logout = fitBitApiService.logout(mToken);
        logout.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    mListener.OnUserLoggedOut();
                } else {
                    // error response, no access to resource?
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                // something went completely south (like no internet connection)
                Log.d("Error", t.getMessage());
            }
        });
    }
}
