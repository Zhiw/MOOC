package com.zhiw.mooc.ui.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhiw.mooc.R;
import com.zhiw.mooc.adapter.TestRecyclerViewAdapter;
import com.zhiw.mooc.framework.base.BaseFragment;
import com.zhiw.mooc.model.Test;
import com.zhiw.mooc.presenter.TestPresenter;
import com.zhiw.mooc.ui.IView.ITestView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link TestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TestFragment extends BaseFragment implements ITestView {

    public static final String TAG = "Test";
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private TestPresenter mPresenter;
    private TestRecyclerViewAdapter mAdapter;


    public TestFragment() {
        // Required empty public constructor
    }


    public static TestFragment newInstance() {
        return new TestFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_docment, container, false);
        ButterKnife.bind(this, view);
        mPresenter = new TestPresenter(fragmentActivity, this);
        mPresenter.init(view);
        mPresenter.getData();
        return view;
    }

    @Override
    public void refresh(List<Test> data) {
        mAdapter.addData(data);


    }

    @Override
    public void initView(View view) {
        mAdapter = new TestRecyclerViewAdapter();
        mRecyclerView.setAdapter(mAdapter);


    }

    @Override
    public void initListener() {
        TestRecyclerViewAdapter.OnItemClickListener listener = new TestRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        };

        mAdapter.setListener(listener);

    }

    @Override
    public void showLoading(boolean show) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
