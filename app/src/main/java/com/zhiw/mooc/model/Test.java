package com.zhiw.mooc.model;

import cn.bmob.v3.BmobObject;

/**
 * ClassName: Test
 * Desc:
 * Created by zhiw on 16/4/17.
 */
public class Test extends BmobObject{
    private String subject;
    private String title;

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
}
