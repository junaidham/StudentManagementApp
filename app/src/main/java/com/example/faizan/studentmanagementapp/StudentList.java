package com.example.faizan.studentmanagementapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.faizan.studentmanagementapp.Model.Student;
import com.example.faizan.studentmanagementapp.adapter.StudentAdapter;
import com.example.faizan.studentmanagementapp.db.DatabaseHandler;

import java.util.List;

public class StudentList extends AppCompatActivity
{

    List<Student> studentList;
    DatabaseHandler handler;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        handler =new DatabaseHandler(StudentList.this);

        lv = (ListView) findViewById(R.id.list);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Student std = studentList.get(position);
                Intent in = new Intent(StudentList.this,DetailActivity.class);
                in.putExtra("name",std.getSt_name());
                in.putExtra("email",std.getSt_email());
                in.putExtra("mobile",std.getSt_mobile());
                in.putExtra("gender",std.getSt_gender());
                in.putExtra("course",std.getSt_android()+" AND "+std.getSt_ios());
                in.putExtra("id",std.getId());

                startActivity(in);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        studentList=handler.getAllStudentData();
        StudentAdapter studentAdapter = new StudentAdapter(StudentList.this,studentList);
        lv.setAdapter(studentAdapter);
    }
}
