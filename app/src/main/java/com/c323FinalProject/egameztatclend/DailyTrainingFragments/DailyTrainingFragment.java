package com.c323FinalProject.egameztatclend.DailyTrainingFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.c323FinalProject.egameztatclend.R;

public class DailyTrainingFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_daily_training, container, false);
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