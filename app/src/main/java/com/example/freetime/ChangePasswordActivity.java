package com.example.freetime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.freetime.presenter.ChangePasswordPresenter;
import com.example.freetime.view.IChangePasswordView;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ChangePasswordActivity extends BaseActivity<ChangePasswordPresenter, IChangePasswordView> implements IChangePasswordView {

    EditText new_psd;
    EditText new_psd_2;
    ImageButton confirm;
    ImageButton channel;
    Map<String, String> usermsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        usermsg = (Map<String, String>) getIntent().getSerializableExtra("usermsg");
        initPage();
    }

    @Override
    protected ChangePasswordPresenter createPresenter() {
        return new ChangePasswordPresenter();
    }

    @Override
    public void showErrorMessage(String msg) {

    }

    private void initPage(){
        new_psd = findViewById(R.id.new_psw);
        new_psd_2 = findViewById(R.id.new_psw_2);
        confirm = findViewById(R.id.change_psw_confirm);
        channel = findViewById(R.id.change_psw_reset);


        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = null;
                switch (v.getId()){
                    case R.id.change_psw_confirm:
                        // 提交修改申请
                        if (new_psd.getText().toString().equals("")){
                            toast = Toast.makeText(ChangePasswordActivity.this, "请输入新密码", Toast.LENGTH_SHORT);
                            break;
                        }
                        if (new_psd_2.getText().toString().equals("")){
                            toast = Toast.makeText(ChangePasswordActivity.this, "请再次输入新密码", Toast.LENGTH_SHORT);
                            break;
                        }
                        if (!new_psd.getText().toString().equals(new_psd_2.getText().toString())){
                            toast = Toast.makeText(ChangePasswordActivity.this, "请确保两次输入的密码一样", Toast.LENGTH_SHORT);
                            break;
                        }
                        presenter.fetch(new_psd.getText().toString(), usermsg.get("uid"));
                        break;
                    case R.id.change_psw_reset:
                        // 重置
                        new_psd.setText("");
                        new_psd_2.setText("");
                        break;
                }
                if (toast != null){
                    toast.show();
                }
            }
        };

    }

    @Override
    public void changePsd(Map<String, Object> info) {
        Map<String, String> msg = new HashMap<>();
        msg.put("nonEmail", "未绑定邮箱，请先绑定");
        msg.put("psdSame", "傻了吧，和原密码一样");
        msg.put("identifyEmail", "请前往邮箱完成修改密码验证");
        msg.put("postEmailError", "邮件发送错误QVQ");
        Toast toast = Toast.makeText(this, msg.get((String) info.get("status")), Toast.LENGTH_SHORT);
        toast.show();
    }
}