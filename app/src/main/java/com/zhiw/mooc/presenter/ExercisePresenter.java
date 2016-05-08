package com.zhiw.mooc.presenter;

import android.app.Activity;

import com.zhiw.mooc.framework.base.BasePresenter;
import com.zhiw.mooc.model.Exercise;
import com.zhiw.mooc.model.Test;
import com.zhiw.mooc.ui.IView.ExerciseView;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.listener.FindListener;

/**
 * ClassName: ExercisePresenter
 * Desc:
 * Created by zhiw on 16/5/8.
 */
public class ExercisePresenter extends BasePresenter<ExerciseView> {
    public ExercisePresenter(Activity activity, ExerciseView viewImpl) {
        super(activity, viewImpl);
    }

    public void getExerciseDetail(String objectId){
        viewImpl.showLoading(true);
        BmobQuery<Exercise> query=new BmobQuery<>();
        Test test=new Test();
        test.setObjectId(objectId);
        query.addWhereEqualTo("test",new BmobPointer(test));

        query.findObjects(activity, new FindListener<Exercise>() {
            @Override
            public void onSuccess(List<Exercise> list) {
                viewImpl.showLoading(false);
                viewImpl.deployTopic(list);
            }

            @Override
            public void onError(int i, String s) {
                viewImpl.showLoading(false);

            }
        });


    }
}
