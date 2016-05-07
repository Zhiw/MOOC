package com.zhiw.mooc.model;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;

/**
 * ClassName: Attendance
 * Desc:
 * Created by zhiw on 16/4/29.
 */
public class Attendance extends BmobObject {
    private BmobUser student;
    private double latitude;
    private double longitude;
    private String address;
    private String description;

    public Attendance(BmobUser student, double latitude, double longitude, String address, String description) {
        this.student = student;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.description = description;
    }


    public BmobUser getStudent() {
        return student;
    }

    public void setStudent(BmobUser student) {
        this.student = student;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
