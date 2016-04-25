package com.zhiw.mooc.presenter;

import android.app.Activity;

import com.zhiw.mooc.framework.base.BasePresenter;
import com.zhiw.mooc.model.Test;
import com.zhiw.mooc.ui.IView.ITestView;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * ClassName: TestPresenter
 * Desc:
 * Created by zhiw on 16/4/18.
 */
public class TestPresenter extends BasePresenter<ITestView> {

    public TestPresenter(Activity activity, ITestView viewImpl) {
        super(activity, viewImpl);
    }

    public void getData(){
        BmobQuery<Test> query = new BmobQuery<>();
        query.findObjects(activity, new FindListener<Test>() {
            @Override
            public void onSuccess(List<Test> list) {
                viewImpl.refresh(list);
            }

            @Override
            public void onError(int i, String s) {

            }
        });
    }
}
