package com.example.freetime.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Build;
import android.util.Base64;

import androidx.annotation.RequiresApi;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;

public class ImageUtils {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static byte[] readImageToBytes(String path) throws IOException {
        File file = new File(path);
        if (file.exists()){
            return Files.readAllBytes(file.toPath());
        }
        return null;
    }

    //回收图片所占的内存
    public static void gcBitmap(Bitmap bitmap) {
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle(); // 回收图片所占的内存
            bitmap = null;
            System.gc(); // 提醒系统及时回收
        }
    }

    /**
     * 旋转图片
     *
     * @param bitmap
     * @param rotation
     * @Return
     */
    public static Bitmap getRotatedBitmap(Bitmap bitmap, int rotation) {
        Matrix matrix = new Matrix();
        matrix.postRotate(rotation);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                bitmap.getHeight(), matrix, false);
    }

    /**
     * 镜像翻转图片
     *
     * @param bitmap
     * @Return
     */
    public static Bitmap getFlipBitmap(Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.setScale(-1, 1);
        matrix.postTranslate(bitmap.getWidth(), 0);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                bitmap.getHeight(), matrix, false);
    }

    /**
     * bitmap转为byte[]
     *
     * @param bitmap
     * @return
     */
    public static byte[] bitmap2Bytes(Bitmap bitmap) throws IOException {
        ByteArrayOutputStream bao = null;
        byte[] bytes;
        try {
            bao = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, bao);
            bytes = bao.toByteArray();
        }finally {
            bao.close();
        }
        return bytes;
    }

    /**
     * byte[]转为bitmap
     *
     * @param bytes
     * @return
     */
    public static Bitmap bytes2bitmap(byte[] bytes){
        return BitmapFactory.decodeByteArray(bytes,0,bytes.length);
    }

    /**
     * bitmap转为base64
     *
     * @param bitmap
     * @return
     */
    public static String bitmapToBase64(Bitmap bitmap) {

        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);

                baos.flush();
                baos.close();

                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * base64转为bitmap
     *
     * @param base64Data
     * @return
     */
    public static Bitmap base64ToBitmap(String base64Data) {
        byte[] bytes = Base64.decode(base64Data, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}
