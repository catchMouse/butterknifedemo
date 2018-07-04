package com.example.szx.butterknifedemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LauncherActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
    }

    public void baseUseForButterknife(View view) {
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        startActivity(intent);
    }
    public void annotationAndAPT(View view) {
        Intent intent = new Intent();
        intent.setClass(this, AnnotationActivity.class);
        startActivity(intent);
    }
}
