package com.example.freetime;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import com.example.freetime.presenter.LoginPagePresenter;
import com.example.freetime.view.ILoginPageView;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LoginActivity extends BaseActivity<LoginPagePresenter, ILoginPageView> implements ILoginPageView {

    ImageButton login_btn;
    EditText unameInput;
    EditText psdInput;
    boolean change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_btn = findViewById(R.id.login_btn_before);
        unameInput = findViewById(R.id.login_uname_input);
        psdInput = findViewById(R.id.login_password_input);
        change = false;
        login_btn.setOnClickListener(v -> {
            try {
                presenter.fetch(unameInput.getText().toString(), psdInput.getText().toString());
//                System.out.println(unameInput.getText().toString()+" "+psdInput.getText().toString());
            } catch (Exception e) {
                Toast toast = Toast.makeText(this, "error", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 重置输入栏以及按钮视图
        login_btn.setBackgroundResource(R.drawable.login_btn_before);
        change = false;
    }

    @Override
    protected LoginPagePresenter createPresenter() {
        return new LoginPagePresenter();
    }

    @Override
    public void showErrorMessage(String msg) {

    }

    @Override
    public void goLogin(Map<String, Object> info) {
        // 拿到后端Json数据以后处理
        // 定义拿到数据的错误类型
        System.out.println("INFO: "+info);
        Map<String ,String> msg = new HashMap<>();
        msg.put("psdUnCheck", "密码错误");
        msg.put("unameUnCheck", "用户名错误");
        msg.put("posterror","请求错误QVQ");
        msg.put("loginSuccess", "登录成功");
        Toast toast = Toast.makeText(this, msg.get((String) info.get("postType")), Toast.LENGTH_SHORT);
        toast.show();

        if (Objects.equals((String) info.get("postType"), "loginSuccess"))
        {
            String uid = unameInput.getText().toString();
            String utype = (String) info.get("utype");
            String userToken = (String) info.get("userToken");
            if (Objects.equals(utype, "student")){
                // 跳转至学生首页
                Intent intent = new Intent(this, StudentAppContentActivity.class);
                intent.putExtra("userToken", (Serializable) userToken);
                intent.putExtra("uid", uid);
                startActivity(intent);
            }
            if (Objects.equals(utype, "teacher")){
                // 跳转至教师首页
                Intent intent = new Intent(this, TeacherAppContentActivity.class);
                intent.putExtra("userToken", (Serializable) userToken);
                intent.putExtra("uid", uid);
                startActivity(intent);
            }
        }
    }

}