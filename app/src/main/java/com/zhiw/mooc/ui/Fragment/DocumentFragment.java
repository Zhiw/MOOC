package com.zhiw.mooc.ui.Fragment;

import com.zhiw.mooc.R;
import com.zhiw.mooc.adapter.DocumentRecyclerViewAdapter;
import com.zhiw.mooc.framework.base.BaseFragment;
import com.zhiw.mooc.model.Document;
import com.zhiw.mooc.presenter.DocumentPresenter;
import com.zhiw.mooc.ui.IView.DocumentView;
import com.zhiw.mooc.utils.FileUtil;
import com.zhiw.mooc.utils.LogTool;
import com.zhiw.mooc.utils.ToastUtil;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.DownloadFileListener;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link DocumentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DocumentFragment extends BaseFragment implements DocumentView {


    public static final String TAG = "Document";
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;


    private DocumentPresenter mPresenter;
    private DocumentRecyclerViewAdapter mAdapter;

    public DocumentFragment() {
        // Required empty public constructor
    }


    public static DocumentFragment newInstance() {
        DocumentFragment fragment = new DocumentFragment();
        return fragment;
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
                String temp = FileUtil.getRootPath() + "/test.pptx";
                Intent intent = FileUtil.getFileIntent(temp);
                ComponentName name = intent.resolveActivity(fragmentActivity.getPackageManager());
                if (name != null) {
                    startActivity(intent);
                } else {
                    ToastUtil.get().showShortToast(fragmentActivity, "No app to open this file");
                }

            }

            @Override
            public void onDownLoadClick(View view, int position) {
                // TODO: 16/4/10 download
                Document document = mAdapter.getDataFrom(position);
                String url = document.getUrl();
                String name=url.substring(url.lastIndexOf("/")+1);
                LogTool.e(name);
                final File file=new File(Environment.getExternalStorageDirectory(),name);
                BmobFile bmobfile = new BmobFile(name, "", url);
                bmobfile.download(fragmentActivity, file,new DownloadFileListener() {
                    @Override
                    public void onSuccess(String s) {
                        Snackbar.make(mRecyclerView,"下载成功",Snackbar.LENGTH_LONG)
                                .setAction("查看", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent=new Intent(Intent.ACTION_VIEW);
                                        intent.setDataAndType(Uri.fromFile(file),"image/*");
                                        startActivity(intent);
                                    }
                                })
                                .show();
                    }

                    @Override
                    public void onFailure(int i, String s) {

                    }
                });


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
