package com.example.faizan.studentmanagementapp.adapter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.faizan.studentmanagementapp.Model.Student;
import com.example.faizan.studentmanagementapp.R;

import java.util.List;

/**
 * Created by FAIZAN on 24-11-2016.
 */

public class StudentAdapter extends BaseAdapter {
    Context context;
    List<Student> studentList;

    public StudentAdapter(Context context, List<Student> studentList) {
        this.context = context;
        this.studentList = studentList;
    }

    @Override
    public int getCount() {
        return studentList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    /* Create a holder Class to contain inflated xml file elements */
    public class ViewHolder {
        TextView name;
        TextView mobile;
        TextView email;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.rowstudent, null);
            holder = new ViewHolder();
            holder.name = (TextView) view.findViewById(R.id.name);
            holder.email = (TextView) view.findViewById(R.id.email);
            holder.mobile = (TextView) view.findViewById(R.id.mobile);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.name.setText(studentList.get(position).getSt_name());
        holder.email.setText(studentList.get(position).getSt_email());
        holder.mobile.setText(studentList.get(position).getSt_mobile());

        // Method for sending email via onclick listview item
        holder.email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

                emailIntent.setType("plain/text");

                emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,
                        new String[]{studentList.get(position).getSt_email()});

                emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
                        "Email Subject");

                emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,
                        "Email Body");

                context.startActivity(Intent.createChooser(
                        emailIntent, "Send mail..."));


            }
        });
        // Method for making call via onclick listview item
       // holder.mobile.setOnClickListener(new View.OnClickListener() {
         //   @Override
           // public void onClick(View view) {
             //   final Intent callIntent = new Intent(Intent.ACTION_CALL);
               // callIntent.setData(Uri.parse("Phone no:9871694384"));
                //context.startActivity(callIntent);

           // }
        //});
        return view;

     }
}
