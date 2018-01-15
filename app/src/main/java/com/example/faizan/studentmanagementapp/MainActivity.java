package com.example.faizan.studentmanagementapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.faizan.studentmanagementapp.constants.AppConstants;
import com.example.faizan.studentmanagementapp.db.DatabaseHandler;

public class MainActivity extends AppCompatActivity
{

    EditText nameEdt,passEdt;
    Button submit;
    DatabaseHandler handler;
    //Declaring SharedPreference
    SharedPreferences sharedPreferences=null;
    //UserSession userSession;
   // private static final String PREFER_NAME= null;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        handler= new DatabaseHandler(getApplicationContext());
       long id = handler.createUser();
        if(id>0){
          Toast.makeText(MainActivity.this," User Created!",Toast.LENGTH_SHORT).show();
        }

        nameEdt = (EditText) findViewById(R.id.name);
        passEdt = (EditText) findViewById(R.id.pass);
        submit = (Button) findViewById(R.id.submit);

        //User session manager
        //settings = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPreferences = getApplicationContext().getSharedPreferences(AppConstants.PREF, Context.MODE_PRIVATE); //1



        submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String name=nameEdt.getText().toString();
                String pass=passEdt.getText().toString();

                    String dbPass= handler.loginUser(name);

                if(dbPass.equalsIgnoreCase(pass))
                {
                    Toast.makeText(MainActivity.this,"Login Success",Toast.LENGTH_SHORT).show();
                    Intent in = new Intent(MainActivity.this,CounsellorActivity.class);

                    SharedPreferences.Editor editor = sharedPreferences.edit(); //2

                    editor.putBoolean(AppConstants.IS_LOGGEDIN, true); //3
                    editor.commit(); //4
                    startActivity(in);
                    finish();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Invalid Credential!",Toast.LENGTH_SHORT).show();
                }

            }
        });

        }
}