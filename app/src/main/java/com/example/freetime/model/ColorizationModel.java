package com.example.freetime.model;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.freetime.beans.ResponseBean;
import com.example.freetime.model.interfaces.IColorizationModel;
import com.example.freetime.network.RetrofitClient;
import com.example.freetime.network.service.ColorizationService;
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

public class ColorizationModel implements IColorizationModel {
    private byte[] img;

    public ColorizationModel(Bitmap img) throws IOException {
        if (img != null){
            this.img = ImageUtils.bitmap2Bytes(img);
        }
    }

    @SuppressLint("CheckResult")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void colorization(OnLoaderListener onLoaderListener) {
        if (img != null){
            ColorizationService service = RetrofitClient.getInstance().getService(ColorizationService.class);
            String s = Base64.getEncoder().encodeToString(this.img);
            Map<String, Object> data = new HashMap<>();
            data.put("img", s);
            data.put("uname", SaveInfoUtils.readInfo()[0]);
            service.colorization(data)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<ResponseBean<String>>() {
                        @Override
                        public void accept(ResponseBean<String> stringResponseBean) throws Throwable {
                            if (stringResponseBean.getData() != null){
                                byte[] decode = Base64.getDecoder().decode(stringResponseBean.getData());
                                System.out.println(Arrays.toString(decode));
                                onLoaderListener.onObjectComplete(decode);
                            }else {
                                onLoaderListener.onObjectComplete(null);
                            }
                        }
                    },new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Throwable {
                            throwable.printStackTrace();
                            onLoaderListener.onErrMsg("网络请求错误");
                        }
                    });
    }
}
}
