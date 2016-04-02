package com.zhiw.mooc.ui.IView;

import com.zhiw.mooc.framework.base.BaseView;
import com.zhiw.mooc.model.Video;

import java.util.List;

/**
 * ClassName: IVideoView
 * Desc:
 * Created by zhiw on 16/3/30.
 */
public interface IVideoView extends BaseView {

    /**
     *
     * @param video
     */
    void refreshUI(List<Video> video);

}
