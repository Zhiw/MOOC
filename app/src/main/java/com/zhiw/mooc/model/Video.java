package com.zhiw.mooc.model;

import cn.bmob.v3.BmobObject;

/**
 * ClassName: Video
 * Desc:
 * Created by zhiw on 16/3/26.
 */
public class Video extends BmobObject {
    private String title;
    private String teacher;
    private String school;
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
