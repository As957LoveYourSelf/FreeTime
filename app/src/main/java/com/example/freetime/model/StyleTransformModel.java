package com.example.freetime.model;

import android.graphics.Bitmap;

import com.example.freetime.model.interfaces.IStyleTransformModel;
import com.example.freetime.network.RetrofitClient;
import com.example.freetime.network.service.StyleTransformService;
import com.example.freetime.utils.ImageUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.functions.Consumer;

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
        onLoaderListener.onObjectComplete(defaultStyleTransform());
    }

    @Override
    public void anyStyleTransform(OnLoaderListener onLoaderListener) {
        onLoaderListener.onObjectComplete(anyStyleTransform());
    }

    private byte[] defaultStyleTransform( ){
        try {
            List<byte[]> info = new ArrayList<>();
            if (this.bitmap != null && type != null){
                byte[] bytes = ImageUtils.bitmap2Bytes(bitmap);
                StyleTransformService service = RetrofitClient.getInstance().getService(StyleTransformService.class);
                service.defaultStyleTransform(bytes, type).subscribe(new Consumer<byte[]>() {
                    @Override
                    public void accept(byte[] bytes) throws Throwable {
                        info.add(bytes);
                    }
                });
            }
            return info.get(0);
        }catch (IOException e){
            return null;
        }
    }

    private byte[] anyStyleTransform(){
        try {
            List<byte[]> info = new ArrayList<>();
            if (this.context != null && style != null){
                byte[] context = ImageUtils.bitmap2Bytes(this.context);
                byte[] style = ImageUtils.bitmap2Bytes(this.style);
                StyleTransformService service = RetrofitClient.getInstance().getService(StyleTransformService.class);
                service.anyStyleTransform(context, style).subscribe(new Consumer<byte[]>() {
                    @Override
                    public void accept(byte[] bytes) throws Throwable {
                        info.add(bytes);
                    }
                });
            }
            return info.get(0);
        }catch (IOException e){
            return null;
        }
    }
}
