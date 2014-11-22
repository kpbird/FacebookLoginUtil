package com.kpbird.facebookloginutils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MyActivity extends Activity implements FacebookLoginUtils.FacebookLoginStatus {
    private String TAG = this.getClass().getSimpleName();
    private FacebookLoginUtils fLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        fLogin = new FacebookLoginUtils(this, R.id.activity_login_fb);
        fLogin.setLoginStatus(this);
        fLogin.setEnable(true);
    }

    protected void onResume() {
        super.onResume();
        fLogin.onResume();
    };

    @Override
    protected void onPause() {
        super.onPause();
        fLogin.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        fLogin.onDestroy();
    }


    @Override
    protected void onActivityResult(int requestCode, int responseCode,Intent intent) {
        fLogin.onActivityResult(requestCode, responseCode, intent);
    }

    @Override
    public void OnSuccessFacebookLogin(Bundle profile) {
        Log.i(TAG,profile.getString(FacebookLoginUtils.USERID));
        Log.i(TAG,profile.getString(FacebookLoginUtils.EMAIL));
        Log.i(TAG,profile.getString(FacebookLoginUtils.NAME));
        Log.i(TAG,profile.getString(FacebookLoginUtils.FIRST_NAME));
        Log.i(TAG,profile.getString(FacebookLoginUtils.LAST_NAME));

    }
}
