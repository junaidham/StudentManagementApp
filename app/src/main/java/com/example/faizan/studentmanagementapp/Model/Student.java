package com.example.faizan.studentmanagementapp.Model;

/**
 * Created by FAIZAN on 24-11-2016.
 */

public class Student
{
    private String St_name;
    private String St_email;
    private String St_mobile;
    private String St_gender;
    private String St_android;
    private String St_ios;
    private String St_status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private long id;

    public String getSt_name()
    {
        return St_name;
    }

    public void setSt_name(String st_name) {
        St_name = st_name;
    }

    public String getSt_email() {
        return St_email;
    }

    public void setSt_email(String st_email) {
        St_email = st_email;
    }

    public String getSt_mobile() {
        return St_mobile;
    }

    public void setSt_mobile(String st_mobile) {
        St_mobile = st_mobile;
    }

    public String getSt_gender() {
        return St_gender;
    }

    public void setSt_gender(String st_gender) {
        St_gender = st_gender;
    }

    public String getSt_ios() {
        return St_ios;
    }

    public void setSt_ios(String st_ios) {
        St_ios = st_ios;
    }

    public String getSt_android() {
        return St_android;
    }

    public void setSt_android(String st_android) {
        St_android = st_android;
    }

    public String getSt_status() {
        return St_status;
    }

    public void setSt_status(String st_status) {
        St_status = st_status;
    }




}
