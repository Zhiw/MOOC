package com.zhiw.mooc.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhiw.mooc.R;
import com.zhiw.mooc.model.Video;

import java.util.List;


public class MyVideoRecyclerViewAdapter extends RecyclerView.Adapter<MyVideoRecyclerViewAdapter.ViewHolder> {

    private final List<Video> mList;

    private OnItemClickListener mOnItemClickListener;

    public MyVideoRecyclerViewAdapter(List<Video> items) {
        mList = items;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Video video = mList.get(position);
        holder.mTitleView.setText(video.getTitle());
        holder.mContentView.setText(video.getTeacher());
        holder.mSchoolView.setText(video.getSchool());
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getLayoutPosition();
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(view, position);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }



    public void addData(List<Video> data){
        mList.addAll(data);
        notifyDataSetChanged();

    }

    public void insertData(int position){
        Video video = new Video();
        video.setTitle("Google");
        mList.add(position,video);
        notifyItemInserted(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTitleView;
        public final TextView mContentView;
        public final TextView mSchoolView;
        public final CardView mCardView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mCardView = (CardView)view.findViewById(R.id.card_view);
            mTitleView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
            mSchoolView = (TextView) view.findViewById(R.id.school);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
