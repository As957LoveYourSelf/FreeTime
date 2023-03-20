package com.example.freetime;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.freetime.presenter.FaceSignSelectPagePresenter;
import com.example.freetime.view.IFaceSignSelectPageView;
import com.wx.wheelview.widget.WheelViewDialog;

import java.util.ArrayList;
import java.util.List;

public class FaceSignSelectPageActivity extends BaseActivity<FaceSignSelectPagePresenter, IFaceSignSelectPageView> implements IFaceSignSelectPageView {

    Button btn;
    Button start;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_sign_select_page);
        btn = findViewById(R.id.select_class_button);
        textView = findViewById(R.id.select_class_text);
        start = findViewById(R.id.sign_start);
        presenter.fetch();
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (textView.getText() == null || textView.getText().equals("")){
                    Toast.makeText(FaceSignSelectPageActivity.this, "请选择签到班级", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(FaceSignSelectPageActivity.this, FaceSignActivity.class);
                    intent.putExtra("classname", textView.getText());
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    protected FaceSignSelectPagePresenter createPresenter() {
        return new FaceSignSelectPagePresenter();
    }

    @Override
    public void getSignClasses(List<Object> response) {
        System.out.println(response);
        if (response != null){
            List<String> list = new ArrayList<>();
            for (Object o:response){
                list.add((String) o);
            }
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    WheelViewDialog dialog = new WheelViewDialog(FaceSignSelectPageActivity.this);
                    dialog.setTitle("选择签到班级")
                            .setItems(list).setButtonText("确定")
                            .setDialogStyle(Color.parseColor("#6699ff"))
                            .setOnDialogItemClickListener(new WheelViewDialog.OnDialogItemClickListener() {
                                @Override
                                public void onItemClick(int i, String s) {
                                    textView.setText(s);
                                }
                            })
                            .setCount(5).show();
                }
            });
        }else {
            Toast.makeText(this, "班级数据为空!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showErrorMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}