package com.example.admin.zhifubao;

import android.app.Dialog;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.LayerDrawable;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements KeyboardView.OnKeyboardActionListener, View.OnClickListener {

    private android.widget.RelativeLayout group;
    private android.widget.RelativeLayout activitymain;
    private android.view.View top1;
    private android.widget.LinearLayout markGroup;
    List<View> list=new ArrayList<>();
    private LayerDrawable layerDrawable;
    private android.inputmethodservice.KeyboardView keyBoary;
    private Dialog dialog;
    private ShuRuView shuru;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.activitymain = (RelativeLayout) findViewById(R.id.activity_main);
        this.shuru = (ShuRuView) findViewById(R.id.shuru);
     initDiaog();

    }

    private void initDiaog() {
        View v= View.inflate(this,R.layout.l,null);
        this.keyBoary = (KeyboardView) v.findViewById(R.id.keyBoary);
        keyBoary.setKeyboard(new Keyboard(this,R.xml.key));
        keyBoary.setOnKeyboardActionListener(this);
        dialog = new Dialog(this);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.setContentView(v);
        dialog.getWindow().setLayout(getWindowManager().getDefaultDisplay().getWidth(), WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new BitmapDrawable());
        dialog.getWindow().setWindowAnimations(R.style.ani);
//        dialog.getWindow().getAttributes().flags=WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
        dialog.getWindow().getAttributes().dimAmount=0;
        dialog.show();
    }

    StringBuilder stringBuilder=new StringBuilder();
    int i=0;
    public void shuru(int key){

        shuru.add(key);
    }
    public void quxiao(){
shuru.remove();
    }
public void toast(View v){
    Toast.makeText(this,shuru.getDaAn(),0).show();
}
    public View getDot(int width, int height) {
        View view=new View(this);
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(width,height);
        view.setLayoutParams(params);
        view.setBackgroundResource(R.drawable.circle);
        return view;
    }

    @Override
    public void onPress(int primaryCode) {

    }

    @Override
    public void onRelease(int primaryCode) {

    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
       if(primaryCode==11){
           // 删除
           quxiao();
       }else if(primaryCode!=10){
           // 添加
           shuru(primaryCode);
       }
    }

    @Override
    public void onText(CharSequence text) {

    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }

    @Override
    public void onClick(View v) {
        toggle();
    }

    private void toggle() {
        dialog.show();
    }
    public void click(View v){
        dialog.show();
    }
}
