package com.example.faizan.studentmanagementapp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.faizan.studentmanagementapp.db.DatabaseHandler;

public class DetailActivity extends AppCompatActivity
{
    //Declaration
    DatabaseHandler handler;
    TextView nametxt,emailtxt,mobiletxt,gendertxt,coursetxt;
    ImageView backBtn;
    Button deleteBtn, editBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        final Intent in = getIntent();

        //finding ID
        nametxt = (TextView) findViewById(R.id.name);
        emailtxt = (TextView) findViewById(R.id.email);
        mobiletxt = (TextView) findViewById(R.id.mobile);
        gendertxt = (TextView) findViewById(R.id.gender);
        coursetxt = (TextView) findViewById(R.id.course);
        backBtn = (ImageView) findViewById(R.id.back);
        deleteBtn = (Button) findViewById(R.id.delete);
        editBtn = (Button) findViewById(R.id.edit);

        //inseting data
        nametxt.setText(in.getStringExtra("name"));
        emailtxt.setText(in.getStringExtra("email"));
        mobiletxt.setText(in.getStringExtra("mobile"));
        gendertxt.setText(in.getStringExtra("gender"));
        coursetxt.setText(in.getStringExtra("course"));


        //Delete Button
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {   handler = new DatabaseHandler(getApplicationContext());
                long delId = in.getLongExtra("id",0);
                showConfirmation(delId);
                //Toast.makeText(DetailActivity.this,"Clicked on Delete",Toast.LENGTH_SHORT).show();
            }
        });
        //Edit Button
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                handler = new DatabaseHandler((getApplicationContext()));
                long editId = in.getLongExtra("id",0);
                                    //sending data to regtration page via Intent
                Intent intent = new Intent(DetailActivity.this,Registration.class);
                intent.putExtra("name",nametxt.getText().toString());
                intent.putExtra("email",emailtxt.getText().toString());
                intent.putExtra("mobile",mobiletxt.getText().toString());
                intent.putExtra("gender",gendertxt.getText().toString());
                intent.putExtra("course",coursetxt.getText().toString());
                intent.putExtra("id",editId);
                startActivity(intent);

                Toast.makeText(DetailActivity.this,"Clicked on Edit",Toast.LENGTH_SHORT).show();
            }
        });
        //Back Button
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                finish();
                Toast.makeText(DetailActivity.this,"Clicked on Edit",Toast.LENGTH_SHORT).show();
            }
        });

    }
    //PopUp delete dialogue
    public void showConfirmation(final long id)
    {
        new AlertDialog.Builder(this)
                .setTitle("Confirmation")
                .setMessage("Do you really want to Delete?")
                .setIcon(android.R.drawable.ic_dialog_alert)

                //On pressing Yes:-
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener()
                {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        handler.delStudentdetails(id);
                        Toast.makeText(DetailActivity.this, "Your Data is deleted..!!!", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                        finish();
                    }})
                //On pressing No:-
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i)
                    {
                        dialog.cancel();
                    }
                }).show();

    }
}
