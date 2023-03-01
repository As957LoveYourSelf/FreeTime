package com.example.freetime.presenter;

import com.example.freetime.model.TeacherModel;
import com.example.freetime.model.interfaces.IBaseModel;
import com.example.freetime.model.interfaces.ITeacherModel;
import com.example.freetime.view.ITeacherView;

import java.util.List;
import java.util.Map;

public class TeacherPresenter extends BasePresenter<ITeacherView> {
    ITeacherModel teacherModel = new TeacherModel();

    public void fetch(String tno){
        if (teacherModel != null && mView.get() != null){
            teacherModel.setTno(tno);
            teacherModel.getTeacherInfo(new IBaseModel.OnLoaderListener() {
                @Override
                public void onMapComplete(Map<String, Object> map) {
                    mView.get().getUserInfo(map);
                }

                @Override
                public void onListComplete(List<Object> list) {

                }

                @Override
                public void onObjectComplete(Object obj) {

                }

                @Override
                public void onErrMsg() {

                }
            });
        }
    }
}
