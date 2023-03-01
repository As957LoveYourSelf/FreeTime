package com.example.freetime.model.interfaces;

import java.util.Map;

/**
 * 提供登录请求接口
 * */
public interface ILoginModel extends IBaseModel{
    void setUname(String uname);
    void setPassword(String psd);
    void goLogin(OnLoaderListener onLoaderListener) throws InterruptedException;
}
