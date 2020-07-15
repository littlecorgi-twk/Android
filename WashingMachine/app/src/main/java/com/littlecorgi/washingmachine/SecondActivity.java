package com.littlecorgi.washingmachine;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author littlecorgi
 */
public class SecondActivity extends AppCompatActivity {

    private static final String TAG = SecondActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
                @Override
                public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
                    Log.d(TAG, "onActivityCreated: 1");
                }

                @Override
                public void onActivityStarted(@NonNull Activity activity) {
                    Log.d(TAG, "onActivityStarted: 1");
                }

                @Override
                public void onActivityResumed(@NonNull Activity activity) {
                    Log.d(TAG, "onActivityResumed: 1");
                }

                @Override
                public void onActivityPaused(@NonNull Activity activity) {
                    Log.d(TAG, "onActivityPaused: 1");
                }

                @Override
                public void onActivityStopped(@NonNull Activity activity) {
                    Log.d(TAG, "onActivityStopped: 1");
                }

                @Override
                public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {
                    Log.d(TAG, "onActivitySaveInstanceState: 1");
                }

                @Override
                public void onActivityDestroyed(@NonNull Activity activity) {
                    Log.d(TAG, "onActivityDestroyed: 1");
                    if (activity.isFinishing()) {
                        Log.d(TAG, "onActivityDestroyed: isFinished");
                    } else {
                        Log.d(TAG, "onActivityDestroyed: not finished");
                    }
                }
            });
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: finish");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: finish");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: finish");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: finish");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: finish Destroy");
    }
}