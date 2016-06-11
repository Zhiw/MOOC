package com.zhiw.mooc.ui.IView;

import com.zhiw.mooc.framework.base.BaseView;
import com.zhiw.mooc.model.Comment;

import java.util.List;

/**
 * ClassName: MainVideoView
 * Desc:
 * Created by zhiw on 16/5/5.
 */
public interface MainVideoView extends BaseView{

    void refreshCommentList(List<Comment> list);

    String getCommentContent();

    void SubmitSuccess();

}
