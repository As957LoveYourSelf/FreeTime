package com.example.freetime.model;

import com.example.freetime.beans.ResponseBean;
import com.example.freetime.model.interfaces.ILoginModel;
import com.example.freetime.network.RetrofitClient;
import com.example.freetime.network.service.LoginService;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.rxjava3.functions.Consumer;

public class LoginModel implements ILoginModel {

    /**
     *  发送数据至后台，获取登录状态信息
     */

    private String uname;
    private String psd;

    public LoginModel(){
    }

    @Override
    public void goLogin(OnLoaderListener onLoaderListener) throws InterruptedException {
        // 获取真实数据
        onLoaderListener.onMapComplete(getInfo());
    }

    @Override
    public void setUname(String uname) {
        this.uname = uname;
    }

    @Override
    public void setPassword(String psd) {
        this.psd = psd;
    }

    private Map<String, Object> getInfo() throws InterruptedException {
        Map<String, Object> response = new HashMap<>();
        // 具体获取数据业务实现，保存Token
        if (this.uname != null && this.psd != null){
            LoginService service = RetrofitClient.getInstance().getService(LoginService.class);
            Map<String, String> map = new HashMap<>();
            map.put("uname", this.uname);
            map.put("psd", this.psd);
            service.post(map).subscribe(new Consumer<ResponseBean<Map<String, Object>>>() {
                @Override
                public void accept(ResponseBean<Map<String, Object>> mapResponseBean) throws Throwable {
                    Map<String, Object> info = mapResponseBean.getData();
                    if (info.get("loginState").equals("success")) {
                        response.put("postType", "loginSuccess");
                        response.put("utype", info.get("utype"));
                        response.put("userToken", info.get("userToken"));
                    } else {
                        response.put("postType", info.get("loginState"));
                    }
                }
            });
        }
        return response;
    }
}
