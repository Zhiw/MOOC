package com.zhiw.mooc.model;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;

/**
 * ClassName: Comment
 * Desc:
 * Created by zhiw on 16/5/5.
 */
public class Comment extends BmobObject {

    private BmobUser user;

    private Video video;

    private String content;

    public BmobUser getUser() {
        return user;
    }

    public void setUser(BmobUser user) {
        this.user = user;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
