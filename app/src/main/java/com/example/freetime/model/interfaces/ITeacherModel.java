package com.example.freetime.model.interfaces;

import java.util.Map;

public interface ITeacherModel extends IUserManageModel{
    void setTno(String tno);
    void getTeacherInfo(OnLoaderListener onLoaderListener);
}
