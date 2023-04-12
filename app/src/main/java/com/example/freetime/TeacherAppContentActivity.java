package com.example.freetime;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.freetime.adapter.TeacherFragmentPageAdapter;
import com.example.freetime.fragment.FuncFragmentTea;
import com.example.freetime.fragment.HomeFragmentStu;
import com.example.freetime.fragment.HomeFragmentTea;
import com.example.freetime.fragment.MineFragment;
import com.example.freetime.presenter.TeacherPresenter;
import com.example.freetime.utils.SaveInfoUtils;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_content_teacher);
        presenter.fetch(SaveInfoUtils.readInfo()[0]);
    }
    @Override
    protected TeacherPresenter createPresenter() {
        return new TeacherPresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showErrorMessage(String msg) {
        if (msg != null){
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getUserInfo(Map<String, Object> info) {
        if (info != null){
            initPage((Map<String, Object>)info.get("info"));
        }
        else {
            Toast.makeText(this, "空数据！", Toast.LENGTH_SHORT).show();
        }
    }


    private long firstTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        long secondTime = System.currentTimeMillis();
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (secondTime - firstTime < 2000) {
                presenter.loginout();
                onDestroy();
            } else {
                Toast.makeText(TeacherAppContentActivity.this, "再点一次退出程序", Toast.LENGTH_SHORT).show();
                firstTime = System.currentTimeMillis();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void initPage(Map<String, Object> userinfo){
//        System.out.println(userinfo);
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
        fragments.add(HomeFragmentTea.newInstance(userinfo));
        fragments.add(FuncFragmentTea.newInstance());
        fragments.add(MineFragment.newInstance());
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
}