package com.threebeardsmobile.fitquest.oauth2;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginApiService {

    // Refresh the access token
    //grant_type=refresh_token&refresh_token=abcdef01234567890abcdef01234567890abcdef01234567890abcdef0123456
    @FormUrlEncoded
    @POST("token")
    Call<AccessToken> refreshAccessToken(
            @Field("grant_type") String grantType,
            @Field("refresh_token") String refreshToken);

}