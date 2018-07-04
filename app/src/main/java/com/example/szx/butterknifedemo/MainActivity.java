package com.example.szx.butterknifedemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import butterknife.BindArray;
import butterknife.BindBitmap;
import butterknife.BindColor;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import butterknife.OnLongClick;
import butterknife.OnTextChanged;
import butterknife.Optional;

//ButterKnife是一个Android系统的View注入框架
//省去了findViewById等步骤
//ButterKnife用到的注解并不是在运行时反射的，而是在编译的时候生成新的class
//通过 @BindXXX 对资源文件id与对应的控件对象进行绑定

//优点:
// 1.省略了findViewById等步骤
// 2.省略了setOnItemClick, setOnClick, setOnLongClick等步骤


//Butterknife插件：zelezny  一次性将布局文件中所有控件的注解都声明

public class MainActivity extends Activity {
    public static final String TAG = "bk_MainActivity";

    @BindView(R.id.textview)
    TextView textview;   //绑定控件

    @BindView(R.id.button)
    TextView button;

    @BindView(R.id.imageview)
    ImageView imageview;
    @BindView(R.id.listview)
    ListView listview;

    @BindViews({R.id.button1, R.id.button2, R.id.button3})   //布局内多个控件id 注解
    List<Button> buttonList ;

    @BindString(R.string.text_new_txt)  //绑定字符串
    String text_new_txt;
    @BindString(R.string.button_new_txt)
    String button_new_txt;

    @BindArray(R.array.persons)   //绑定数组
    String [] persons ;

    @BindBitmap(R.drawable.a1)  //绑定Bitmap 资源
    Bitmap a1;

    @BindColor(R.color.colorAccent)
    int colorAccent;

    @BindView(R.id.spinner)
    Spinner spinner;

    //选择性注入，如果当前对象不存在，就会抛出一个异常，为了压制这个异常，
    //可以在变量或者方法上加入一下注解,让注入变成选择性的,如果目标View存在,则注入, 不存在,则什么事情都不做
    @Optional
    //绑定控件点击事件
    @OnClick(R.id.button)
    public void btn_click_handle(View view) {
        Log.d(TAG, "btn_click_handle:"+view);
        button.setText(button_new_txt);
    }

    //事件绑定
    @OnLongClick(R.id.button)
    public boolean btn_longclick_handle(View view) {
        Log.d(TAG, "btn_longclick_handle:"+view);
        button.setText("long:"+button_new_txt);
        return true;
    }

    @Nullable
    @BindViews({R.id.editText1, R.id.editText2, R.id.editText3})
    List<EditText> editTexts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);  //target==Activity  绑定初始化ButterKnife

        //Fragment中onCreateView绑定   unbinder = ButterKnife.bind(this, view);
        //onDestroyView中进行解绑操作 unbinder.unbind();

        //Adapter可以在ViewHolder中绑定   ButterKnife.bind(this, view);

        textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textview.setText(text_new_txt);
            }
        });

        listview.setAdapter(new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, persons));



        buttonList.get( 0 ).setText( "hello 1 ");
        buttonList.get( 1 ).setText( "hello 2 ");
        buttonList.get( 2 ).setText( "hello 3 ");

        imageview.setImageBitmap(a1);
        buttonList.get( 2 ).setTextColor(colorAccent);


        ButterKnife.apply(editTexts, DISABLE_ACTION);  //统一对多个控件使用Action
        ButterKnife.apply(editTexts, ENABLED_SETTER, false);  //通过setter方法赋值

        //View类内部有多个Property实现: ALPHA  TRANSLATION_X   TRANSLATION_Y  TRANSLATION_Z   X
        //FloatProperty IntProperty等抽象类需要自己实现内部setValue()方法
        ButterKnife.apply(editTexts, View.ALPHA, 0.1f);  //通过Property方式赋值, Property是android提供的类


    }

    private ButterKnife.Setter<EditText, Boolean> ENABLED_SETTER = new ButterKnife.Setter<EditText, Boolean>() {
        @Override
        public void set(@NonNull EditText view, Boolean value, int index) {
            view.setEnabled(value);  //传值方式
        }
    };

    private ButterKnife.Action<EditText> DISABLE_ACTION = new ButterKnife.Action<EditText>() {
        @Override
        public void apply(@NonNull EditText view, int index) {
            view.setEnabled(false);
        }
    };

    @OnItemSelected(R.id.spinner)
    public void OnItemSelected(int position) {
        Log.d(TAG, "OnItemSelected:position="+position);
    }

    @OnItemSelected(value=R.id.spinner, callback = OnItemSelected.Callback.NOTHING_SELECTED)
    //Spinner中只要有数据,默认都会选中第0个数据
    public void OnNothingSelected() {
        Log.d(TAG, "OnItemSelected:Nothing selected!");
    }

    //指定多个id绑定事件
    @OnClick({R.id.button1, R.id.button2, R.id.button3})
    public void handleBtnClick(View view) {
        Log.d(TAG, "handleBtnClick:"+view);
        switch (view.getId()) {
            case R.id.button1:
                break;
            case R.id.button2:
                break;
            case R.id.button3:
                break;
        }
    }


    // @OnCheckedChanged 针对CompoundButton(CheckBox, RadioButton, Switch, ToggleButton)

    // TextView firstName = ButterKnife.findById(view, R.id.first_name);


    /**
     ButterKnife的代码混淆
     在混淆文件中，添加如下代码：

     -keep class butterknife.** { *; }
     -dontwarn butterknife.internal.**
     -keep class **$$ViewBinder { *; }

     -keepclasseswithmembernames class * {
     @butterknife.* <fields>;
     }

     -keepclasseswithmembernames class * {
     @butterknife.* <methods>;
     }
     */

}
