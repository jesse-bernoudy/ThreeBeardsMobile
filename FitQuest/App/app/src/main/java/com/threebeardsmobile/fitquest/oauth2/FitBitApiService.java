package com.threebeardsmobile.fitquest.oauth2;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface FitBitApiService {

    // Get an access token
    // client_id=22942C&grant_type=authorization_code&code=1234567890
    @FormUrlEncoded
    @POST("token")
    Call<AccessToken> getAccessToken(
            @Field("client_id") String clientId,
            @Field("grant_type") String grantType,
            @Field("code") String code);

    // Refresh the access token
    //grant_type=refresh_token&refresh_token=abcdef01234567890abcdef01234567890abcdef01234567890abcdef0123456
    @FormUrlEncoded
    @POST("token")
    Call<AccessToken> refreshAccessToken(
            @Field("grant_type") String grantType,
            @Field("refresh_token") String refreshToken);

}