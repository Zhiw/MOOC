package com.zhiw.mooc.ui.IView;

import com.zhiw.mooc.framework.base.BaseView;
import com.zhiw.mooc.model.Exercise;

import java.util.List;

/**
 * ClassName: ExerciseView
 * Desc:
 * Created by zhiw on 16/5/8.
 */
public interface ExerciseView extends BaseView {

    void deployTopic(List<Exercise> list);



}
