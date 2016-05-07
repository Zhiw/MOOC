package com.zhiw.mooc.framework.base;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

/**
 * ClassName: BaseViewHolder
 * Desc:
 * Created by zhiw on 16/5/5.
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {
    protected final SparseArray<View> mViews;
    protected View mConvertView;

    public BaseViewHolder(View itemView) {
        super(itemView);
        mViews = new SparseArray<>();
        mConvertView = itemView;
    }

    /**
     * 通过资源Id获取对应控件，如果没有，则加入views
     * @param viewId 资源Id
     * @param <T> view
     * @return
     */
    public <T extends View> T getView(int viewId){
        View view = mViews.get(viewId);
        if (view == null){
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId,view);
        }
        return (T)view;
    }

    /**
     * 实现通用的属性设置方法
     * @param viewId 控件Id
     * @param value string value
     * @return
     */
    public BaseViewHolder setText(int viewId,String value){
        TextView view = getView(viewId);
        view.setText(value);
        return BaseViewHolder.this;
    }
}
