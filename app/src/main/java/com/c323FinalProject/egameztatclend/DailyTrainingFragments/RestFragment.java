package com.c323FinalProject.egameztatclend.DailyTrainingFragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.c323FinalProject.egameztatclend.ExerciseFragments.Exercise;
import com.c323FinalProject.egameztatclend.NavBarActivity;
import com.c323FinalProject.egameztatclend.R;

import java.util.ArrayList;


public class RestFragment extends Fragment {

    CountDownTimer countDownTimer;
    TextView countDownTextView;
    int counter;

    ArrayList<Exercise> exercises;
    int index;



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ProgressBarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RestFragment newInstance(ArrayList<Exercise> exercises, int index) {
        RestFragment fragment = new RestFragment();
        Bundle args = new Bundle();
        args.putSerializable("exercises", exercises);
        args.putInt("index", index);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            exercises = (ArrayList<Exercise>) getArguments().getSerializable("exercises");
            index = getArguments().getInt("index");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_rest, container, false);
        counter = 10;

        countDownTextView = v.findViewById(R.id.textViewCountDownTimerRest);

        Button skipButton = v.findViewById(R.id.buttonSkip);

        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NavBarActivity) requireActivity()).replaceProgressBarFragment(exercises,index+1);
            }
        });


        countDownTimer = new CountDownTimer(10000, 1000){
            public void onTick(long millisUntilFinished){
                countDownTextView.setText(String.valueOf(counter));
                counter--;
            }

            public void onFinish(){
                ((NavBarActivity) requireActivity()).replaceProgressBarFragment(exercises,index+1);
            }
        }.start();

        return v;
    }


}