package com.zhiw.mooc.ui.Activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.zhiw.mooc.R;
import com.zhiw.mooc.framework.base.BaseActivity;
import com.zhiw.mooc.framework.base.BaseRecyclerViewAdapter;
import com.zhiw.mooc.framework.base.BaseViewHolder;
import com.zhiw.mooc.model.Comment;
import com.zhiw.mooc.presenter.MainVideoPresenter;
import com.zhiw.mooc.ui.IView.MainVideoView;
import com.zhiw.mooc.ui.widgets.MyMediaController;
import com.zhiw.mooc.utils.FileUtil;
import com.zhiw.mooc.utils.LogTool;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.VideoView;

public class VideoActivity extends BaseActivity implements MainVideoView {

    public static final String EXTRA_URL = "url";
    public static final String EXTRA_TITLE = "title";
    public static final String EXTRA_ID = "id";

    @Bind(R.id.video_view)
    VideoView mVideoView;
    @Bind(R.id.comment_content_et)
    EditText mCommentContentEt;
    @Bind(R.id.comment_list)
    RecyclerView mCommentList;

    private MainVideoPresenter mPresenter;

    private String objectId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!LibsChecker.checkVitamioLibs(this)) {
            return;
        }
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);
        showLoadingView(true);

        mPresenter = new MainVideoPresenter(this, this);
        mPresenter.init();
        objectId = getIntent().getStringExtra(EXTRA_ID);
        mPresenter.getCommentList(objectId);


        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(VideoActivity.this, ScrollingActivity.class);
                startActivity(intent);
            }
        });*/
    }


    @Override
    public void initView(View view) {
        String defaultPath = FileUtil.getRootPath() + "/test.mov";
        LogTool.e(defaultPath);
//        String defaultPath = "http://www.modrails.com/videos/passenger_nginx.mov";
        String url = getIntent().hasExtra(EXTRA_URL) ? getIntent().getStringExtra(EXTRA_URL) : defaultPath;
        String title = getIntent().getStringExtra(EXTRA_TITLE);

        setTitle(title);
        mVideoView = (VideoView) findViewById(R.id.video_view);
        if (mVideoView != null) {
            mVideoView.setVideoPath(url);
        }
        final MyMediaController myMediaController = new MyMediaController(this);
        int windowHeight = getResources().getDisplayMetrics().heightPixels;

        myMediaController.setPadding(0, 0, 0, windowHeight - 594);

        mVideoView.setMediaController(myMediaController);
        mVideoView.requestFocus();
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                showLoadingView(false);
                mp.setPlaybackSpeed(1.0f);
            }
        });

        mCommentList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void initListener() {

    }

    @Override
    public void showLoading(boolean show) {
        showLoadingView(show);

    }


    @Override
    public void refreshCommentList(List<Comment> list) {
        BaseRecyclerViewAdapter<Comment> adapter=new BaseRecyclerViewAdapter<Comment>(list,this,R.layout.item_comment) {
            @Override
            public void onBindView(BaseViewHolder viewHolder, Comment item, int position) {
                viewHolder.setText(R.id.comment_user_name,item.getUser().getUsername())
                        .setText(R.id.comment_content,item.getContent())
                        .setText(R.id.comment_time,item.getUpdatedAt());

            }
        };
        mCommentList.setAdapter(adapter);

    }

    @Override
    public String getCommentContent() {
        return mCommentContentEt.getText().toString().trim();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mVideoView != null) {
            mVideoView.stopPlayback();
        }
    }

    @OnClick(R.id.submit_comment_btn)
    public void onClick() {
        mPresenter.submitComment(objectId);
    }
}
