package com.example.freetime.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.freetime.beans.ResponseBean;
import com.example.freetime.model.interfaces.IFaceImportModel;
import com.example.freetime.network.RetrofitClient;
import com.example.freetime.network.service.FaceImportService;
import com.example.freetime.utils.ImageUtils;
import com.example.freetime.utils.SaveInfoUtils;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FaceImportModel implements IFaceImportModel {

    public FaceImportModel(){

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void importFace(OnLoaderListener onLoaderListener) {
        FaceImportService service = RetrofitClient.getInstance().getService(FaceImportService.class);
        try {
            Map<String, Object> map = new HashMap<>();
            byte[] bytes = ImageUtils.readImageToBytes("data/data/com.example.freetime/select_img.jpg");
            String s = SaveInfoUtils.readInfo()[0];
            String img = Base64.getEncoder().encodeToString(bytes);
            map.put("uid", s);
            map.put("face", img);
            service.importFace(map).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<ResponseBean<Object>>() {
                        @Override
                        public void accept(ResponseBean<Object> data) throws Throwable {
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
