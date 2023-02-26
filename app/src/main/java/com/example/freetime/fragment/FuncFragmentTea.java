package com.example.freetime.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.freetime.FaceSignActivity;
import com.example.freetime.R;

public class FuncFragmentTea extends BaseUserFragment {

    private View view;
    ImageButton btn6;

    public FuncFragmentTea() {
        // Required empty public constructor
    }

    public static FuncFragmentTea newInstance() {
        return new FuncFragmentTea();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (this.view == null){
            view = inflater.inflate(R.layout.fragment_func_tea, container, false);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn6 = view.findViewById(R.id.face_sign);

        btn6.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), FaceSignActivity.class);
            startActivity(intent);
        });
    }
}