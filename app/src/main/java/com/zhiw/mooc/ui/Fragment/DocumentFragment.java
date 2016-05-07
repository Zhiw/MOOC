package com.zhiw.mooc.ui.Fragment;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhiw.mooc.R;
import com.zhiw.mooc.adapter.DocumentRecyclerViewAdapter;
import com.zhiw.mooc.framework.base.BaseFragment;
import com.zhiw.mooc.model.Document;
import com.zhiw.mooc.presenter.DocumentPresenter;
import com.zhiw.mooc.ui.IView.DocumentView;
import com.zhiw.mooc.utils.FileUtil;
import com.zhiw.mooc.utils.ToastUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link DocumentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DocumentFragment extends BaseFragment implements DocumentView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public static final String TAG = "Document";
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private DocumentPresenter mPresenter;
    private DocumentRecyclerViewAdapter mAdapter;

    public DocumentFragment() {
        // Required empty public constructor
    }


    public static DocumentFragment newInstance(String param1, String param2) {
        DocumentFragment fragment = new DocumentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_docment, container, false);
        ButterKnife.bind(this, view);
        mPresenter = new DocumentPresenter(fragmentActivity, this);
        mPresenter.init(view);
        mPresenter.getData();
        return view;
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
    public void initView(View view) {
        mAdapter = new DocumentRecyclerViewAdapter();
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void initListener() {
        DocumentRecyclerViewAdapter.OnItemClickListener listener = new DocumentRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                String url = mAdapter.getDataFrom(position).getUrl();
                String temp = FileUtil.getRootPath()+"/test.pptx";
                Intent intent = FileUtil.getFileIntent(temp);
                ComponentName name = intent.resolveActivity(fragmentActivity.getPackageManager());
                if (name!=null){
                    startActivity(intent);
                }else {
                    ToastUtil.get().showShortToast(fragmentActivity,"No app to open this file");
                }

            }

            @Override
            public void onDownLoadClick(View view, int position) {
                // TODO: 16/4/10 download


            }
        };
        mAdapter.setListener(listener);

    }

    @Override
    public void showLoading(boolean show) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void refresh(List<Document> list) {
        mAdapter.addData(list);
    }
}
