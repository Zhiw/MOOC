package com.zhiw.mooc.framework.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: BaseRecyclerViewAdapter
 * Desc:
 * Created by zhiw on 16/5/5.
 */
public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    protected List<T> mList;
    protected Context mContext;
    protected final int mItemLayoutId;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(View view, Object data, int position);
    }

    public BaseRecyclerViewAdapter(List<T> list, Context context, int itemLayoutId) {
        if (list == null) {
            mList = new ArrayList<>();
        } else {
            mList = new ArrayList<>();
            mList.addAll(list);
        }
        mContext = context;
        mItemLayoutId = itemLayoutId;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(mItemLayoutId, parent, false));
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder,  int position) {
        final int pos=holder.getAdapterPosition();
        final T item = getItem(pos);
        if (mListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClick(v, item, pos);
                }
            });
        }
        onBindView(holder, item, position);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public abstract void onBindView(BaseViewHolder viewHolder, T item, int position);

    public void setListener(OnItemClickListener listener) {
        mListener = listener;
    }

    protected T getItem(int position) {
        return mList.get(position);
    }

    public void setData(List<T> data) {
        mList.clear();
        mList.addAll(data);
        notifyDataSetChanged();
    }

    public void addData(List<T> data) {
        mList.addAll(data);
        notifyDataSetChanged();
    }
}
