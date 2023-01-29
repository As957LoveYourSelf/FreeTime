package com.example.freetime.model;

import com.example.freetime.entity.User;

public class LoginModel implements ILoginModel{

    /**
     *  发送数据至后台，获取登录状态信息
     */
    @Override
    public void goLogin(OnLoaderListener onLoaderListener) {
        // 获取真实数据
        onLoaderListener.onComplete(getUser());
    }


    private User getUser(){
        // 具体获取数据业务实现
        return null;
    }
}
