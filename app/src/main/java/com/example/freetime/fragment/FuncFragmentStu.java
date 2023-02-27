package com.example.freetime.fragment;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.freetime.R;


public class FuncFragmentStu extends BaseUserFragment {

    private View view;

    public FuncFragmentStu() {
        // Required empty public constructor
    }

    public static FuncFragmentStu newInstance() {
        return new FuncFragmentStu();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (this.view == null){
            view = inflater.inflate(R.layout.fragment_func_stu, container, false);
        }
        return view;
    }
}