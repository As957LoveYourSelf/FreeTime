package com.example.freetime.model;

import com.example.freetime.model.interfaces.ILoginModel;
import com.example.freetime.network.RetrofitClient;
import com.example.freetime.network.service.LoginService;
import com.example.freetime.utils.Md5Util;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LoginModel implements ILoginModel {

    /**
     *  发送数据至后台，获取登录状态信息
     */

    private String uname;
    private String psd;


    public LoginModel(String uname, String psd) throws Exception {
        this.uname = uname;
        this.psd = Md5Util.encodeByMd5(psd);
    }

    @Override
    public void goLogin(OnLoaderListener onLoaderListener) {
        // 获取真实数据
        try {
            // 具体获取数据业务实现，保存Token
            if (this.uname != null && this.psd != null){
                LoginService service = RetrofitClient.getInstance().getService(LoginService.class);
                Map<String, String> map = new HashMap<>();
                Map<String, Object> response = new HashMap<>();
                map.put("id", this.uname);
                map.put("psd", this.psd);
                service.post(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mapResponseBean -> {
                    Map<String, Object> info = mapResponseBean.getData();
//                    System.out.println(info);
                    if (info.get("loginState").equals("success")) {
                        response.put("postType", "loginSuccess");
                        response.put("utype", info.get("utype"));
                        response.put("userToken", info.get("usertoken"));
                    } else {
                        response.put("postType", info.get("loginState"));
                    }
                    onLoaderListener.onMapComplete(response);
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
