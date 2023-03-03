package com.example.freetime.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.freetime.R;

public class CircleCanvasView extends View {
    private Context mContext;

    private Paint mPaint;

    private float MAX_X = 0;

    private float MAX_Y = 0;
    private boolean isRewview = false;

    public CircleCanvasView(Context context) {
        super(context);
        init(context);
    }

    public CircleCanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CircleCanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {

        this.mContext = context;

        Display display = ((Activity) context).getWindowManager().getDefaultDisplay();
        MAX_X = display.getWidth();
        MAX_Y = display.getHeight();

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAlpha(0);
        // android.graphics.PorterDuff.Mode.CLEAR 显示挖空canvas为透明
        mPaint.setXfermode(new PorterDuffXfermode(android.graphics.PorterDuff.Mode.CLEAR));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isRewview){
            canvas.drawARGB(255, 255, 255, 255);
            canvas.drawCircle(MAX_X / 2, MAX_Y / 2, 350, mPaint);
        }else{
            canvas.drawARGB(255, 255, 255, 255);
            @SuppressLint("DrawAllocation")
            Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_launcher);
            @SuppressLint("DrawAllocation")
            Rect dst = new Rect((int) (MAX_X / 2) - 350, (int) (MAX_Y / 2) - 350, (int) (MAX_X / 2) + 350, (int) (MAX_Y / 2) + 350);
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(getResources().getColor(R.color.white));
            paint.setAlpha(255);
            canvas.drawBitmap(bitmap, null, dst, paint);
        }
    }

    public void setSuccess(){
        isRewview = true;
    }
}
