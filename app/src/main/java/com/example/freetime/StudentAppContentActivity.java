package com.example.freetime;

import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.freetime.adapter.StudentFragmentPageAdapter;
import com.example.freetime.fragment.FuncFragmentStu;
import com.example.freetime.fragment.HomeFragmentStu;
import com.example.freetime.fragment.MineFragment;
import com.example.freetime.presenter.StudentPresenter;
import com.example.freetime.utils.SaveInfoUtils;
import com.example.freetime.view.IStudentView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentAppContentActivity extends BaseActivity<StudentPresenter, IStudentView> implements IStudentView {

    ViewPager2 viewPager;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_app_content);
        String[] strings = SaveInfoUtils.readInfo();
        try {
            presenter.fetch(strings[0]);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected StudentPresenter createPresenter() {
        return new StudentPresenter();
    }

    @Override
    public void showErrorMessage(String msg) {

    }

    @Override
    public void getUserInfo(Map<String, Object> info) {
//        System.out.println("StuInfo: "+info);
        if (info != null){
            initPage((Map<String, Object>) info.get("info"));
        }
    }
    private void initPage(Map<String, Object> info){
        bottomNavigationView = findViewById(R.id.student_bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            viewPager.setCurrentItem(item.getOrder());
            return true;
        });

        // viewPage2 fragment manage
        viewPager = findViewById(R.id.student_viewpage);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(HomeFragmentStu.newInstance(info));
        fragments.add(FuncFragmentStu.newInstance());
        fragments.add(MineFragment.newInstance());
        StudentFragmentPageAdapter adapter = new StudentFragmentPageAdapter(getSupportFragmentManager(), getLifecycle(), fragments);
        viewPager.setAdapter(adapter);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }
}