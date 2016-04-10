package com.zhiw.mooc.ui.Activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thefinestartist.finestwebview.FinestWebView;
import com.zhiw.mooc.R;
import com.zhiw.mooc.framework.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AboutActivity extends BaseActivity {

    @Bind(R.id.about_recyclerview)
    RecyclerView mAboutRecyclerview;
    @Bind(R.id.repository)
    TextView mRepository;
    @Bind(R.id.github)
    TextView mGithub;

    private ArrayMap<String, String> mLibsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mLibsList = new ArrayMap<>();
        mLibsList.put("CardView", "http://developer.android.com/reference/android/support/v7/widget/CardView.html");
        mLibsList.put("Bmob", "http://www.bmob.cn");
        mLibsList.put("Gson", "https://github.com/google/gson");
        mLibsList.put("vitamio", "https://www.vitamio.org/en/");
        mLibsList.put("RecyclerView", "http://developer.android.com/reference/android/support/v7/widget/RecyclerView.html");
        mLibsList.put("Material", "https://www.google.com/design/spec/material-design/introduction.html");
        mLibsList.put("Facebook/Fresco","https://github.com/facebook/fresco");
        mLibsList.put("TriangleLabelView", "https://github.com/shts/TriangleLabelView");
        mLibsList.put("FinestWebView", "https://github.com/TheFinestArtist/FinestWebView-Android");
        mLibsList.put("NavigationView", "http://developer.android.com/reference/android/support/design/widget/NavigationView.html");
        mLibsList.put("ButterKnife", "http://jakewharton.github.io/butterknife/");

        AboutAdapter adapter = new AboutAdapter();
        mAboutRecyclerview.setAdapter(adapter);
        mAboutRecyclerview.setLayoutManager(new LinearLayoutManager(this));

        setAutoLinkTextView(mRepository);
        setAutoLinkTextView(mGithub);


    }

    /**
     * 自定义设置了AutoLink的TextView点击事件
     * @param textView android:autoLink="web"
     */
    private void setAutoLinkTextView(TextView textView){
        CharSequence text = textView.getText();

        if (text instanceof Spannable){
            int end = text.length();
            Spannable sp = (Spannable)text;
            URLSpan[] urls = sp.getSpans(0,end,URLSpan.class);
            SpannableStringBuilder style = new SpannableStringBuilder(text);
            style.clearSpans();
            for (URLSpan urlSpan : urls) {
                MyURLSpan myURLSpan = new MyURLSpan(urlSpan.getURL());
                style.setSpan(myURLSpan, sp.getSpanStart(urlSpan),
                        sp.getSpanEnd(urlSpan),
                        Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            }
            textView.setText(style);
        }
    }

    /**
     * 设置点击事件
     */
    private class MyURLSpan extends ClickableSpan{

        private String url;

        public MyURLSpan(String url) {
            this.url = url;
        }

        @Override
        public void onClick(View view) {
            openUrl(url);

        }
    }

    private void openUrl(String url) {
        new FinestWebView.Builder(this)
                .statusBarColorRes(R.color.colorPrimary)
                .iconDefaultColorRes(R.color.white)
                .iconDisabledColorRes(R.color.white)
                .iconPressedColorRes(R.color.white)
                .swipeRefreshColorRes(R.color.colorPrimary)
                .titleColorRes(R.color.white)
                .urlColorRes(R.color.white)
                .progressBarColorRes(R.color.white)
                .menuTextColorRes(R.color.colorPrimary)
                .stringResRefresh(R.string.refresh)
                .stringResShareVia(R.string.share_via)
                .stringResCopyLink(R.string.copy_link)
                .stringResOpenWith(R.string.open_with)
                .stringResCopiedToClipboard(R.string.copied_to_clipboard)
                .show(url);
    }

    class AboutAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return viewType == 1
                    ? new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_about,
                    parent, false))
            :new HeaderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.header_about,parent,false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            if (holder.getItemViewType() == 1){
                ((ItemViewHolder)holder).mItemText.setText(mLibsList.keyAt(position - 1));
                ((ItemViewHolder)holder).mItemText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        openUrl(mLibsList.valueAt(position - 1));
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return mLibsList.size() + 1;
        }

        @Override
        public int getItemViewType(int position) {
            return position == 0 ? 0 : 1;
        }

        class ItemViewHolder extends RecyclerView.ViewHolder {
            @Bind(R.id.item_text)
            TextView mItemText;


            public ItemViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }

        class HeaderViewHolder extends RecyclerView.ViewHolder{


            public HeaderViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this,itemView);
            }
        }

    }
}
