package com.example.freetime.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Path;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class BitmapUtils {
    /**
     * @param bitmapPath bitmap图片的地址
     * @return
     */
    public static BitmapFactory.Options getBitmapInfo(String bitmapPath) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(bitmapPath, options);
        return options;
    }

    /**
     * @param options    原图的Options对象
     * @param showWidth  需要显示的宽度
     * @param showHeight 需要显示的高度
     * @return
     */
    public static int calculateInSampleSize(BitmapFactory.Options options, int showWidth, int showHeight) {
        int height = options.outHeight;
        int width = options.outWidth;
        int inSampleSize = 1;
        if (height > showHeight || width > showWidth) {
            // 需要进行缩放，每次缩放一半
            int scaleHeight = height / 2;
            int scaleWidth = width / 2;
            while ((scaleHeight / inSampleSize) > showHeight && (scaleWidth / inSampleSize) > showWidth) {
                inSampleSize *= 2;
            }
            // 通过上面的2的幂次方可以找到一个范围 inSampleSize ~ inSampleSize*2 的一个区间存在一个合适的值
            // 通过2分法找到那个最合适的值。
            int lValue = inSampleSize, rValue = 2 * inSampleSize;
            int midValue = (lValue + rValue) / 2;
            while (midValue > lValue && midValue < rValue) {
                scaleHeight = height / midValue;
                scaleWidth = width / midValue;
                // 特殊情况的处理
                if (scaleHeight == showHeight || scaleWidth == showWidth) {
                    break;
                }
                if (scaleHeight > showHeight && scaleWidth > showWidth) {
                    lValue = midValue;
                } else {
                    rValue = midValue;
                }
                midValue = (lValue + rValue) / 2;
            }
            inSampleSize = midValue;
        }
        return inSampleSize;
    }

    public static Bitmap scaleImage(Bitmap bitmap, int view_w, int view_h) {
        if (view_h > view_w){
            float t = view_h/bitmap.getHeight() >= 1?view_h/(float)bitmap.getHeight():(float) bitmap.getHeight()/view_h;
            int dst_w = (int)(t*bitmap.getWidth());
            return Bitmap.createScaledBitmap(bitmap,dst_w,view_h,true);
        }else {
            float t = view_w/bitmap.getWidth() >=1 ?view_w/(float)bitmap.getWidth():(float) bitmap.getWidth()/view_w;
            int dst_h = (int) (t*bitmap.getHeight());
            return Bitmap.createScaledBitmap(bitmap,view_w,dst_h,true);
        }
    }

    public static String saveBitmapToLocal(Bitmap bitmap, String dir_type){
        String dir = Environment.getExternalStorageDirectory()
                .getAbsolutePath()+"/freetime/"+SaveInfoUtils.readInfo()[0]+"/"+dir_type+"/";
        String state = Environment.getExternalStorageState();
        if (!state.equals(Environment.MEDIA_MOUNTED)){
            return null;
        }
        Calendar now = new GregorianCalendar();
        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
        String fileName = simpleDate.format(now.getTime());
        try {
            File file = new File(dir + fileName + ".png");
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
            return file.getPath();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
