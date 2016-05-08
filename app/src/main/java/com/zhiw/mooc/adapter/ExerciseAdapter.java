package com.zhiw.mooc.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zhiw.mooc.model.Exercise;
import com.zhiw.mooc.ui.Fragment.ChoiceFragment;
import com.zhiw.mooc.ui.Fragment.FillFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: ExerciseAdapter
 * Desc:
 * Created by zhiw on 16/5/8.
 */
public class ExerciseAdapter extends FragmentPagerAdapter {

    private List<Exercise> mList;

    public ExerciseAdapter(FragmentManager fm, List<Exercise> list) {
        super(fm);
        if (list == null) {
            mList = new ArrayList<>();
        } else {
            mList = list;
        }
    }

    @Override
    public Fragment getItem(int position) {
        String type = mList.get(position).getType();
        if (Exercise.TYPE_FILL.equals(type)) {
            return FillFragment.newInstance(position);
        } else
            return ChoiceFragment.newInstance(position);

    }

    @Override
    public int getCount() {
        return mList.size();
    }
}
