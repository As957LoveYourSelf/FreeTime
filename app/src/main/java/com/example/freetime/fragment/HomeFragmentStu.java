package com.example.freetime.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.freetime.R;

import java.io.Serializable;
import java.util.Map;

public class HomeFragmentStu extends Fragment {

    private static final String USERINFO = "userinfo";
    private View view;
    TextView uname;
    TextView cls;
    TextView college;
    TextView major;
    TextView school;
    TextView introduce;
    TextView uno;
    TextView email;
    TextView phone;
    TextView age;

    private Map<String, Object> userInfo;

    public HomeFragmentStu() {
    }

    public static HomeFragmentStu newInstance(Map<String, Object> userInfo) {
        HomeFragmentStu fragment = new HomeFragmentStu();
        if (userInfo != null){
            Bundle args = new Bundle();
            args.putSerializable(USERINFO, (Serializable) userInfo);
            fragment.setArguments(args);
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userInfo = (Map<String, Object>) getArguments().getSerializable(USERINFO);
            System.out.println(userInfo);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null){
            view = inflater.inflate(R.layout.fragment_home_stu, container, false);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        uname = view.findViewById(R.id.user_name);
        cls = view.findViewById(R.id.user_class);
        college = view.findViewById(R.id.user_collage);
        major = view.findViewById(R.id.user_major);
        school = view.findViewById(R.id.user_school);
        introduce = view.findViewById(R.id.user_introduce);
        uno = view.findViewById(R.id.user_uno);
        email = view.findViewById(R.id.user_email);
        phone = view.findViewById(R.id.user_phone);
        age = view.findViewById(R.id.user_age);
        if (this.userInfo != null){
            uname.setText((String)this.userInfo.get("name"));
            cls.setText((String)this.userInfo.get("class"));
            college.setText((String)this.userInfo.get("college"));
            major.setText((String)this.userInfo.get("major"));
            school.setText("岭南师范学院");
            uno.setText((String)this.userInfo.get("no"));
            email.setText((String)this.userInfo.get("email"));
            phone.setText((String)this.userInfo.get("phone"));
            Integer a = (Integer) this.userInfo.get("age");
            age.setText(a != null?String.valueOf(a):"无");
            Toast.makeText(getContext(), "欢迎您，"+uname.getText().toString()+"同学", Toast.LENGTH_SHORT).show();
        }

    }
}