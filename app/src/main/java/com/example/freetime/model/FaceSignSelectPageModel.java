package com.example.freetime.model;

import com.example.freetime.beans.ResponseBean;
import com.example.freetime.model.interfaces.IFaceSignSelectPageModel;
import com.example.freetime.network.RetrofitClient;
import com.example.freetime.network.service.FaceSignSelectPageService;
import com.example.freetime.utils.SaveInfoUtils;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class FaceSignSelectPageModel implements IFaceSignSelectPageModel {

    public FaceSignSelectPageModel(){

    }

    @Override
    public void getClassSelector(OnLoaderListener onLoaderListener) {
        FaceSignSelectPageService service = RetrofitClient.getInstance().getService(FaceSignSelectPageService.class);
        service.getClasses(SaveInfoUtils.readInfo()[0]).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResponseBean<List<Object>>>() {
                    @Override
                    public void accept(ResponseBean<List<Object>> listResponseBean) throws Throwable {
                        onLoaderListener.onListComplete(listResponseBean.getData());
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
