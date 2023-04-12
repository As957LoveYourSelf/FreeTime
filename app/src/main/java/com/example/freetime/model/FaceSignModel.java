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
import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FaceSignModel implements IFaceSignModel {

    private String classname;
    private String course;
    private String uid;
    private Number state;
    public FaceSignModel(String classname, String course){
        this.classname = classname;
        this.course = course;
    }

    public FaceSignModel(String classname){
        this.classname = classname;
    }

    public FaceSignModel(String uid, Number state){
        this.uid = uid;
        this.state = state;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void signFace(OnLoaderListener onLoaderListener) {
        FaceSignService service = RetrofitClient.getInstance().getService(FaceSignService.class);
        try {
            Map<String, Object> map = new HashMap<>();
            byte[] bytes = ImageUtils.readImageToBytes("data/data/com.example.freetime/sign_img.jpg");
            String img = Base64.getEncoder().encodeToString(bytes);
            map.put("classname", classname);
            map.put("face", img);
            map.put("course", course);
            map.put("tid", SaveInfoUtils.readInfo()[0]);
//            System.out.println("post data");
            service.sign(map).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<ResponseBean<Map<String, Object>>>() {
                        @Override
                        public void accept(ResponseBean<Map<String, Object>> data) throws Throwable {
                            onLoaderListener.onMapComplete(data.getData());
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Throwable {
                            throwable.printStackTrace();
                            onLoaderListener.onErrMsg("网络请求错误");
                        }
                    });

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void getDetail(OnLoaderListener onLoaderListener) {
        FaceSignService service = RetrofitClient.getInstance().getService(FaceSignService.class);
        service.getDetail(classname).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBean<Map<String, Object>>>() {
                    @Override
                    public void accept(ResponseBean<Map<String, Object>> responseBean) throws Throwable {
                        onLoaderListener.onMapComplete(responseBean.getData());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        throwable.printStackTrace();
                        onLoaderListener.onErrMsg("网络请求错误");
                    }
                });
    }

    @Override
    public void setSign(OnLoaderListener onLoaderListener) {
        FaceSignService service = RetrofitClient.getInstance().getService(FaceSignService.class);
        service.setSign(uid, state).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBean<Object>>() {
                    @Override
                    public void accept(ResponseBean<Object> responseBean) throws Throwable {
                        onLoaderListener.onObjectComplete(responseBean.getData());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        throwable.printStackTrace();
                        onLoaderListener.onErrMsg("网络请求错误");
                    }
                });
    }

    @Override
    public void endSign(OnLoaderListener onLoaderListener) {
        FaceSignService service = RetrofitClient.getInstance().getService(FaceSignService.class);
        service.endSign(classname).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBean<Object>>() {
                    @Override
                    public void accept(ResponseBean<Object> responseBean) throws Throwable {
                        onLoaderListener.onObjectComplete(responseBean.getData());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        throwable.printStackTrace();
                        onLoaderListener.onErrMsg("网络请求错误");
                    }
                });
    }
}
