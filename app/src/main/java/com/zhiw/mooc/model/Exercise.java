package com.zhiw.mooc.model;

import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * ClassName: Exercise
 * Desc:
 * Created by zhiw on 16/5/8.
 */
public class Exercise extends BmobObject {

    public static final String TYPE_FILL = "FILL";
    public static final String TYPE_CHOICE = "CHOICE";

    private Video video;

    /**
     * 题目
     */
    private String topic;
    /**
     * 类型
     */
    private String type;

    private List<String> choice;


    private String rightAnswer;

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public List<String> getChoice() {
        return choice;
    }

    public void setChoice(List<String> choice) {
        this.choice = choice;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }
}
