package com.example.faizan.studentmanagementapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.faizan.studentmanagementapp.Model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FAIZAN on 20-11-2016.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    public static final String DB_NAME ="STD_DB";

    //table name constants
    public static final String KEY_STUDENT_TABLE ="STUDENT";

   // STudent table column names
    public static final String KEY_STUDENT_EMAIL ="email";
    public static final String KEY_STUDENT_ID ="id";
    public static final String KEY_STUDENT_MOBILE ="mobile";
    public static final String KEY_STUDENT_GENDER ="gender";
    public static final String KEY_STUDENT_NAME ="name";
    public static final String KEY_STUDENT_IOS ="IOS";
    public static final String KEY_STUDENT_ANDROID ="android";
    public static final String KEY_STUDENT_STATUS ="status";


    public static final int DB_version=1;

    public static final String STD_TABLE_QUERY="CREATE TABLE "+KEY_STUDENT_TABLE+" " + "( "
            +KEY_STUDENT_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            +KEY_STUDENT_NAME+" TEXT,"
            +KEY_STUDENT_EMAIL+" TEXT,"
            +KEY_STUDENT_MOBILE+" TEXT,"
            +KEY_STUDENT_GENDER+" TEXT,"
            +KEY_STUDENT_ANDROID+" TEXT,"
            +KEY_STUDENT_IOS+" TEXT,"
            +KEY_STUDENT_STATUS+" TEXT"
            +")";

    public static final String USER_TABLE_QUERY="CREATE TABLE USER ( id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,email TEXT,mobile TEXT,password TEXT, isAdmin INTEGER)";





    public DatabaseHandler(Context context)
    {
        super(context, DB_NAME, null, DB_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
      try {
          db.execSQL(STD_TABLE_QUERY);
          db.execSQL(USER_TABLE_QUERY);
      }catch(SQLException e){
          e.printStackTrace();
      }
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public long createUser(){

        SQLiteDatabase db =getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put("name","raj");
        values.put("email","r@gmail.com");
        values.put("password","123");
        values.put("mobile","797598385");
        values.put("isAdmin",0);

        long id = db.insert("USER",null,values);

        return id;

    }


    public String loginUser(String email) {
        SQLiteDatabase db = getReadableDatabase();

        String query= "SELECT * from USER WHERE email='"+email+"'";
        String pass="na";
         Cursor cursor= db.rawQuery(query,null);
        if(cursor.moveToFirst())
        {
           pass=  cursor.getString(cursor.getColumnIndex("password"));
        }

        return pass;

    }



    //
     public Long regUser(Student std)
    {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_STUDENT_NAME,std.getSt_name());
        values.put(KEY_STUDENT_EMAIL,std.getSt_email());
        values.put(KEY_STUDENT_MOBILE,std.getSt_mobile());
        values.put(KEY_STUDENT_GENDER,std.getSt_gender());
        values.put(KEY_STUDENT_ANDROID,std.getSt_android());
        values.put(KEY_STUDENT_IOS,std.getSt_ios());
         long id = db.insert("STUDENT",null,values);
        return id ;
    }




    //
    public List<Student> getAllStudentData()
    {
        SQLiteDatabase db = getWritableDatabase();
        String query ="SELECT * FROM "+KEY_STUDENT_TABLE;
        List<Student> studentList = new ArrayList<>();
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst())
        {
            do{
                Student student = new Student();

                student.setId(cursor.getLong(cursor.getColumnIndex("id")));
                student.setSt_name(cursor.getString(cursor.getColumnIndex("name")));
                student.setSt_email(cursor.getString(cursor.getColumnIndex("email")));
                student.setSt_mobile(cursor.getString(cursor.getColumnIndex("mobile")));
                student.setSt_gender(cursor.getString(cursor.getColumnIndex("gender")));
                student.setSt_android(cursor.getString(cursor.getColumnIndex("android")));
                student.setSt_ios(cursor.getString(cursor.getColumnIndex("IOS")));
                student.setSt_status(cursor.getString(cursor.getColumnIndex("status")));
                studentList.add(student);
            }while(cursor.moveToNext());
        }
           return studentList;

    }


    //
    public void delStudentdetails(long delId)
    {
        SQLiteDatabase db = getReadableDatabase();
        db.delete(KEY_STUDENT_TABLE,KEY_STUDENT_ID +"="+delId,null);
    }



    //
    public long UpdateStudentDetails(Student std)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",std.getSt_name());
        values.put("mobile",std.getSt_mobile());
        values.put("email",std.getSt_email());
        values.put("gender",std.getSt_gender());
        values.put("android",std.getSt_android());
        values.put("IOS",std.getSt_ios());
        long id=db.update(KEY_STUDENT_TABLE,values,KEY_STUDENT_ID+"=?",new String[]{String.valueOf(std.getId())});
        return id;
    }

}
