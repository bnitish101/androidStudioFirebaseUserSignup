package com.example.androidstudiofirebaseusersignup;

public class dataHolder {
    String name, course, mobile, pImage;

    public dataHolder(String name, String course, String mobile, String pImage){
        this.name = name;
        this.course = course;
        this.mobile = mobile;
        this.pImage = pImage;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public void setCourse(String course) {
        this.course = course;
    }
    public String getCourse() {
        return course;
    }

    public void setMobile(String mobile){
        this.mobile = mobile;
    }
    public String getMobile(){
        return mobile;
    }

    public void setpImage(String pImage){
        this.pImage = pImage;
    }
    public String getpImage(){
        return pImage;
    }
}
