package com.example.a1203.fragmentbestpractice;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Objects;

/**
 * @author littlecorgi
 * @Date 2018-08-17 16:59
 * @email a1203991686@126.com
 */
public class NewsTitleFragment extends Fragment{
    private boolean isTwoPane;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_title_frag, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (Objects.requireNonNull(getActivity()).findViewById(R.id.news_content_layout) != null){
            // 可以找到news_content_layout布局时，为双页模式
            isTwoPane = true;
        } else {
            // 找不到news_content_layout布局时，为单页模式
            isTwoPane = false;
        }
    }

    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
        private List<News> mNewsList;

        class ViewHolder extends RecyclerView.ViewHolder{
            TextView newsTitleText;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                newsTitleText = ((TextView) itemView.findViewById(R.id.news_title));
            }
        }

        public NewsAdapter(List<News> newsList){
            mNewsList = newsList;
        }


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
            final ViewHolder holder = new ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    News news = mNewsList.get(holder.getAdapterPosition());
                    if (isTwoPane){
                        // 如果是双页模式，则刷新 NewsContentFragment 中的内容
                        NewsContentFragment newsContentFragment = null;
                        if (getFragmentManager() != null) {
                            newsContentFragment = ((NewsContentFragment) getFragmentManager().findFragmentById(R.id.news_content_fragment));
                        }
                        if (newsContentFragment != null) {
                            newsContentFragment.refresh(news.getTitle(), news.getContent());
                        }
                    } else {
                        // 如果是单页模式，则直接启动 NewsContentActivity中的内容
                        NewsContentActivity.actionStart(getActivity(), news.getTitle(), news.getContent());
                    }
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            News news = mNewsList.get(position);
            holder.newsTitleText.setText(news.getTitle());
        }

        @Override
        public int getItemCount() {
            return mNewsList.size();
        }
    }
}
