package com.example.freetime.model;

import com.example.freetime.beans.ResponseBean;
import com.example.freetime.model.interfaces.ICourseTableModel;
import com.example.freetime.network.RetrofitClient;
import com.example.freetime.network.service.CourseTableService;
import com.example.freetime.utils.SaveInfoUtils;

import java.util.Map;
import java.util.Objects;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CourseTableModel implements ICourseTableModel {

    private String uid;
    private String utype;

    @Override
    public void setUid(String uid, String utype) {
        this.uid = uid;
        this.utype = utype;
    }

    @Override
    public void getTable(OnLoaderListener onLoaderListener) throws InterruptedException {
        if (uid != null){
            CourseTableService courseTableService = RetrofitClient.getInstance().getService(CourseTableService.class);
            if (Objects.equals(this.utype, "teacher")){
                courseTableService.getTableByTno(uid)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<ResponseBean<Map<String, Object>>>() {
                    @Override
                    public void accept(ResponseBean<Map<String, Object>> mapResponseBean) throws Throwable {
                        if (Objects.equals(mapResponseBean.getMessage(), "success")){
                            onLoaderListener.onMapComplete(mapResponseBean.getData());
                        }
                    }
                },new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Throwable {
                                onLoaderListener.onErrMsg("网络请求错误");
                            }}
                        );
            }

            if (Objects.equals(this.utype, "student")){
                courseTableService.getTableBySno(uid)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<ResponseBean<Map<String, Object>>>() {
                    @Override
                    public void accept(ResponseBean<Map<String, Object>> mapResponseBean) throws Throwable {
                        if (Objects.equals(mapResponseBean.getMessage(), "success")){
                            onLoaderListener.onMapComplete(mapResponseBean.getData());

                        }
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

}
