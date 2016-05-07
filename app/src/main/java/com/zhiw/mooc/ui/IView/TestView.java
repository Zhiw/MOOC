package com.zhiw.mooc.ui.IView;

import com.zhiw.mooc.framework.base.BaseView;
import com.zhiw.mooc.model.Test;

import java.util.List;

/**
 * ClassName: TestView
 * Desc:
 * Created by zhiw on 16/4/18.
 */
public interface TestView extends BaseView {
    void refresh(List<Test> data);
}
