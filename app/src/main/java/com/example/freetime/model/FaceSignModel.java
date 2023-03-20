package com.example.freetime.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.freetime.beans.ResponseBean;
import com.example.freetime.model.interfaces.IFaceSignModel;
import com.example.freetime.network.RetrofitClient;
import com.example.freetime.network.service.FaceSignService;
import com.example.freetime.utils.ImageUtils;
import com.example.freetime.utils.SaveInfoUtils;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FaceSignModel implements IFaceSignModel {

    private String classname;
    public FaceSignModel(String classname){
        this.classname = classname;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void signFace(OnLoaderListener onLoaderListener) {
        FaceSignService service = RetrofitClient.getInstance().getService(FaceSignService.class);
        try {
            Map<String, Object> map = new HashMap<>();
            // TODO: change path
            byte[] bytes = ImageUtils.readImageToBytes("data/data/com.example.freetime/sign_img.jpg");
            String img = Base64.getEncoder().encodeToString(bytes);
            map.put("classname", classname);
            map.put("face", img);
            service.sign(map).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<ResponseBean<Map<String, Object>>>() {
                        @Override
                        public void accept(ResponseBean<Map<String, Object>> data) throws Throwable {
                            onLoaderListener.onObjectComplete(data.getData());

                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Throwable {
                            throwable.printStackTrace();
                            onLoaderListener.onErrMsg("error");
                        }
                    });

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
