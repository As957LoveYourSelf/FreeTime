package com.example.freetime.model.interfaces;

public interface IStudentModel extends IUserManageModel{
    void setStudentID(String id);
    void getStudentInfo(OnLoaderListener onLoaderListener);
}
