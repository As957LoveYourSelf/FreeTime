package com.example.freetime.model;

import com.example.freetime.beans.ResponseBean;
import com.example.freetime.model.interfaces.IBaseModel;
import com.example.freetime.model.interfaces.IStudentModel;
import com.example.freetime.network.RetrofitClient;
import com.example.freetime.network.service.StudentService;
import com.example.freetime.utils.SaveInfoUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class StudentModel extends UserManageModel implements IStudentModel {

    /**
     *  发送数据至后台，获取登录状态信息
     */

    private String sid;

    public StudentModel(String id){
        this.sid = id;
    }

    @Override
    public void getStudentInfo(IBaseModel.OnLoaderListener onLoaderListener) throws InterruptedException {
        if (this.sid != null){
            StudentService service = RetrofitClient.getInstance().getService(StudentService.class);
            Map<String, Object> userData = new HashMap<>();
//            System.out.println("开始请求用户信息");
            service.getInfo(this.sid)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<ResponseBean<Map<String, Object>>>() {
                @Override
                public void accept(ResponseBean<Map<String, Object>> mapResponseBean) throws Throwable {
                    Map<String, Object> info = mapResponseBean.getData();
                    userData.put("info", info);
                    onLoaderListener.onMapComplete(userData);
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
