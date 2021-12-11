package com.c323FinalProject.egameztatclend.DailyTrainingFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.c323FinalProject.egameztatclend.R;
import com.google.android.material.button.MaterialButton;

public class DailyTrainingFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_daily_training, container, false);
        MaterialButton b = v.findViewById(R.id.startTrainingButton);
        b.setIcon(getContext().getResources().getDrawable(android.R.drawable.btn_plus));
        b.setIconTint(null);
        return v;
    }

    //                new CountDownTimer(30000, 1000){
    //                    public void onTick(long millisUntilFinished){
    //                        textView.setText(String.valueOf(counter));
    //                        counter++;
    //                    }
    //                    public  void onFinish(){
    //                       textView.setText("FINISH!!");
    //                    }
    //                }.start();
}