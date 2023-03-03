package com.example.freetime.model;

import com.example.freetime.model.interfaces.ITeacherModel;
import com.example.freetime.network.RetrofitClient;
import com.example.freetime.network.service.TeacherService;

import java.util.HashMap;
import java.util.Map;

public class TeacherModel extends UserManageModel implements ITeacherModel {

    private String tno;

    @Override
    public void setTno(String tno) {
        this.tno = tno;
    }

    @Override
    public void getTeacherInfo(OnLoaderListener onLoaderListener) {
        onLoaderListener.onMapComplete(getInfo());
    }

    private Map<String, Object> getInfo(){
        Map<String, Object> response = new HashMap<>();
        if (tno != null){
            TeacherService service = RetrofitClient.getInstance().getService(TeacherService.class);
            service.getInfo(tno).subscribe(mapBaseBean -> response.put("info", mapBaseBean.getData()));
        }
        return response;
    }
}
