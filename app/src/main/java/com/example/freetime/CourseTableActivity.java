package com.example.freetime;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.Toast;

import com.example.freetime.adapter.CourseTableFragmentAdapter;
import com.example.freetime.adapter.StudentFragmentPageAdapter;
import com.example.freetime.fragment.MajorCourseFragment;
import com.example.freetime.fragment.PublicCourseFragment;
import com.example.freetime.presenter.CourseTablePresenter;
import com.example.freetime.view.ICourseTableView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CourseTableActivity extends BaseActivity<CourseTablePresenter, ICourseTableView> implements ICourseTableView {

    ViewPager2 viewPager;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_table);
        String utype = getIntent().getStringExtra("utype");
        Map<String, String> usermsg = (Map<String, String>) getIntent().getSerializableExtra("usermsg");
        System.out.println("Activity Info msg: "+usermsg);
        System.out.println("Activity Info utype: "+utype);
        if (usermsg != null){
            try {
                Toast.makeText(this, "获取课表信息中...", Toast.LENGTH_SHORT).show();
                presenter.fetch(usermsg.get("uid"), utype);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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
        if (info != null){
            initPage((List<Map<String, Object>>) info.get("public_course"), (List<Map<String, Object>>) info.get("major_course"));
        }
    }

    private void initPage(List<Map<String, Object>> pcs, List<Map<String, Object>> mcs){
        bottomNavigationView = findViewById(R.id.table_bottomNavigationView);
        viewPager = findViewById(R.id.cstable_viewpage);
        List<Fragment> fragments = new ArrayList<>();
        bottomNavigationView.setOnItemSelectedListener(item -> {
            viewPager.setCurrentItem(item.getOrder());
            return true;
        });
        fragments.add(MajorCourseFragment.newInstance(mcs));
        fragments.add(PublicCourseFragment.newInstance(pcs));
        CourseTableFragmentAdapter adapter = new CourseTableFragmentAdapter(getSupportFragmentManager(), getLifecycle(), fragments);
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