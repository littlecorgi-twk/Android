package com.example.a1203.fragmentbestpractice;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * @author littlecorgi
 * @email a1203991686@126.com
 * @Date 2018-08-17 16:06
 */

public class NewsContentActivity extends AppCompatActivity {
    public static void actionStart(Context context, String newsTitle, String newsContent){
        Intent intent = new Intent(context, NewsContentActivity.class);
        intent.putExtra("news_title", newsTitle);
        intent.putExtra("news_content", newsContent);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_content);
        // 获取传入的新闻标题
        String newsTitle = getIntent().getStringExtra("news_title");
        // 获取传入的新闻内容
        String newsContent = getIntent().getStringExtra("news_content");
        NewsContentFragment newsContentFragment = ((NewsContentFragment) getSupportFragmentManager().findFragmentById(R.id.news_content_fragment));
        // 刷新NewsContentFragment界面
        if (newsContentFragment != null) {
            newsContentFragment.refresh(newsTitle, newsContent);
        }
    }
}
