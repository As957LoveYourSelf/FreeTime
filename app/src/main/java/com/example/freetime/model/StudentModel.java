package com.example.freetime.model;

import com.example.freetime.beans.BaseBean;
import com.example.freetime.model.interfaces.IStudentModel;
import com.example.freetime.network.RetrofitClient;
import com.example.freetime.network.service.StudentService;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.rxjava3.functions.Consumer;

public class StudentModel extends UserManageModel implements IStudentModel {

    /**
     *  发送数据至后台，获取登录状态信息
     */

    private String sid;

    @Override
    public void getStudentInfo(IStudentModel.OnLoaderListener onLoaderListener) {
        onLoaderListener.onComplete(getStudentMap());
    }

    @Override
    public void setStudentID(String id) {
        this.sid = id;
    }

    private Map<String, Object> getStudentMap(){
        if (this.sid != null){
            StudentService service = RetrofitClient.getInstance().getService(StudentService.class);
            Map<String, Object> userData = new HashMap<>();
            service.getStu(this.sid).subscribe(new Consumer<BaseBean<Map<String, Object>>>() {
                @Override
                public void accept(BaseBean<Map<String, Object>> mapBaseBean) throws Throwable {
                    Map<String, Object> info = mapBaseBean.getData();
                    userData.put("info", info);
                }
            });
            return userData;
        }
        return null;
    }
}
