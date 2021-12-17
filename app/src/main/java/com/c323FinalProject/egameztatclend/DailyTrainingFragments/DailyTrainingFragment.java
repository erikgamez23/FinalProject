package com.c323FinalProject.egameztatclend.DailyTrainingFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.c323FinalProject.egameztatclend.NavBarActivity;
import com.c323FinalProject.egameztatclend.R;
import com.google.android.material.button.MaterialButton;

public class DailyTrainingFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_daily_training, container, false);
        MaterialButton b = v.findViewById(R.id.startTrainingButton);
        b.setOnClickListener(new View.OnClickListener() {
            /**
             * Takes user through Daily Training Fragments
             * @param view
             */
            @Override
            public void onClick(View view) {
                ((NavBarActivity) getActivity()).replaceFragments(GetReadyFragment.class);
            }
        });
        return v;
    }
}