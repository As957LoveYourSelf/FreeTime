package com.example.freetime.view;


import java.util.Map;

public interface IUserManageView extends IBaseView {
    void changeInfo(Map<String, Object> map);
    void changePassword(Map<String, Object> map);
}
