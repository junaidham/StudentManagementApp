package com.example.faizan.studentmanagementapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.faizan.studentmanagementapp.constants.AppConstants;

public class SplashScreenActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        sharedPreferences = getApplicationContext().getSharedPreferences(AppConstants.PREF, Context.MODE_PRIVATE); //1

        new Handler().postDelayed(new Runnable() {

            // Using handler with postDelayed called runnable run method

            @Override
            public void run() {

                if(sharedPreferences.getBoolean(AppConstants.IS_LOGGEDIN,false)){
                    Intent i = new Intent(SplashScreenActivity.this, CounsellorActivity.class);
                    startActivity(i);

                }else{
                    Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(i);

                }


                // close this activity
                finish();
            }
        }, 5*1000); // wait for 5 seconds

    }

}

