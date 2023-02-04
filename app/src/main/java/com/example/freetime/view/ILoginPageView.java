package com.example.freetime.view;

import java.util.Map;

/**
 * 登录界面 UI逻辑，由 LoginActivity 实现该接口，用于显示对应的 UI
 */
public interface ILoginPageView extends IBaseView{
    void goLogin(Map<String, Object> map);
}
