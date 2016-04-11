package com.zhiw.mooc.ui.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhiw.mooc.R;
import com.zhiw.mooc.adapter.MyVideoRecyclerViewAdapter;
import com.zhiw.mooc.framework.base.BaseFragment;
import com.zhiw.mooc.model.Video;
import com.zhiw.mooc.presenter.VideoPresenter;
import com.zhiw.mooc.ui.Activity.VideoActivity;
import com.zhiw.mooc.ui.IView.IVideoView;
import com.zhiw.mooc.ui.widgets.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A fragment representing a list of Items.
 * <p/>
 * interface.
 */
public class VideoFragment extends BaseFragment implements IVideoView{

    public static final String TAG = "Video";
    @Bind(R.id.list)
    RecyclerView mRecyclerView;
    @Bind(R.id.swipe)
    SwipeRefreshLayout mSwipe;

    private MyVideoRecyclerViewAdapter mAdapter;
    private VideoPresenter mPresenter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public VideoFragment() {
    }

    // TODO: Customize parameter initialization
    public static VideoFragment newInstance() {
        VideoFragment fragment = new VideoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video_list, container, false);
        ButterKnife.bind(this, view);

        mPresenter = new VideoPresenter(fragmentActivity,this);
        mPresenter.init(view);
        mPresenter.getData();

        return view;
    }

    @Override
    public void refreshUI(List<Video> data) {

        mAdapter.addData(data);
    }

    @Override
    public void initView(View view) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(fragmentActivity));
        List<Video> list = new ArrayList<>();
        for (int i = 'A'; i < 'z'; i++) {
            Video video = new Video();
            video.setTitle("" + (char) i);
            list.add(video);
        }
        mAdapter = new MyVideoRecyclerViewAdapter(list);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(4));

        mSwipe.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Video video = new Video();
                        video.setTitle("Google");
                        mAdapter.insertData(6);
                        mSwipe.setRefreshing(false);
                    }
                }, 3000);


            }
        });
    }

    @Override
    public void initListener() {
        MyVideoRecyclerViewAdapter.OnItemClickListener listener = new MyVideoRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(fragmentActivity, VideoActivity.class);
//                intent.putExtra(VideoActivity.EXTRA_URL,mAdapter.getDataFrom(position).getUrl());
//                intent.putExtra(VideoActivity.EXTRA_TITLE,mAdapter.getDataFrom(position).getTitle());
                startActivity(intent);
            }
        };

        mAdapter.setOnItemClickListener(listener);

    }

    @Override
    public void showLoading(boolean show) {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


}
