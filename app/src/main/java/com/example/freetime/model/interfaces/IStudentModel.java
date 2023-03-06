package com.example.freetime.model.interfaces;

import java.util.Map;

public interface IStudentModel extends IUserManageModel{
    void getStudentInfo(OnLoaderListener onLoaderListener) throws InterruptedException;
}
