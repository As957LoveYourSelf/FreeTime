package com.example.freetime.model;

import android.graphics.Bitmap;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.freetime.beans.ResponseBean;
import com.example.freetime.model.interfaces.IStyleTransformModel;
import com.example.freetime.network.RetrofitClient;
import com.example.freetime.network.service.StyleTransformService;
import com.example.freetime.utils.ImageUtils;
import com.example.freetime.utils.SaveInfoUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class StyleTransformModel implements IStyleTransformModel {

    private Bitmap bitmap;
    private Integer type;
    private Bitmap context;
    private Bitmap style;
    private String uname;

    public StyleTransformModel(Bitmap bitmap, Integer type,String uname){
        this.bitmap = bitmap;
        this.type = type;
        this.uname = uname;
    }

    public StyleTransformModel(Bitmap context, Bitmap style,String uname){
        this.context = context;
        this.style = style;
        this.uname = uname;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void defaultStyleTransform(OnLoaderListener onLoaderListener) {
        try {
            if (this.bitmap != null && type != null){
                byte[] bytes = ImageUtils.bitmap2Bytes(bitmap);
                String s = Base64.getEncoder().encodeToString(bytes);
//                String s = ImageUtils.bitmapToBase64(bitmap);
                StyleTransformService service = RetrofitClient.getInstance().getService(StyleTransformService.class);
                Map<String, Object> data = new HashMap<>();
                data.put("img", s);
                data.put("uname", uname);
                data.put("type", type);
//                System.out.println(data);
                service.defaultStyleTransform(data)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<ResponseBean<String>>() {
                            @Override
                            public void accept(ResponseBean<String> responseBean) throws Throwable {
                                byte[] decode = Base64.getDecoder().decode(responseBean.getData());
                                onLoaderListener.onObjectComplete(decode);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Throwable {
                                onLoaderListener.onErrMsg("网络请求错误");
                            }
                        });
            }
        }catch (Exception e){
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
                Map<String, Object> data = new HashMap<>();
                data.put("context", context);
                data.put("style", style);
                service.anyStyleTransform(data)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<ResponseBean<byte[]>>() {
                            @Override
                            public void accept(ResponseBean<byte[]> responseBean) throws Throwable {
                                onLoaderListener.onObjectComplete(responseBean.getData());
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Throwable {
                                onLoaderListener.onErrMsg("网络请求错误");
                            }
                        });
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
