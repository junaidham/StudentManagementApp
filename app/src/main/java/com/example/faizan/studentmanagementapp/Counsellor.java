package com.example.faizan.studentmanagementapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Counsellor extends AppCompatActivity {
    Button btnreg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counsellor);
        btnreg =(Button) findViewById(R.id.reg);
        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(Counsellor.this,Registration.class);
                startActivity(in);
            }
        });
    }
}
