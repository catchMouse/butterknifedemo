package com.example.szx.utils;

import android.app.Activity;

import com.example.szx.annotation.BindMyView;

import java.lang.reflect.Field;

public class Annotationutil {

    //针对运行时注解的使用(反射技术)
    public static void bind(Activity target) {
        Class clazz = target.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            BindMyView anno = field.getAnnotation(BindMyView.class);
            if (anno != null) {
                field.setAccessible(true);
                try {
                    field.set(target, target.findViewById(anno.value()));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
