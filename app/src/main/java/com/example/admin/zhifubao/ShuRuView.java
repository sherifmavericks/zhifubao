package com.example.admin.zhifubao;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by admin on 2016/12/1.
 */

public class ShuRuView extends View {

    private Paint paint;
    private boolean isIfrst;
    private Paint circlePaint;

    public ShuRuView(Context context) {
        this(context,null);
    }

    public ShuRuView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ShuRuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#cccccc"));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(dp2px(2));
        circlePaint = new Paint();
        circlePaint.setAntiAlias(true);
        circlePaint.setColor(Color.BLACK);
        circlePaint.setStyle(Paint.Style.FILL);
        r=dp2px(10);
    }
    int i=0;
    int r=0;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
       if(isIfrst){
           initRect(canvas);
           initLines(canvas);
       }
        for(int j=0;j<i;j++) {
            canvas.drawCircle(getCenterX(j), getHeight()/2, r, circlePaint);
        }
    }
    StringBuilder sb=new StringBuilder();
    public void add(int key){
        if(i<6){
            i++;
            sb.append(key);
            invalidate();
        }
    }
    public void remove(){
        if(i>0){
            i--;
            sb.deleteCharAt(i);
            invalidate();
        }
    }
    public int getCenterX(int j){
      int width=  getWidth();
       return width/6/2+width/6*(j);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        isIfrst = true;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        isIfrst=false;
    }

    private void initLines(Canvas canvas) {
        int width=getWidth();
        int height=getHeight();
       int path= width/6;
        for(int i=0;i<5;i++){
            canvas.drawLine(path*(i+1),0,path*(i+1),height,paint);
        }
    }

    private void initRect(Canvas canvas) {
        int width=getWidth();
        int height=getHeight();
        RectF rectF=   new RectF(0,0,width,height);
        canvas.drawRoundRect(rectF,dp2px(10),dp2px(10),paint);
    }

    public int dp2px(int d){
       return (int) (getResources().getDisplayMetrics().density*d);
    }

    public String getDaAn() {
        return sb.toString();
    }
}
