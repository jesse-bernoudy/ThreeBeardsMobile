package com.threebeardsmobile.fitquest;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.threebeardsmobile.fitquest.FitBitApi.FitBitServiceGenerator;
import com.threebeardsmobile.fitquest.oauth2.AccessToken;
import com.threebeardsmobile.fitquest.oauth2.LoginApiService;
import com.threebeardsmobile.fitquest.oauth2.ServiceGenerator;

import java.io.IOException;
import java.util.HashMap;

import retrofit2.Call;

public class LoginActivity extends AppCompatActivity {
    // client id
    private final String clientId = "227MH4";
    private final String redirectUri = "http://bobmchenry.com/threebeards";
    private final String mState = Long.toString(clientId.hashCode() + redirectUri.hashCode());

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
                        Uri.parse(FitBitServiceGenerator.API_LOGIN_URL +
                                "authorize?response_type=token" +
                                "&client_id=" + clientId +
                                "&redirect_uri=" + redirectUri +
                                "&scope=activity heartrate profile" +
                                "&expires_in=2592000" + //30 days
                                "&state=" + mState));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //this.finish();
        // the intent filter defined in AndroidManifest will handle the return from ACTION_VIEW intent
        final Uri uri = getIntent().getData();
        if (uri != null && uri.toString().startsWith(redirectUri)) {
            String[] temp = uri.getFragment().split("&");
            HashMap<String, String> parameters = new HashMap<>();
            for(String s: temp){
                String[] parameter = s.split("=");
                parameters.put(parameter[0], parameter[1]);
            }
            String state = parameters.get("state");
            String error = parameters.get("error");
            if (error != null) {
                // show an error message here
                Intent output = new Intent(this, HomeActivity.class);
                output.putExtra(HomeActivity.ERROR_MSG, error);
                setResult(RESULT_CANCELED, null);
            } else if (state.compareTo(mState) != 0) {
                Intent output = new Intent(this, HomeActivity.class);
                output.putExtra(HomeActivity.ERROR_MSG, "Invalid URI. Possible cross-site request forgery (CSRF)");
                setResult(RESULT_CANCELED, null);
            } else {
                // use the parameter your API exposes for the token
                String token = parameters.get("access_token");
                String userId = parameters.get("user_id");
                if (token != null && userId != null) {
                    Intent output = new Intent(this, HomeActivity.class);
                    output.putExtra(HomeActivity.USER_ACCESS_TOKEN, token);
                    output.putExtra(HomeActivity.USER_ID, userId);
                    setResult(RESULT_OK, output);
                }
            }
            finish();
        }
    }
}
