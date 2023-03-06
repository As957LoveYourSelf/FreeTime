package com.example.freetime.fragment;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.freetime.CourseTableActivity;
import com.example.freetime.R;

import java.io.Serializable;
import java.util.Map;


public class FuncFragmentStu extends BaseFuncFragment {

    private static final String USERMSG = "usermsg";
    private View view;
    public FuncFragmentStu() {
        // Required empty public constructor
    }

    public static FuncFragmentStu newInstance(Map<String, String> userMsg) {
        FuncFragmentStu fragment = new FuncFragmentStu();
        if (userMsg != null){
            Bundle args = new Bundle();
            args.putSerializable(USERMSG, (Serializable) userMsg);
            fragment.setArguments(args);
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            userMsg = (Map<String, String>) getArguments().getSerializable(USERMSG);
            System.out.println(userMsg);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (this.view == null){
            view = inflater.inflate(R.layout.fragment_func_stu, container, false);
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn5.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), CourseTableActivity.class);
            intent.putExtra("utype", "student");
            intent.putExtra(USERMSG,(Serializable) userMsg);
            startActivity(intent);
        });
    }
}