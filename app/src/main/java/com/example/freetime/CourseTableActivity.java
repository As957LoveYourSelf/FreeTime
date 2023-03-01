package com.example.freetime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.freetime.presenter.CourseTablePresenter;
import com.example.freetime.view.ICourseTableView;

import java.util.Map;

public class CourseTableActivity extends BaseActivity<CourseTablePresenter, ICourseTableView> implements ICourseTableView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_table);
    }

    @Override
    protected CourseTablePresenter createPresenter() {
        return new CourseTablePresenter();
    }

    @Override
    public void showErrorMessage(String msg) {

    }

    @Override
    public void getTable(Map<String, Object> info) {

    }
}