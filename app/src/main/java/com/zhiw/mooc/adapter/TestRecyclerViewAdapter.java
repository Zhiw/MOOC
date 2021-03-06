package com.zhiw.mooc.adapter;

import com.zhiw.mooc.R;
import com.zhiw.mooc.model.Test;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * ClassName: TestRecyclerViewAdapter
 * Desc:
 * Created by zhiw on 16/4/17.
 */
public class TestRecyclerViewAdapter extends RecyclerView.Adapter<TestRecyclerViewAdapter.ViewHolder> {


    private List<Test> mList = new ArrayList<>();
    private OnItemClickListener mListener;

    public void setListener(OnItemClickListener listener) {
        mListener = listener;
    }


    public void addData(List<Test> data) {
        mList.addAll(data);
        notifyDataSetChanged();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_test, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final int pos=holder.getAdapterPosition();
        Test test = mList.get(pos);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener!=null){
                    mListener.onItemClick(view,pos);
                }
            }
        });

        holder.mSubject.setText(test.getSubject());
        holder.mTitle.setText(test.getTitle());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public Test getItem(int position){
        return mList.get(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.subject)
        TextView mSubject;
        @Bind(R.id.title)
        TextView mTitle;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

    }
}
