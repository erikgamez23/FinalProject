package com.c323FinalProject.egameztatclend.ExerciseFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.c323FinalProject.egameztatclend.DailyTrainingFragments.GetReadyFragment;
import com.c323FinalProject.egameztatclend.NavBarActivity;
import com.c323FinalProject.egameztatclend.R;
import com.google.android.material.button.MaterialButton;


public class SingleExercise extends Fragment {
    TextView countdown;
    public int counter = 20;
    CountDownTimer countDownTimer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_single_exercise, container, false);
        initializeViews(v);
        return v;
    }

    private void initializeViews(View v) {
        countdown = v.findViewById(R.id.singleTimerTextView);
        MaterialButton startDoneButton = v.findViewById(R.id.startDoneButton);
        startDoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((startDoneButton.getText().equals("Start"))) {
                    countDownTimer = new CountDownTimer(20000, 1000){
                        public void onTick(long millisUntilFinished){
                            countdown.setText(String.valueOf(counter));
                            counter--;
                        }

                        public  void onFinish(){
                            ((NavBarActivity) getActivity()).replaceFragments(ExercisesFragment.class);
                        }
                    }.start();
                    Log.i("BUTTON", "EXERCISES");
                    startDoneButton.setText("Done");
                }
                else {
                    ((NavBarActivity) getActivity()).replaceFragments(ExercisesFragment.class);
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        countDownTimer.cancel();
    }
}