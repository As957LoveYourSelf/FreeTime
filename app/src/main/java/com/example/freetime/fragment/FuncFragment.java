package com.example.freetime.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.freetime.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FuncFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FuncFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_TITLE = "title";
    private View view;

    // TODO: Rename and change types of parameters
    private String mTextTitle;

    public FuncFragment() {
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
    public static FuncFragment newInstance(String param1) {
        FuncFragment fragment = new FuncFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTextTitle = getArguments().getString(ARG_TITLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (this.view == null){
            view = inflater.inflate(R.layout.fragment_func, container, false);
        }
        return view;
    }
}