package com.c323FinalProject.egameztatclend.DailyTrainingFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.c323FinalProject.egameztatclend.NavBarActivity;
import com.c323FinalProject.egameztatclend.R;

public class GetReadyFragment extends Fragment {
    public int counter = 5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_get_ready, container, false);

        TextView countdownTextView = v.findViewById(R.id.getReadyTimer);
        new CountDownTimer(5000, 1000){
            public void onTick(long millisUntilFinished){
                countdownTextView.setText(String.valueOf(counter));
                counter--;
            }
            public  void onFinish(){
                ((NavBarActivity) getActivity()).replaceFragments(GetReadyFragment.class);
            }
        }.start();
        return v;
    }
}