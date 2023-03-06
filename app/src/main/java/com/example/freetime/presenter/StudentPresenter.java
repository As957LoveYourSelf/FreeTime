package com.example.freetime.presenter;

import com.example.freetime.model.StudentModel;
import com.example.freetime.model.interfaces.IBaseModel;
import com.example.freetime.model.interfaces.IStudentModel;
import com.example.freetime.model.interfaces.IUserManageModel;
import com.example.freetime.view.IStudentView;

import java.util.List;
import java.util.Map;

public class StudentPresenter extends BasePresenter<IStudentView>{

    public void fetch(String sid) throws InterruptedException {
        IStudentModel iStudentModel = new StudentModel(sid);
        if (mView.get() != null){
            iStudentModel.getStudentInfo(new IBaseModel.OnLoaderListener() {
                @Override
                public void onMapComplete(Map<String, Object> info) {
                    mView.get().getUserInfo(info);
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
