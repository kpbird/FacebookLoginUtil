package com.kpbird.facebookloginutils;

import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.facebook.FacebookException;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import com.facebook.widget.LoginButton.OnErrorListener;
import com.facebook.widget.LoginButton.UserInfoChangedCallback;

public class FacebookLoginUtils implements UserInfoChangedCallback,OnErrorListener {
	private String TAG = "FacebookLoginUtils";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String USERID = "userid";

	private Context ctx;

	private LoginButton loginBtn;
	private UiLifecycleHelper uiHelper;
	private static final List<String> PERMISSIONS = Arrays.asList("public_profile", "email");
	private FacebookLoginStatus loginstatus;
	
	public interface FacebookLoginStatus{
		public void OnSuccessFacebookLogin(Bundle profile);
	}
	
	public FacebookLoginUtils(Context ctx,int btnRes){
		this.ctx = ctx;
		loginBtn = (LoginButton)((Activity)ctx).findViewById(btnRes);
		uiHelper = new UiLifecycleHelper((Activity)ctx, statusCallback);
		loginBtn.setReadPermissions(PERMISSIONS);
		loginBtn.setOnErrorListener(this);
		loginBtn.setUserInfoChangedCallback(this);

	}
	public void setEnable(boolean enabled){
		loginBtn.setEnabled(enabled);
	}
	public void setLoginStatus(FacebookLoginStatus loginStatus){
		this.loginstatus = loginStatus;
	}
	public void onResume(){
		uiHelper.onResume();
	}
	public void onPause(){
		uiHelper.onPause();
	}
	public void onDestroy(){
		uiHelper.onDestroy();
	}
	public void onActivityResult(int requestCode,int responseCode,Intent intent){
		uiHelper.onActivityResult(requestCode, responseCode, intent);
	}

	private Session.StatusCallback statusCallback = new Session.StatusCallback() {
		@Override
		public void call(Session session, SessionState state,
				Exception exception) {
			if (state.isOpened()) {
				Log.d("FacebookSampleActivity", "Facebook session opened");
				Log.i(TAG, "Access Token" + session.getAccessToken());
			} else if (state.isClosed()) {
				Log.d("FacebookSampleActivity", "Facebook session closed");
			}
		}
	};

	@Override
	public void onUserInfoFetched(GraphUser user) {
		if (user != null) {
			Log.i(TAG, "Hello " + user.getName());
			Log.i(TAG, "Email " + user.asMap().get("email").toString());
			Log.i(TAG, "User ID " + user.getId());
			Bundle profile = new Bundle();
			profile.putString(NAME, user.getName());
			profile.putString(EMAIL, user.asMap().get("email").toString());
			profile.putString(USERID, user.getId());
			profile.putString(FIRST_NAME,user.getFirstName());
			profile.putString(LAST_NAME, user.getLastName());
			loginstatus.OnSuccessFacebookLogin(profile);
		} else {
			Log.i(TAG, "You are not logged in");

		}		
	}

	@Override
	public void onError(FacebookException error) {
		Log.i(TAG,"onError: "+ error.getMessage());
		error.printStackTrace();
			
	}
}
