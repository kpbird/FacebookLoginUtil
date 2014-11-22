Android Login with Facebook Util
----------
### Pre-requisite###

Official Document: https://developers.facebook.com/docs/android/

Official Tutorial: https://developers.facebook.com/docs/android/login-with-facebook/v2.2

Previous Document: http://www.kpbird.com/2013/03/android-login-using-facebook-sdk-30.html

1. Facebook Android SDK 3.0 -[Download](https://github.com/facebook/facebook-android-sdk)
2. Facebook API Key from Facebook Application - [Detail](https://developers.facebook.com/docs/android/getting-started)
3. Hash Key of your debug certificate  - [Detail](http://sonyarouje.com/2011/09/18/facebook-hash-key-for-android-apps/)

### Usage ###

#### Step 1: Import "FacebookLoginUtils.java" class in your package
#### Step 2: Implement callback listener
	public class MyActivity extends Activity implements FacebookLoginUtils.FacebookLoginStatus 

#### Step 3: Declare callback method
    @Override
    public void OnSuccessFacebookLogin(Bundle profile) {
        Log.i(TAG,profile.getString(FacebookLoginUtils.USERID));
        Log.i(TAG,profile.getString(FacebookLoginUtils.EMAIL));
        Log.i(TAG,profile.getString(FacebookLoginUtils.NAME));
        Log.i(TAG,profile.getString(FacebookLoginUtils.FIRST_NAME));
        Log.i(TAG,profile.getString(FacebookLoginUtils.LAST_NAME));

    }
#### Step 4: Declare object of GooglePlusLoginUtils.java
    private FacebookLoginUtils fLogin;

#### Step 5: Initialize object in onCreate method
        fLogin = new FacebookLoginUtils(this, R.id.activity_login_fb);
        fLogin.setLoginStatus(this);
        fLogin.setEnable(true);
#### Step 6: Declare few supporting methods 

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

### FAQ###
Q: What is the problem of above approach ?

A: You activity has a lot of scattered code for Facebook login, It will be more complex and unmanageable when you will have another social network login like Facebook and Twitter.

Q: How I will receive a response for login success?

A: You need to implement interface "FacebookLoginStatus", on successful login you will receive callback "OnSuccessFacebookLogin"

Q: What will be received on login success ?

A: You will have Name, Email , First Name, Last Name, User Id.

Q: Do I need to handle login failure / error ?

A: No, FacebookLoginUtils.java will handle login failure, error and display appropriate message on screen.   


   
