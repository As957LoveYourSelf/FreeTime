package com.example.freetime;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.freetime.presenter.ChangeInfoPresenter;
import com.example.freetime.utils.ChangeInfoUtil;
import com.example.freetime.utils.SaveInfoUtils;
import com.example.freetime.view.IChangeInfoView;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ChangeInfoActivity extends BaseActivity<ChangeInfoPresenter, IChangeInfoView> implements IChangeInfoView {

    EditText agetext;
    EditText indroducetext;
    EditText emailtext;
    EditText phonetext;
    RadioGroup radioGroup;
    ImageView avatar;
    String sex;
    ImageButton confirm;
    ImageButton reset;
    RadioButton man;
    RadioButton woman;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_info);
        initView();
    }

    @Override
    protected ChangeInfoPresenter createPresenter() {
        return new ChangeInfoPresenter();
    }

    @Override
    public void showErrorMessage(String msg) {

    }

    @Override
    public void changeInfo(Map<String, Object> info) {
        if (info != null && Objects.equals(info.get("status"), "success")){
            Toast.makeText(this, "修改成功，下次登录生效！", Toast.LENGTH_SHORT).show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initView(){
        agetext = findViewById(R.id.user_age);
        emailtext = findViewById(R.id.user_email);
        indroducetext = findViewById(R.id.user_introduce);
        phonetext = findViewById(R.id.user_phone);
        radioGroup = findViewById(R.id.radio_sex);
        avatar = findViewById(R.id.user_avatar);
        man = findViewById(R.id.man_radio);
        woman = findViewById(R.id.woman_radio);

        String[] strings = ChangeInfoUtil.readInfo();
        agetext.setText(strings[0]);
        if (strings[1].equals("男")){
            radioGroup.check(man.getId());
        }else {
            radioGroup.check(woman.getId());
        }
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton bt = findViewById(group.getCheckedRadioButtonId());
                sex = bt.getText().toString();
            }
        });
        emailtext.setText(strings[2]);
        phonetext.setText(strings[3]);
        indroducetext.setText(strings[4]);
    }

    private void setButtonListener(){
        String uid = SaveInfoUtils.readInfo()[0];
        Map<String, Object> data = new HashMap<>();
        data.put("sex", sex);
        data.put("email", emailtext.getText().toString());
        data.put("age", Integer.parseInt(agetext.getText().toString()));
        data.put("phone", phonetext.getText().toString());
        data.put("introduce", indroducetext.getText().toString());
        confirm = findViewById(R.id.change_info_confirm);
        reset = findViewById(R.id.change_info_reset);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.fetch(uid, data);
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                initView();
            }
        });
    }
}