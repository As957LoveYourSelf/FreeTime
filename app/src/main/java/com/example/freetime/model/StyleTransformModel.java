package com.example.freetime.model;

import android.graphics.Bitmap;

import com.example.freetime.model.interfaces.IStyleTransformModel;
import com.example.freetime.network.RetrofitClient;
import com.example.freetime.network.service.StyleTransformService;
import com.example.freetime.utils.ImageUtils;

import java.io.IOException;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class StyleTransformModel implements IStyleTransformModel {

    private Bitmap bitmap;
    private Integer type;
    private Bitmap context;
    private Bitmap style;

    public StyleTransformModel(Bitmap bitmap, Integer type){
        this.bitmap = bitmap;
        this.type = type;
    }

    public StyleTransformModel(Bitmap context, Bitmap style){
        this.context = context;
        this.style = style;
    }

    @Override
    public void defaultStyleTransform(OnLoaderListener onLoaderListener) {
        try {
            if (this.bitmap != null && type != null){
                byte[] bytes = ImageUtils.bitmap2Bytes(bitmap);
                StyleTransformService service = RetrofitClient.getInstance().getService(StyleTransformService.class);
                service.defaultStyleTransform(bytes, type)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<byte[]>() {
                    @Override
                    public void accept(byte[] bytes) throws Throwable {
                        onLoaderListener.onObjectComplete(bytes);
                    }
                });
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void anyStyleTransform(OnLoaderListener onLoaderListener) {
        try {
            if (this.context != null && style != null){
                byte[] context = ImageUtils.bitmap2Bytes(this.context);
                byte[] style = ImageUtils.bitmap2Bytes(this.style);
                StyleTransformService service = RetrofitClient.getInstance().getService(StyleTransformService.class);
                service.anyStyleTransform(context, style)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<byte[]>() {
                    @Override
                    public void accept(byte[] bytes) throws Throwable {
                        onLoaderListener.onObjectComplete(bytes);
                    }
                });
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
