package com.example.faizan.studentmanagementapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.faizan.studentmanagementapp.Model.Student;
import com.example.faizan.studentmanagementapp.db.DatabaseHandler;

public class Registration extends AppCompatActivity {
    Button submit;
    EditText etname;
    EditText etmobile;
    EditText etemail;
    RadioGroup rgGender;
    RadioButton rgender;
    CheckBox android, ios;
    Student std=new Student();
    DatabaseHandler handler;
    long userID=0;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        etname = (EditText) findViewById(R.id.etname);
        etmobile = (EditText) findViewById(R.id.mobile);
        etemail = (EditText) findViewById(R.id.email);

        android = (CheckBox) findViewById(R.id.chkAndroid);
        ios = (CheckBox) findViewById(R.id.chkIos);
        rgGender = (RadioGroup) findViewById(R.id.gender);
        submit = (Button) findViewById(R.id.btnsubmit);

        Intent in = getIntent();
         userID= in.getLongExtra("id",0);
        if(userID>0)
        {
            etname.setText(in.getStringExtra("name"));
            etemail.setText(in.getStringExtra("email"));
            etmobile.setText(in.getStringExtra("mobile"));
            if(in.getStringExtra("gender").equalsIgnoreCase("male")){
                rgender= (RadioButton) findViewById(R.id.rmale);
            }else{
                rgender= (RadioButton) findViewById(R.id.rfemale);
            }

            rgender.setChecked(true);

            String course = in.getStringExtra("course");
            String [] courses= course.split("AND");
            if(courses[0]!=null){
                android.setChecked(true);
            }

            if(courses[1]!=null){
                ios.setChecked(true);
            }
            submit.setText("Update");

        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = etname.getText().toString();
                String mobile = etmobile.getText().toString();
                String email = etemail.getText().toString();
                int selectid = rgGender.getCheckedRadioButtonId();
                rgender = (RadioButton) findViewById(selectid);
                String gender = rgender.getText().toString();
                String and = null;
                String chIos = null;
                and = android.getText().toString();
                chIos = ios.getText().toString();

                Student std = new Student();
                std.setSt_name(name);
                std.setSt_email(email);
                std.setSt_mobile(mobile);
                std.setSt_ios(chIos);
                std.setSt_android(and);
                std.setSt_gender(gender);

                if(validate()) {
                    handler = new DatabaseHandler(getApplicationContext());
                    long rowId = 0;
                    String message = "";
                    if (userID > 0) {
                        message = "Update";
                        std.setId(userID);
                        rowId = handler.UpdateStudentDetails(std);
                    } else {
                        message = "Registeration";
                        rowId = handler.regUser(std);
                    }
                    if (rowId > 0) {

                        Toast.makeText(Registration.this, message + " Sussess", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Registration.this, "Some Error occurred in " + message, Toast.LENGTH_SHORT).show();
                    }

                    Intent intent = new Intent(Registration.this, CounsellorActivity.class);
                    startActivity(intent);
                    finish();
                }

            }

        });

    }

    private  boolean validate()
    {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if(etname.getText().toString().equalsIgnoreCase(""))
        {
            etname.setError("Enter Name");
            return  false;
        }
        if(etname.getText().toString().length()<3)
        {
            etname.setError("Atleast 3 char");
            return  false;
        }
        if(etemail.getText().toString().equalsIgnoreCase(""))
        {
            etemail.setError("Blank");
            return false;
        }
        if(!etemail.getText().toString().matches(emailPattern))
        {
            etemail.setError("invalid email type");
            return false;
        }
        if(etmobile.getText().toString().equalsIgnoreCase(""))
        {
            etmobile.setError("Blank");
            return  false;
        }
        if(etmobile.getText().toString().length()<10)
        {
            etmobile.setError("10 digit No");
            return false;
        }

        if (!android.isChecked() && !ios.isChecked())
        {
            Toast.makeText(Registration.this,"Select Courses",Toast.LENGTH_SHORT).show();
            return false;

        }
        return true;
    }

}


