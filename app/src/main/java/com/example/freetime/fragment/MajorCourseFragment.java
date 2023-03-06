package com.example.freetime.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.freetime.R;
import com.example.freetime.adapter.CommonAdapter;
import com.example.freetime.adapter.ViewHolder;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class MajorCourseFragment extends Fragment {

    private static final String CSINFO = "csinfo";
    private View view;
    ListView listView;
    List<Map<String, Object>> data;

    public MajorCourseFragment() {
        // Required empty public constructor
    }

    public static MajorCourseFragment newInstance(List<Map<String, Object>> data) {
        MajorCourseFragment fragment = new MajorCourseFragment();
        if (data != null){
            Bundle args = new Bundle();
            args.putSerializable(CSINFO,(Serializable) data);
            fragment.setArguments(args);
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 获取数据
        if (getArguments() != null) {
            this.data = (List<Map<String, Object>>) getArguments().getSerializable(CSINFO);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null){
            view = inflater.inflate(R.layout.fragment_major_course, container, false);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // 显示数据
        if (data != null){
            System.out.println("MCS: "+data);
            listView = view.findViewById(R.id.mj_course_listview);
            listView.setAdapter(new CommonAdapter<Map<String, Object>>(getContext(), data, R.layout.item_view) {
                @Override
                public void convert(ViewHolder helper, Map<String, Object> item) {
                    helper.setText(R.id.item_cname, (String) item.get("cname"));
                    helper.setText(R.id.item_address, (String) item.get("address"));
                    helper.setText(R.id.item_time, (String) item.get("time"));
                }
            });
            Toast.makeText(getContext(), "获取专业课数据成功", Toast.LENGTH_SHORT).show();
        }
    }
}