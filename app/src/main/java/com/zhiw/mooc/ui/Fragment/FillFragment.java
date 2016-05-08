package com.zhiw.mooc.ui.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.zhiw.mooc.R;
import com.zhiw.mooc.framework.base.BaseFragment;
import com.zhiw.mooc.model.Exercise;
import com.zhiw.mooc.ui.Activity.ExerciseActivity;

import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FillFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FillFragment extends BaseFragment {

    private static final String ARG_PARAM = "position";
    @Bind(R.id.exercise_topic)
    TextView mExerciseTopic;
    @Bind(R.id.answer_et)
    EditText mAnswerEt;

    private int mPosition;


    public FillFragment() {
    }


    public static FillFragment newInstance(int position) {
        FillFragment fragment = new FillFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPosition = getArguments().getInt(ARG_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_fill, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        Exercise exercise = ((ExerciseActivity) fragmentActivity).getExerciseList().get(mPosition);
        mExerciseTopic.setText(String.format(Locale.getDefault(), "%d.%s", mPosition + 1, exercise.getTopic()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
