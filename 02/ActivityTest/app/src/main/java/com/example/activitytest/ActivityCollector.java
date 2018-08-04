package com.example.activitytest;

import android.app.Activity;
import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by littlecorgi_a1203991686 on 2018/07/30 14:29.
 */
public class ActivityCollector {
    public static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity){
        activities.add(activity);
    }

    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }

    public static void finishAll(){
        // 结束所有活动
        for (Activity activity : activities){
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
        activities.clear();

        // 杀掉进程
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        }, 500);
    }
}
