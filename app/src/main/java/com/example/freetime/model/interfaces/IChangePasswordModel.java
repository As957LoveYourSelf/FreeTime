package com.example.freetime.model.interfaces;

public interface IChangePasswordModel extends IBaseModel{
    void setNewPassword(String psd, String uid) throws Exception;
    void changePsd(OnLoaderListener onLoaderListener) throws InterruptedException;
}
