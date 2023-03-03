package com.example.freetime.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.freetime.CourseTableActivity;
import com.example.freetime.R;
import com.example.freetime.RoomReservationActivity;
import com.example.freetime.StyleTransformActivity;
import com.example.freetime.SuperResolveActivity;
import com.example.freetime.WordDistinguishActivity;

public class BaseFuncFragment extends Fragment {
    ImageButton btn1;
    ImageButton btn2;
    ImageButton btn3;
    ImageButton btn4;
    ImageButton btn5;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn1 = view.findViewById(R.id.image_super_resolution);
        btn2 = view.findViewById(R.id.word_distinguish);
        btn3 = view.findViewById(R.id.style_transform);
        btn4 = view.findViewById(R.id.class_reservation);
        btn5 = view.findViewById(R.id.table_detail);

        btn1.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), SuperResolveActivity.class);
            startActivity(intent);
        });

        btn2.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), WordDistinguishActivity.class);
            startActivity(intent);
        });

        btn3.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), StyleTransformActivity.class);
            startActivity(intent);
        });

        btn4.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), RoomReservationActivity.class);
            startActivity(intent);
        });

    }
}
