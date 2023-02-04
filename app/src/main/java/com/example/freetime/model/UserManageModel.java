package com.example.freetime.model;

import com.example.freetime.model.interfaces.IUserManageModel;

import java.util.Map;

public class UserManageModel implements IUserManageModel {

    @Override
    public void changeInfo(OnLoaderListener onloaderListener) {
        // 与后台交互，完成用户信息修改业务
    }

    @Override
    public void changePassword(OnLoaderListener onLoaderListener) {
        // 与后台交互，完成密码修改业务

    }

    private Map<String ,Object> getChangedUser(){

        return null;
    }

    private Map<String ,Object> getChangedPassword(){
        return null;
    }
}
