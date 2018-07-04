package com.example.szx.butterknifedemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.szx.annotation.BindMyView;
import com.example.szx.utils.Annotationutil;

//运行时注解实例
public class AnnotationActivity extends Activity {

    @BindMyView(R.id.annotation_text)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annotation);

        Annotationutil.bind(this);
        textView.setText("this is annotation text.");

    }
}
