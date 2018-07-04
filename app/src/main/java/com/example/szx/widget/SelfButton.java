package com.example.szx.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

import butterknife.OnClick;

//自定义View使用绑定事件
public class SelfButton extends Button {
    public SelfButton(Context context) {
        super(context);
    }

    public SelfButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SelfButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //不用指定id(OnClick作用于this控件)，直接注解OnClick
    @OnClick
    public void checkOnClick() {

    }

}
