package com.example.freetime.model.interfaces;

import java.util.Map;

public interface IStudentModel extends IUserManageModel{
    void setStudentID(String id);
    void getStudentInfo(OnLoaderListener onLoaderListener);
}
