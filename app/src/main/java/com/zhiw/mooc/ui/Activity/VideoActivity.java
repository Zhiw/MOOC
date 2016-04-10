package com.zhiw.mooc.ui.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.zhiw.mooc.R;
import com.zhiw.mooc.framework.base.BaseActivity;
import com.zhiw.mooc.ui.widgets.MyMediaController;
import com.zhiw.mooc.utils.FileUtil;
import com.zhiw.mooc.utils.LogTool;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.VideoView;

public class VideoActivity extends BaseActivity {

    @Bind(R.id.video_view)
    VideoView mVideoView;

    public static final String EXTRA_URL = "url";
    public static final String EXTRA_TITLE = "title";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!LibsChecker.checkVitamioLibs(this)) {
            return;
        }
        setContentView(R.layout.activity_video);
        ButterKnife.bind(this);
        String defaultPath = FileUtil.getRootPath() + "/test.mov";
        LogTool.e(defaultPath);
//        String defaultPath = "http://www.modrails.com/videos/passenger_nginx.mov";
        String url = getIntent().hasExtra(EXTRA_URL) ? getIntent().getStringExtra(EXTRA_URL) : defaultPath;
        String title = getIntent().getStringExtra(EXTRA_TITLE);
        setTitle(title);

//        showLoadingView(true);

        mVideoView = (VideoView) findViewById(R.id.video_view);
        mVideoView.setVideoPath(url);
        final MyMediaController myMediaController = new MyMediaController(this);
        int windowHeight = getResources().getDisplayMetrics().heightPixels;

        myMediaController.setPadding(0,0,0,windowHeight - 594);

        mVideoView.setMediaController(myMediaController);
        mVideoView.requestFocus();
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setPlaybackSpeed(1.0f);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(VideoActivity.this, ScrollingActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mVideoView!=null){
            mVideoView.stopPlayback();
        }
    }
}
