package com.example.freetime.model.interfaces;

import java.util.Map;

/**
 * 提供登录请求接口
 * */
public interface ILoginModel extends IBaseModel{
    void goLogin(OnLoaderListener onLoaderListener) throws InterruptedException;
    void loginout();
}
