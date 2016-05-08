package com.zhiw.mooc.ui.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.zhiw.mooc.R;
import com.zhiw.mooc.framework.base.BaseFragment;
import com.zhiw.mooc.model.Exercise;
import com.zhiw.mooc.ui.Activity.ExerciseActivity;

import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ChoiceFragment extends BaseFragment {

    private static final String ARG_PARAM = "position";
    @Bind(R.id.exercise_topic)
    TextView mExerciseTopic;
    @Bind(R.id.choice_a)
    RadioButton mChoiceA;
    @Bind(R.id.choice_b)
    RadioButton mChoiceB;
    @Bind(R.id.choice_c)
    RadioButton mChoiceC;
    @Bind(R.id.choice_d)
    RadioButton mChoiceD;


    private static final String[] PREFIX = new String[]{"A", "B", "C", "D"};
    @Bind(R.id.see_right_answer_btn)
    Button mSeeRightAnswerBtn;
    @Bind(R.id.right_answer_tv)
    TextView mRightAnswerTv;

    private int mPosition;

    public ChoiceFragment() {
        // Required empty public constructor
    }


    public static ChoiceFragment newInstance(int position) {
        ChoiceFragment fragment = new ChoiceFragment();
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
        View view = inflater.inflate(R.layout.fragment_choice, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void initView() {

        Exercise exercise = ((ExerciseActivity) fragmentActivity).getExerciseList().get(mPosition);
        mExerciseTopic.setText(String.format(Locale.getDefault(),"%d.%s", mPosition+1, exercise.getTopic()));
        mChoiceA.setText(String.format("%s.%s", PREFIX[0], exercise.getChoice().get(0)));
        mChoiceB.setText(String.format("%s.%s", PREFIX[1], exercise.getChoice().get(1)));
        mChoiceC.setText(String.format("%s.%s", PREFIX[2], exercise.getChoice().get(2)));
        mChoiceD.setText(String.format("%s.%s", PREFIX[3], exercise.getChoice().get(3)));

        mRightAnswerTv.setText(exercise.getRightAnswer());

    }

    @OnClick(R.id.see_right_answer_btn)
    public void onClick() {
        mRightAnswerTv.setVisibility(View.VISIBLE);
    }
}
