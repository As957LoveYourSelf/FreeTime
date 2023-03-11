package com.example.freetime.model;

import android.graphics.Bitmap;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.freetime.beans.ResponseBean;
import com.example.freetime.model.interfaces.ISuperResolveModel;
import com.example.freetime.network.RetrofitClient;
import com.example.freetime.network.service.SuperResolveService;
import com.example.freetime.utils.ImageUtils;
import com.example.freetime.utils.SaveInfoUtils;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SuperResolveModel implements ISuperResolveModel {

    private byte[] img;

    public SuperResolveModel(Bitmap img) throws IOException {
        if (img != null){
            this.img = ImageUtils.bitmap2Bytes(img);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void enhance(OnLoaderListener onLoaderListener) {
        if (img != null){
            SuperResolveService service = RetrofitClient.getInstance().getService(SuperResolveService.class);
            String s = Base64.getEncoder().encodeToString(this.img);
            Map<String, Object> data = new HashMap<>();
            data.put("img", s);
            data.put("uname", SaveInfoUtils.readInfo()[0]);
            service.enhance(data)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<ResponseBean<String>>() {
                        @Override
                        public void accept(ResponseBean<String> stringResponseBean) throws Throwable {
                            byte[] decode = Base64.getDecoder().decode(stringResponseBean.getData());
                            onLoaderListener.onObjectComplete(decode);
                        }
                    },new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Throwable {
                            onLoaderListener.onErrMsg("网络请求错误");
                        }
                    });
        }
    }

}
