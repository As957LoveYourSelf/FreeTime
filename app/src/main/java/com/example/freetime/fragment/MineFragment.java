package com.example.freetime.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.freetime.ChangeInfoActivity;
import com.example.freetime.ChangePasswordActivity;
import com.example.freetime.FaceImportActivity;
import com.example.freetime.R;

import java.io.Serializable;
import java.util.Map;

public class MineFragment extends Fragment {

    private static final String USERMSG = "usermsg";
    private View view;
    ImageButton btn1;
    ImageButton btn2;
    ImageButton btn3;

    public MineFragment() {
        // Required empty public constructor
    }

    public static MineFragment newInstance(Map<String, String> userMsg) {
        MineFragment fragment = new MineFragment();
        Bundle args = new Bundle();
        args.putSerializable(USERMSG, (Serializable) userMsg);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (this.view == null){
            view = inflater.inflate(R.layout.fragment_mine, container, false);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn1 = view.findViewById(R.id.change_password);
        btn2 = view.findViewById(R.id.change_info);
        btn3 = view.findViewById(R.id.face_import);

        btn1.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ChangePasswordActivity.class);
            Map<String, String> msg = (Map<String, String>) getArguments().getSerializable(USERMSG);
            intent.putExtra(USERMSG, (Serializable) msg);
            startActivity(intent);
        });

        btn2.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ChangeInfoActivity.class);
            Map<String, String> msg = (Map<String, String>) getArguments().getSerializable(USERMSG);
            intent.putExtra(USERMSG, (Serializable) msg);
            startActivity(intent);
        });

        btn3.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), FaceImportActivity.class);
            Map<String, String> msg = (Map<String, String>) getArguments().getSerializable(USERMSG);
            intent.putExtra(USERMSG, (Serializable) msg);
            startActivity(intent);
        });
    }
}