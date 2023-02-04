package com.example.freetime.presenter;

import com.example.freetime.model.StudentModel;
import com.example.freetime.model.interfaces.IStudentModel;
import com.example.freetime.model.interfaces.IUserManageModel;
import com.example.freetime.view.IStudentView;

import java.util.Map;

public class StudentPresenter extends BasePresenter<IStudentView>{

    IStudentModel iStudentModel = new StudentModel();

    public void changeInfoFetch(){
        // 修改个人信息
        if (iStudentModel != null && mView.get() != null){
            iStudentModel.changeInfo(new IUserManageModel.OnLoaderListener() {
                @Override
                public void onComplete(Map<String, Object> map) {
                    mView.get().changeInfo(map);
                }

                @Override
                public void onErrMsg() {

                }
            });
        }
    }

    public void changePasswordFetch(){
        //修改密码
        if (iStudentModel != null && mView.get() != null){
            iStudentModel.changePassword(new IUserManageModel.OnLoaderListener() {
                @Override
                public void onComplete(Map<String, Object> map) {
                    mView.get().changePassword(map);
                }

                @Override
                public void onErrMsg() {

                }
            });
        }
    }

    public void getStudentInfo(String sid){
        iStudentModel.setStudentID(sid);
        iStudentModel.getStudentInfo(new IStudentModel.OnLoaderListener() {
            @Override
            public void onComplete(Map<String, Object> map) {
                mView.get().getStudentInfo(map);
            }

            @Override
            public void onErrMsg() {

            }
        });
    }
}
