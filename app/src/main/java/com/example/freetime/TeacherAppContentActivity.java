package com.example.freetime;

import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.freetime.presenter.TeacherPresenter;
import com.example.freetime.view.ITeacherView;

import java.util.Map;

public class TeacherAppContentActivity extends BaseActivity<TeacherPresenter, ITeacherView> implements ITeacherView {

    private Fragment[] fragments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_content_teacher);

    }

    @Override
    protected TeacherPresenter createPresenter() {
        return null;
    }



    @Override
    public void showErrorMessage(String msg) {

    }

    @Override
    public void changeInfo(Map<String, Object> map) {
        // 根据后台的传值信息获取map里的内容
    }

    @Override
    public void changePassword(Map<String, Object> map) {
        // 根据后台的传值信息获取map里的内容
    }
}