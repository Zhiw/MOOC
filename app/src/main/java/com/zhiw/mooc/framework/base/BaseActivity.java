package com.zhiw.mooc.framework.base;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.zhiw.mooc.R;

public class BaseActivity extends AppCompatActivity {

    private ViewGroup mRootView;
    private View mLoadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (!SPHelper.getTheme(this)){
//            setTheme(R.style.AppTheme);
//            LogTool.e("default");
//        }else {
//            LogTool.e("other");
//            setTheme(R.style.AppThemeOther);
//        }
    }

    @Override
    public void setContentView(int layoutResID) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.setContentView(layoutResID);
        mRootView = (ViewGroup)findViewById(android.R.id.content);

        View v = findViewById(R.id.toolbar);
        if (v!=null){
            Toolbar toolbar = (Toolbar) v;
            toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white);
            setSupportActionBar(toolbar);
            getSupportActionBar().setHomeButtonEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }
    }

    public void showLoadingView(boolean show){
        if (mLoadingView == null ){
            mLoadingView = getLayoutInflater().inflate(R.layout.loading_view,mRootView,false);
        }
        if (show){
            mRootView.addView(mLoadingView);
        }else {
            mRootView.removeView(mLoadingView);
        }
    }

    public void startActivity(Class<?> aClass){
        startActivity(new Intent(this,aClass));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLoadingView != null) {
            mRootView.removeView(mLoadingView);
        }
    }

}
