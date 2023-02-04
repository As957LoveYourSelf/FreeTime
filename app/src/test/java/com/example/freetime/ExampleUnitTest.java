package com.example.freetime;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.freetime.beans.BaseBean;
import com.example.freetime.network.RetrofitClient;
import com.example.freetime.network.service.LoginService;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.rxjava3.functions.Consumer;
import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);

        LoginService service = RetrofitClient.getInstance().getService(LoginService.class);
        Map<String, String> map = new HashMap<>();
        map.put("uname", "admin");
        map.put("psd", "e10adc3949ba59abbe56e057f20f883e");
        Map<String, Object> response = new HashMap<>();
        service.post(map).subscribe(new Consumer<BaseBean<Map<String, Object>>>() {
            @Override
            public void accept(BaseBean<Map<String, Object>> mapBaseBean) throws Throwable {
                Map<String, Object> info = mapBaseBean.getData();
                if (info.get("loginState").equals("success")){
                    Map<String, String> user = (Map<String, String>) info.get("userInfo");
                    response.put("user", user);
                    response.put("token", info.get("usertoken"));
                    System.out.println(user);
                }else {
                    response.put("user", null);
                    response.put("errtype", info.get("loginState"));
                }
                System.out.println(response);
            }
        });
        while (true){

        }
    }
}