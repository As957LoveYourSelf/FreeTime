package com.example.freetime.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.freetime.R;

import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FuncFragmentStu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FuncFragmentStu extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_TITLE = "title";
    private View view;
    Map<String, Object> studentInfo;
    ImageButton btn1;
    ImageButton btn2;
    ImageButton btn3;
    ImageButton btn4;
    ImageButton btn5;


    // TODO: Rename and change types of parameters
    private String mTextTitle;

    public FuncFragmentStu() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment FuncFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FuncFragmentStu newInstance(String param1) {
        FuncFragmentStu fragment = new FuncFragmentStu();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO: 设置各个功能监听事件
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTextTitle = getArguments().getString(ARG_TITLE);
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

}