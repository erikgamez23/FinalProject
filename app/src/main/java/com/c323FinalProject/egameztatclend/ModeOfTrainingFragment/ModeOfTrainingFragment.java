package com.c323FinalProject.egameztatclend.ModeOfTrainingFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.c323FinalProject.egameztatclend.R;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

public class ModeOfTrainingFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_mode_of_training, container, false);
        initializeViews(v);
        return v;
    }

    private void initializeViews(View v) {
        //TODO set OnClick for set alarm on/off button

        //TODO set up timepicker to be able to set the alarm

        //TODO save option button

        //TODO radio
        RadioGroup radioGroup = v.findViewById(R.id.radioGroup);
    }
}