package com.zhiw.mooc.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.zhiw.mooc.R;
import com.zhiw.mooc.model.Document;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * ClassName: DocumentRecyclerViewAdapter
 * Desc:
 * Created by zhiw on 16/4/10.
 */
public class DocumentRecyclerViewAdapter extends RecyclerView.Adapter<DocumentRecyclerViewAdapter.ViewHolder> {

    private List<Document> mList = new ArrayList<>();
    private OnItemClickListener mListener;

    public void setListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_document, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Document document = mList.get(position);
        holder.mTitle.setText(document.getTitle());
        holder.mTeacher.setText(document.getTeacher());
        holder.mSchool.setText(document.getSchool());
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getLayoutPosition();
                if (mListener!=null){
                    mListener.onItemClick(view,position);
                }
            }
        });

        holder.mDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getLayoutPosition();
                if (mListener!=null){
                    mListener.onDownLoadClick(view,position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addData(List<Document> data) {
        mList.addAll(data);
        notifyDataSetChanged();

    }

    public Document getDataFrom(int position){
        return mList.get(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.title)
        TextView mTitle;
        @Bind(R.id.download)
        ImageButton mDownload;
        @Bind(R.id.teacher)
        TextView mTeacher;
        @Bind(R.id.school)
        TextView mSchool;
        @Bind(R.id.card_view)
        CardView mCardView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onDownLoadClick(View view,int position);
    }
}
