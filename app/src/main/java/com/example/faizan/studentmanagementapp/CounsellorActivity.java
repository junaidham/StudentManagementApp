package com.example.faizan.studentmanagementapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.faizan.studentmanagementapp.constants.AppConstants;

public class CounsellorActivity extends AppCompatActivity
{
    Button btnreg,btnshowall;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counsellor);

        btnreg =(Button) findViewById(R.id.reg);
        btnreg.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(CounsellorActivity.this,Registration.class);
                startActivity(in);
            }
        });
        btnshowall = (Button) findViewById(R.id.show);
        btnshowall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CounsellorActivity.this,StudentList.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.counseller_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.addnew:
                Intent in1 = new Intent(CounsellorActivity.this,Registration.class);
                startActivity(in1);
                break;
            case R.id.showall:
                Intent in2 = new Intent(CounsellorActivity.this,StudentList.class);
                startActivity(in2);
                break;
            case R.id.logout:
                Intent in3 = new Intent(CounsellorActivity.this,MainActivity.class);
                startActivity(in3);
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(AppConstants.PREF, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                this.finish();
        }

        return super.onOptionsItemSelected(item);

    }
}
