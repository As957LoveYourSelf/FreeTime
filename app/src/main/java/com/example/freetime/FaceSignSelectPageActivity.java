package com.example.freetime;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.freetime.presenter.FaceSignSelectPagePresenter;
import com.example.freetime.view.IFaceSignSelectPageView;
import com.wx.wheelview.adapter.ArrayWheelAdapter;
import com.wx.wheelview.widget.WheelView;
import com.wx.wheelview.widget.WheelViewDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FaceSignSelectPageActivity extends BaseActivity<FaceSignSelectPagePresenter, IFaceSignSelectPageView> implements IFaceSignSelectPageView {

    Button btn;
    Button start;
    TextView textView;
    TextView textView2;
    WheelView<String> wheelView_cls;
    WheelView<String> wheelView_cs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_sign_select_page);
        btn = findViewById(R.id.select_class_button);
        textView = findViewById(R.id.select_class_text);
        textView2 = findViewById(R.id.select_course_text);
        start = findViewById(R.id.sign_start);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (textView.getText() == null || textView.getText().equals("") || textView2.getText() == null || textView2.getText().equals("")){
                    Toast.makeText(FaceSignSelectPageActivity.this, "请选择签到班级以及任课课程", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(FaceSignSelectPageActivity.this, FaceSignActivity.class);
                    intent.putExtra("classname", textView.getText());
                    intent.putExtra("course", textView2.getText());
                    startActivity(intent);
                }
            }
        });

        View wheelview = LayoutInflater.from(this).inflate(R.layout.cls_choose, null);
        wheelView_cls = wheelview.findViewById(R.id.wheelview_class);
        wheelView_cs = wheelview.findViewById(R.id.wheelview_course);
        WheelView.WheelViewStyle style = new WheelView.WheelViewStyle();
        style.selectedTextSize = 16;
        style.textSize = 13;

        wheelView_cls.setWheelAdapter(new ArrayWheelAdapter(this));
        wheelView_cls.setSkin(WheelView.Skin.Holo);
        wheelView_cls.setStyle(style);

        wheelView_cs.setWheelAdapter(new ArrayWheelAdapter(this));
        wheelView_cs.setStyle(style);
        wheelView_cs.setSkin(WheelView.Skin.Holo);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(FaceSignSelectPageActivity.this)
                        .setView(wheelview)
                        .setPositiveButton("确认",
                                (dialogInterface, i) -> {
                                    textView.setText(wheelView_cls.getSelectionItem());
                                    textView2.setText(wheelView_cs.getSelectionItem());
                                })
                        .setNegativeButton("取消",null).create();
                alertDialog.show();
            }
        });
        presenter.fetch();
    }

    @Override
    protected FaceSignSelectPagePresenter createPresenter() {
        return new FaceSignSelectPagePresenter();
    }

    @Override
    public void getSignClasses(Map<String, Object> response) {
//        System.out.println(response);
        if (response != null){
            List<String> cls = (List<String>)response.get("cls");
            Map<String, List<String>> cs = (Map<String, List<String>>) response.get("cs");
            if (cls != null && cs!=null){
                wheelView_cls.setWheelData(cls);
                wheelView_cs.setWheelData(cs.get(wheelView_cls.getSelectionItem()));
            }
        }else {
            Toast.makeText(this, "班级数据为空!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showErrorMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}