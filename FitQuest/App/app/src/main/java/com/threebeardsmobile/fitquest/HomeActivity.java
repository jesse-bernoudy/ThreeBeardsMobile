package com.threebeardsmobile.fitquest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.threebeardsmobile.fitquest.FitBitApi.FitBitServiceGenerator;
import com.threebeardsmobile.fitquest.FitBitApi.FitBitUser;
import com.threebeardsmobile.fitquest.FitBitApi.JSON.StepHistory;
import com.threebeardsmobile.fitquest.view.WalkingView;

import java.util.HashMap;

public class HomeActivity extends AppCompatActivity implements FitBitUser.FitBitUserListener {
    private final String mClientId = "227MH4";
    private final String mRedirectUri = "http://bobmchenry.com/threebeards";
    private final String mState = Long.toString(mClientId.hashCode() + mRedirectUri.hashCode());
    private FitBitUser mCurrentUser;
    private WalkingView wv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_home);
        //this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        wv = new WalkingView(this, mCurrentUser.getDailyGoal());
        setContentView(wv);

        wv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO add previous and newSteps to control animation.

                wv.animateSprite(
                        mCurrentUser.getCurrentSteps()
                        );
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refreshButton:
                if(mCurrentUser == null) {
                    login();
                } else {
                    mCurrentUser.refresh();
                    mCurrentUser.refreshStepHistory();
                }
                break;
            case R.id.logoutButton:
                if (mCurrentUser != null) {
                    mCurrentUser.logout();
                }
                break;
        }
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mCurrentUser != null) {
            SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(getString(R.string.user_id_key), mCurrentUser.getUserId());
            editor.putString(getString(R.string.access_token_key), mCurrentUser.getToken());
            editor.commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // the intent filter defined in AndroidManifest will handle the return from ACTION_VIEW intent
        final Uri uri = getIntent().getData();
        if (uri != null && uri.toString().startsWith(mRedirectUri)) {
            String[] temp = uri.getFragment().split("&");
            HashMap<String, String> parameters = new HashMap<>();
            for (String s : temp) {
                String[] parameter = s.split("=");
                parameters.put(parameter[0], parameter[1]);
            }
            String state = parameters.get("state");
            String error = parameters.get("error");
            if (error != null) {
                // show an error message here
                showToast("Error: " + error);
            } else if (state.compareTo(mState) != 0) {
                showToast("Invalid URI. Possible cross-site request forgery (CSRF)");
            } else {
                // use the parameter your API exposes for the token
                String token = parameters.get("access_token");
                String userId = parameters.get("user_id");
                showToast("New user logged in.");
                if (token != null && userId != null) {
                    mCurrentUser = new FitBitUser(token, userId, this);
                }
            }
        }

        if (mCurrentUser != null) {
            // ToDo: Add a time check from last refresh
//            final TextView userName = (TextView) findViewById(R.id.userName);
//            userName.post(new Runnable() {
//                public void run() {
//                    userName.setText(mCurrentUser.getDisplayName());
//                }
//            });
            mCurrentUser.refresh();
            mCurrentUser.refreshStepHistory();
        } else {
            SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
            String userId = sharedPref.getString(getString(R.string.user_id_key), "");
            String accessToken = sharedPref.getString(getString(R.string.access_token_key), "");
            if(!userId.isEmpty() && !accessToken.isEmpty()) {
                mCurrentUser = new FitBitUser(userId, accessToken, this);
                mCurrentUser.refresh();
                mCurrentUser.refreshStepHistory();
            }
        }
    }

    @Override
    public void OnUserProfileUpdated() {
//        final TextView userName = (TextView) findViewById(R.id.userName);
//
//        userName.post(new Runnable() {
//            public void run() {
//                userName.setText(mCurrentUser.getDisplayName());
//            }
//        });
    }

    @Override
    public void OnUserActivityUpdated() {
//        final TextView todaysSteps = (TextView) findViewById(R.id.todaysSteps);
//        final TextView dailyGoal = (TextView) findViewById(R.id.dailyGoal);
//
//        todaysSteps.post(new Runnable() {
//            public void run() {
//                todaysSteps.setText(mCurrentUser.getCurrentSteps().toString());
//            }
//        });
//
//        dailyGoal.post(new Runnable() {
//            public void run() {
//                dailyGoal.setText(mCurrentUser.getDailyGoal().toString());
//            }
//        });

    }

    @Override
    public void OnStepHistoryUpdated() {
//        final TextView yesterdaysSteps = (TextView) findViewById(R.id.yesterdaysSteps);
//
//        yesterdaysSteps.post(new Runnable() {
//            public void run() {
//                StringBuilder steps = new StringBuilder();
//                for (Integer s : mCurrentUser.getStepCountHistory()) {
//                    steps.append(s.toString());
//                    steps.append(", ");
//                }
//                yesterdaysSteps.setText(steps.toString());
//            }
//        });
    }

    @Override
    public void OnUserLoggedOut() {
        showToast(mCurrentUser.getDisplayName() + " logged out");
        resetUser();
    }

    private void login() {
        Intent intent = new Intent(
                Intent.ACTION_VIEW,
                Uri.parse(FitBitServiceGenerator.API_LOGIN_URL +
                        "authorize?response_type=token" +
                        "&client_id=" + mClientId +
                        "&redirect_uri=" + mRedirectUri +
                        "&scope=activity heartrate profile" +
                        "&expires_in=2592000" + //30 days
                        "&state=" + mState));
        startActivity(intent);
    }

    private void resetUser() {
        mCurrentUser = null;
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove(getString(R.string.user_id_key));
        editor.remove(getString(R.string.access_token_key));
        editor.commit();

//        final TextView userName = (TextView) findViewById(R.id.userName);
//        userName.post(new Runnable() {
//            public void run() {
//                userName.setText("");
//            }
//        });
//
//        final TextView todaysSteps = (TextView) findViewById(R.id.todaysSteps);
//
//        todaysSteps.post(new Runnable() {
//            public void run() {
//                todaysSteps.setText("");
//            }
//        });
//
//        final TextView dailyGoal = (TextView) findViewById(R.id.dailyGoal);
//
//        dailyGoal.post(new Runnable() {
//            public void run() {
//                dailyGoal.setText("");
//            }
//        });
//
//        final TextView yesterdaysSteps = (TextView) findViewById(R.id.yesterdaysSteps);
//
//        yesterdaysSteps.post(new Runnable() {
//            public void run() {
//                yesterdaysSteps.setText("");
//            }
//        });
    }

    private void showToast(final String message) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void OnTokenExpired() {
        showToast("Login expired, please refresh user");
        resetUser();
    }
}

