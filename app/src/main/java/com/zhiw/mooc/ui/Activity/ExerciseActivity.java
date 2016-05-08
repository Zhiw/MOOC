package com.zhiw.mooc.ui.Activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.zhiw.mooc.R;
import com.zhiw.mooc.adapter.ExerciseAdapter;
import com.zhiw.mooc.framework.base.BaseActivity;
import com.zhiw.mooc.model.Exercise;
import com.zhiw.mooc.presenter.ExercisePresenter;
import com.zhiw.mooc.ui.IView.ExerciseView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ExerciseActivity extends BaseActivity implements ExerciseView {
    public static final String EXTRA_ID = "objectId";
    public static final String EXTRA_TITLE = "title";

    @Bind(R.id.viewpager)
    ViewPager mViewpager;
    @Bind(R.id.progress_tv)
    TextView mProgressTv;

    private List<Exercise> mExerciseList;

    private ExercisePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        ButterKnife.bind(this);
        mPresenter = new ExercisePresenter(this, this);
        mPresenter.init();
        String objectId = getIntent().getStringExtra(EXTRA_ID);
        String title=getIntent().getStringExtra(EXTRA_TITLE);
        setTitle(title);
        mPresenter.getExerciseDetail(objectId);

//        tempData();

    }


    @Override
    public void initView(View view) {

    }

    @Override
    public void initListener() {
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mProgressTv.setText(String.format(Locale.getDefault(),"%d/%d", position+1,mExerciseList.size()));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void showLoading(boolean show) {
        showLoadingView(show);

    }


    @Override
    public void deployTopic(List<Exercise> list) {
        mExerciseList = new ArrayList<>();
        mExerciseList.addAll(list);
        mProgressTv.setText(String.format(Locale.getDefault(),"1/%d", mExerciseList.size()));
        ExerciseAdapter exerciseAdapter = new ExerciseAdapter(getSupportFragmentManager(), list);
        mViewpager.setAdapter(exerciseAdapter);

    }

    public List<Exercise> getExerciseList() {
        return mExerciseList;
    }

    private void tempData() {
//        List<Exercise> list=new ArrayList<>();
        mExerciseList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Exercise exercise = new Exercise();
            exercise.setType(Exercise.TYPE_CHOICE);
            exercise.setTopic("中国哪一年举办奥运会");
            List<String> choiceList = new ArrayList<>();
            choiceList.add("1990");
            choiceList.add("2000");
            choiceList.add("8798");
            choiceList.add("2008");
            exercise.setChoice(choiceList);
            mExerciseList.add(exercise);
        }

        Exercise exercise = new Exercise();
        exercise.setType(Exercise.TYPE_FILL);
        exercise.setTopic("北京举办了____年奥运会？");
        mExerciseList.add(exercise);

        ExerciseAdapter exerciseAdapter = new ExerciseAdapter(getSupportFragmentManager(), mExerciseList);
        mViewpager.setAdapter(exerciseAdapter);
    }


}
