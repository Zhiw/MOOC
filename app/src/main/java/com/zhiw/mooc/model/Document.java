package com.zhiw.mooc.model;

import cn.bmob.v3.BmobObject;

/**
 * ClassName: Document
 * Desc:
 * Created by zhiw on 16/4/7.
 */
public class Document extends BmobObject {

    private String subject;

    private String title;

    private String school;

    private String teacher;

    private String url;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
