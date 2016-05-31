package com.threebeardsmobile.fitquest;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.threebeardsmobile.fitquest.FitBitApi.FitBitServiceGenerator;
import com.threebeardsmobile.fitquest.FitBitApi.FitBitUser;

import java.util.HashMap;

public class HomeActivity extends AppCompatActivity {
    public static final int GET_USER_ACCESS_TOKEN = 1;  // The request code
    public static final String USER_ACCESS_TOKEN = "access_token";
    public static final String USER_ID = "user_id";
    public static final String ERROR_MSG = "error_msg";
    private final String clientId = "227MH4";
    private final String redirectUri = "http://bobmchenry.com/threebeards";
    private final String mState = Long.toString(clientId.hashCode() + redirectUri.hashCode());
    private FitBitUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // ToDo: Check to see if the user is authenticated and if not, send them to the login page
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
        //Intent intent = new Intent(this, LoginActivity.class);
        //startActivityForResult(intent, GET_USER_ACCESS_TOKEN);
    }

    @Override
    protected void onResume() {
        super.onResume();
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
                Toast.makeText(this, "Error: " + error , Toast.LENGTH_LONG);
            } else if (state.compareTo(mState) != 0) {
                Toast.makeText(this, "Invalid URI. Possible cross-site request forgery (CSRF)", Toast.LENGTH_LONG);
            } else {
                // use the parameter your API exposes for the token
                String token = parameters.get("access_token");
                String userId = parameters.get("user_id");
                if (token != null && userId != null) {
                    currentUser = new FitBitUser(token, userId);
                    currentUser.refresh();
                }
            }
        }
    }

}

