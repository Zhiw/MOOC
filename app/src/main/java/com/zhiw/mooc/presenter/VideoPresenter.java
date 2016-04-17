package com.zhiw.mooc.presenter;

import android.app.Activity;

import com.zhiw.mooc.framework.base.BasePresenter;
import com.zhiw.mooc.model.Video;
import com.zhiw.mooc.ui.IView.IVideoView;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * ClassName: VideoPresenter
 * Desc:
 * Created by zhiw on 16/3/30.
 */
public class VideoPresenter extends BasePresenter<IVideoView> {
    public VideoPresenter(Activity activity, IVideoView viewImpl) {
        super(activity, viewImpl);
    }

    public void getData(){
        BmobQuery<Video> query = new BmobQuery<>();
        query.findObjects(activity, new FindListener<Video>() {
            @Override
            public void onSuccess(List<Video> list) {
                viewImpl.refreshUI(list);
            }

            @Override
            public void onError(int i, String s) {

            }
        });

    }
}
