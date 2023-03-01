package com.example.freetime.model.interfaces;

import com.example.freetime.beans.User;

import java.util.Map;

public interface IChangeInfoModel extends IBaseModel{
    void setNewInfo(User newInfo);
    void changeInfo(OnLoaderListener onLoaderListener);
}
