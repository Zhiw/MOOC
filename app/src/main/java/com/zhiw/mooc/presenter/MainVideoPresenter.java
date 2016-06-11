package com.zhiw.mooc.presenter;

import com.zhiw.mooc.framework.base.BasePresenter;
import com.zhiw.mooc.model.Comment;
import com.zhiw.mooc.model.Video;
import com.zhiw.mooc.ui.IView.MainVideoView;
import com.zhiw.mooc.utils.ToastUtil;

import android.app.Activity;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

/**
 * ClassName: MainVideoPresenter
 * Desc:
 * Created by zhiw on 16/5/5.
 */
public class MainVideoPresenter extends BasePresenter<MainVideoView> {
    public MainVideoPresenter(Activity activity, MainVideoView viewImpl) {
        super(activity, viewImpl);
    }

    public void getCommentList(String objectId) {
        BmobQuery<Comment> query = new BmobQuery<>();
        Video video = new Video();
        video.setObjectId(objectId);
        query.addWhereEqualTo("video", new BmobPointer(video));
        query.include("user,video");
        query.findObjects(activity, new FindListener<Comment>() {
            @Override
            public void onSuccess(List<Comment> list) {
                viewImpl.refreshCommentList(list);

            }

            @Override
            public void onError(int i, String s) {

            }
        });

    }

    public void submitComment(String objectId) {
        Comment comment = new Comment();
        Video video=new Video();
        video.setObjectId(objectId);
        BmobUser user=BmobUser.getCurrentUser(activity);
        String content = viewImpl.getCommentContent();

        comment.setContent(content);
        comment.setVideo(video);
        comment.setUser(user);

        comment.save(activity, new SaveListener() {
            @Override
            public void onSuccess() {
                ToastUtil.get().showShortToast(activity,"提交成功");
                viewImpl.SubmitSuccess();

            }

            @Override
            public void onFailure(int i, String s) {
                ToastUtil.get().showShortToast(activity,"提交失败");

            }
        });

    }
}
