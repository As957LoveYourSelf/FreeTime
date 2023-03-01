package com.example.freetime.model.interfaces;

public interface ICourseTableModel extends IBaseModel{
    void setUid(String uid, String utype);
    void getTable(OnLoaderListener onLoaderListener);
}
