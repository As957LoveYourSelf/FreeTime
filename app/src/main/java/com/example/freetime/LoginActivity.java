package com.example.freetime;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.freetime.presenter.LoginPagePresenter;
import com.example.freetime.utils.SaveInfoUtils;
import com.example.freetime.view.ILoginPageView;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LoginActivity extends BaseActivity<LoginPagePresenter, ILoginPageView> implements ILoginPageView {

    ImageButton login_btn;
    EditText unameInput;
    EditText psdInput;
    ProgressBar progressBar;

    boolean change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
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
        if (msg != null){
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void goLogin(Map<String, Object> info) {
        // 拿到后端Json数据以后处理
        // 定义拿到数据的错误类型
        String utype = (String) info.get("utype");

//        System.out.println("INFO: "+info);
        Map<String ,String> msg = new HashMap<>();
        msg.put("psdUnCheck", "密码错误");
        msg.put("unameUnCheck", "用户名错误");
        msg.put("posterror","请求错误QVQ");
        msg.put("loginSuccess", "登录成功");
        Toast toast = Toast.makeText(this, msg.get((String) info.get("postType")), Toast.LENGTH_SHORT);
        toast.show();

        if (Objects.equals((String) info.get("postType"), "loginSuccess"))
        {
            if (Objects.equals(utype, "student")){
                // 跳转至学生首页
                Intent intent = new Intent(this, StudentAppContentActivity.class);
                startActivity(intent);
            }
            else if (Objects.equals(utype, "teacher")){
                // 跳转至教师首页
                Intent intent = new Intent(this, TeacherAppContentActivity.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(this, "没有该权限！", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void initView(){
        login_btn = findViewById(R.id.login_btn_before);
        unameInput = findViewById(R.id.login_uname_input);
        psdInput = findViewById(R.id.login_password_input);
        change = false;
        login_btn.setOnClickListener(v -> {
            try {
//                Toast.makeText(this, "登录中...", Toast.LENGTH_SHORT).show();
                presenter.fetch(unameInput.getText().toString(), psdInput.getText().toString());
//                System.out.println(unameInput.getText().toString()+" "+psdInput.getText().toString());
            } catch (Exception e) {
                Toast toast = Toast.makeText(this, "error", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

}