package com.threebeardsmobile.fitquest;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.threebeardsmobile.fitquest.oauth2.AccessToken;
import com.threebeardsmobile.fitquest.oauth2.FitBitApiService;
import com.threebeardsmobile.fitquest.oauth2.ServiceGenerator;

import java.io.IOException;

import retrofit2.Call;

public class LoginActivity extends AppCompatActivity {
    // client id and secret
    private final String clientId = "227MH4";
    private final String clientSecret = "2264228e5da6c5fb975eefd04147a83e";
    private final String redirectUri = "http://bobmchenry.com/threebeards";

    private AccessToken accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = (Button) findViewById(R.id.loginbutton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(ServiceGenerator.API_LOGIN_URL + "authorize?response_type=code&client_id=" + clientId + "&redirect_uri=" + redirectUri + "&scope=activity heartrate"));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // the intent filter defined in AndroidManifest will handle the return from ACTION_VIEW intent
        final Uri uri = getIntent().getData();
        if (uri != null && uri.toString().startsWith(redirectUri)) {
            // use the parameter your API exposes for the code (mostly it's "code")
            final String code = uri.getQueryParameter("code");
            if (code != null) {
                // network operation shouldn't run on main thread
                new Thread(new Runnable() {
                    public void run() {
                        // get access token
                        FitBitApiService fitBitApiService =
                                ServiceGenerator.createService(FitBitApiService.class, clientId, clientSecret);
                        Call<AccessToken> call = fitBitApiService.getAccessToken(clientId, "authorization_code", code);
                        try {
                            AccessToken accessToken = call.execute().body();
                            if(accessToken != null) {
                                if (accessToken.getAccessToken().isEmpty()) {
                                    Call<AccessToken> refresh = fitBitApiService.refreshAccessToken("refresh_token", code);
                                    accessToken = refresh.execute().body();
                                }
                            }
                            Log.d("", "AccessToken: " + accessToken);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            } else if (uri.getQueryParameter("error") != null) {
                // show an error message here
            }
        }
    }
}
