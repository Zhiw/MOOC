package com.zhiw.mooc.ui.Activity;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zhiw.mooc.R;
import com.zhiw.mooc.adapter.ViewPagerAdapter;
import com.zhiw.mooc.framework.base.BaseActivity;
import com.zhiw.mooc.ui.Fragment.DocumentFragment;
import com.zhiw.mooc.ui.Fragment.LogoutDialogFragment;
import com.zhiw.mooc.ui.Fragment.TestFragment;
import com.zhiw.mooc.ui.Fragment.VideoFragment;
import com.zhiw.mooc.utils.ToastUtil;

import android.app.DialogFragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, LogoutDialogFragment.LogoutDialogListener {

    @Bind(R.id.tab_layout)
    TabLayout mTabLayout;
    @Bind(R.id.viewpager)
    ViewPager mViewpager;
    @Bind(R.id.nav_view)
    NavigationView mNavView;
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.fab)
    FloatingActionButton mFab;

    private TextView userNameView;
    private SimpleDraweeView avatarView;
    private ImageView exitView;

    private static final int LOGIN = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //init bmob
        Bmob.initialize(this, getString(R.string.bmob_app_id));
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mFab.performClick();
            }
        }, 5000);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        mNavView.setNavigationItemSelectedListener(this);
        initTabLayout();

        View view = mNavView.getHeaderView(0);
        avatarView = (SimpleDraweeView) view.findViewById(R.id.imageView);
        userNameView = (TextView) view.findViewById(R.id.user_name_tv);
        exitView = (ImageView) view.findViewById(R.id.exit);
        avatarView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivityForResult(intent, LOGIN);
            }
        });

        exitView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogoutDialogFragment fragment = new LogoutDialogFragment();
                fragment.show(getFragmentManager(), "LogOutFragment");

            }
        });

        checkLogin();


    }

    private void checkLogin() {
        BmobUser user = BmobUser.getCurrentUser(this);
        if (user != null) {
            avatarView.setClickable(false);
            avatarView.setImageURI(Uri.parse("res://mipmap/" + R.mipmap.circle_avatar));
            userNameView.setText(user.getUsername());
            exitView.setVisibility(View.VISIBLE);
        } else {
            userNameView.setText("Click avatar to login");
            avatarView.setClickable(true);
            avatarView.setImageURI(Uri.parse("res://mipmap/" + R.mipmap.ic_account_circle_white_48dp));
            exitView.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == LOGIN) {
                checkLogin();
            }
        }
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        BmobUser.logOut(MainActivity.this);
        checkLogin();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            startActivity(AboutActivity.class);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            startActivity(SignInActivity.class);
        } else {
            ToastUtil.get().showShortToast(MainActivity.this,"功能待开发");
        }
//        else if (id == R.id.nav_gallery) {
//            boolean isDefault = !SPHelper.getTheme(this);
//            SPHelper.setTheme(this, isDefault);
//            finish();
//            final Intent intent = getIntent();
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(intent);
//            overridePendingTransition(0, 0);

//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }else if (id==R.id.action_settings){
//        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    private void initTabLayout() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        VideoFragment videoFragment = VideoFragment.newInstance();
        DocumentFragment documentFragment = DocumentFragment.newInstance();
        TestFragment testFragment = TestFragment.newInstance();

        adapter.addFragment(videoFragment, VideoFragment.TAG);
        adapter.addFragment(documentFragment, DocumentFragment.TAG);
        adapter.addFragment(testFragment, TestFragment.TAG);

        mViewpager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewpager);
    }

}
