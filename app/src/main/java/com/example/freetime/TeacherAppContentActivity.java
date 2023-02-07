package com.example.freetime;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.freetime.adapter.StudentFragmentPageAdapter;
import com.example.freetime.adapter.TeacherFragmentPageAdapter;
import com.example.freetime.fragment.FuncFragmentStu;
import com.example.freetime.fragment.FuncFragmentTea;
import com.example.freetime.fragment.HomeFragment;
import com.example.freetime.fragment.MineFragment;
import com.example.freetime.presenter.TeacherPresenter;
import com.example.freetime.view.ITeacherView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TeacherAppContentActivity extends BaseActivity<TeacherPresenter, ITeacherView> implements ITeacherView {

    ViewPager2 viewPager;
    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_content_teacher);
    }
    private void initPage(){
        bottomNavigationView = findViewById(R.id.teacher_bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                viewPager.setCurrentItem(item.getOrder());
                return true;
            }
        });

        // viewPage2 fragment manage
        viewPager = findViewById(R.id.teacher_viewpage);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(HomeFragment.newInstance("首页"));
        fragments.add(FuncFragmentTea.newInstance("功能"));
        fragments.add(MineFragment.newInstance("我的"));
        TeacherFragmentPageAdapter adapter = new TeacherFragmentPageAdapter(getSupportFragmentManager(), getLifecycle(), fragments);
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
    @Override
    protected TeacherPresenter createPresenter() {
        return new TeacherPresenter();
    }



    @Override
    public void showErrorMessage(String msg) {

    }

    @Override
    public void getUserInfo(Map<String, Object> info) {

    }
}