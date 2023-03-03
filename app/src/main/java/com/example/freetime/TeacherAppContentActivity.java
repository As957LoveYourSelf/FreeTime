package com.example.freetime;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.freetime.adapter.TeacherFragmentPageAdapter;
import com.example.freetime.fragment.FuncFragmentTea;
import com.example.freetime.fragment.HomeFragmentStu;
import com.example.freetime.fragment.HomeFragmentTea;
import com.example.freetime.fragment.MineFragment;
import com.example.freetime.presenter.TeacherPresenter;
import com.example.freetime.view.ITeacherView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeacherAppContentActivity extends BaseActivity<TeacherPresenter, ITeacherView> implements ITeacherView {

    ViewPager2 viewPager;
    BottomNavigationView bottomNavigationView;
    Map<String, Object> userinfo;
    String userToken;
    HashMap<String, String> userMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_content_teacher);
        String uid = getIntent().getStringExtra("uid");
        userToken = getIntent().getStringExtra("userToken");
        userMsg.put("uid", uid);
        userMsg.put("token", userToken);
        presenter.fetch(uid);
        initPage();
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
        fragments.add(HomeFragmentTea.newInstance(this.userinfo));
        fragments.add(FuncFragmentTea.newInstance(this.userMsg));
        fragments.add(MineFragment.newInstance(this.userMsg));
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
        this.userinfo = (Map<String, Object>) info.get("info");
    }
}