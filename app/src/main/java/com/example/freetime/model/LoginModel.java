package com.example.freetime.model;

import android.widget.Toast;

import com.example.freetime.LoginActivity;
import com.example.freetime.beans.BaseBean;
import com.example.freetime.model.interfaces.ILoginModel;
import com.example.freetime.network.RetrofitClient;
import com.example.freetime.network.service.LoginService;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.rxjava3.exceptions.OnErrorNotImplementedException;
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
        onLoaderListener.onComplete(getInfo());
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
            service.post(map).subscribe(new Consumer<BaseBean<Map<String, Object>>>() {
                @Override
                public void accept(BaseBean<Map<String, Object>> mapBaseBean) throws Throwable {
                    Map<String, Object> info = mapBaseBean.getData();
                    if (info.get("loginState").equals("success")) {
                        Map<String, String> usermap = (Map<String, String>) info.get("userInfo");
                        response.put("user", usermap);
                        response.put("token", info.get("usertoken"));
                    } else {
                        response.put("user", null);
                        response.put("errtype", info.get("loginState"));
                    }
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Throwable {
                    response.put("user", null);
                    response.put("errtype", "posterror");
                    throwable.printStackTrace();
                }
            });
        }
        System.out.println("Model return data: "+response);
        Thread.sleep(10);
        return response;
    }
}
