package com.zhiw.mooc.presenter;

import android.app.Activity;

import com.zhiw.mooc.framework.base.BasePresenter;
import com.zhiw.mooc.model.Document;
import com.zhiw.mooc.ui.IView.IDocumentView;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * ClassName: DocumentPresenter
 * Desc:
 * Created by zhiw on 16/4/10.
 */
public class DocumentPresenter extends BasePresenter<IDocumentView> {
    public DocumentPresenter(Activity activity, IDocumentView viewImpl) {
        super(activity, viewImpl);
    }

    public void getData(){
        BmobQuery<Document> query = new BmobQuery<>();
        query.findObjects(activity, new FindListener<Document>() {
            @Override
            public void onSuccess(List<Document> list) {
                viewImpl.refresh(list);

            }

            @Override
            public void onError(int i, String s) {

            }
        });
    }
}
