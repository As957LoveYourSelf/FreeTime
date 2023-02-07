package com.example.freetime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.freetime.presenter.CourseTablePresenter;
import com.example.freetime.view.ICourseTableView;

public class CourseTableActivity extends BaseActivity<CourseTablePresenter, ICourseTableView> implements ICourseTableView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_table);
    }

    @Override
    protected CourseTablePresenter createPresenter() {
        return null;
    }

    @Override
    public void showErrorMessage(String msg) {

    }
}