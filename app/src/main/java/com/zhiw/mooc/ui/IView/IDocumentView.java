package com.zhiw.mooc.ui.IView;

import com.zhiw.mooc.framework.base.BaseView;
import com.zhiw.mooc.model.Document;

import java.util.List;

/**
 * ClassName: IDocumentView
 * Desc:
 * Created by zhiw on 16/4/10.
 */
public interface IDocumentView extends BaseView {

    void refresh(List<Document> list);

}
