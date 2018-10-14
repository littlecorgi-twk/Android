package com.example.a1203.fragmenttest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * @author littlecorgi
 * @email a1203991686@126.com
 * @Date 2018-08-17 08:43
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(this);
        replaceFragment(new RightFragment());
    }

    @Override
    public void onClick(View v) {
        count++;
        switch (v.getId()){
            case R.id.button:
                if (count % 2 == 0){
                    replaceFragment(new RightFragment());
                } else {
                    replaceFragment(new AnotherRightFragment());
                }
                break;
            default:
                break;
        }
    }

    private void replaceFragment(Fragment fragment){
        // FragmentManager fragmentManager = getSupportFragmentManager();
        // FragmentTransaction transaction = fragmentManager.beginTransaction();
        // transaction.replace(R.id.right_layout, fragment);
        // transaction.addToBackStack(null);
        // transaction.commit();
    }
}
