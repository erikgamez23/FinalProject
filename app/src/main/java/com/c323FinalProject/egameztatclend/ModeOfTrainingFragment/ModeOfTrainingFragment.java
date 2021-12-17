package com.c323FinalProject.egameztatclend.ModeOfTrainingFragment;

import static android.content.Context.ALARM_SERVICE;
import static android.content.Context.MODE_PRIVATE;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import com.c323FinalProject.egameztatclend.Alarm;
import com.c323FinalProject.egameztatclend.ExerciseFragments.MyExerciseDBHandler;
import com.c323FinalProject.egameztatclend.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.util.Date;

public class ModeOfTrainingFragment extends Fragment {

    MaterialButton setAlarm;
    TimePicker timePicker;
    MaterialButton saveOption;
    RadioGroup radioGroup;
    //Shared Preferences for changing mode
    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_mode_of_training, container, false);
        sharedPreferences = getContext().getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        initializeViews(v);
        return v;
    }

    /**
     * View v used as reference to fragment's views
     * @param v
     */
    private void initializeViews(View v) {
        setAlarm = v.findViewById(R.id.SetAlarmButton);
        setAlarm.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                int time = timePicker.getHour()*60;
                Intent i = new Intent(getActivity(), Alarm.class);
                AlarmManager am =(AlarmManager) getActivity().getSystemService(ALARM_SERVICE);
            }
        });
        timePicker = v.findViewById(R.id.timePicker);
        saveOption = v.findViewById(R.id.SaveOptionButton);
        saveOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                int radioButtonID = radioGroup.getCheckedRadioButtonId();
                String modeSet = "";
                switch (radioButtonID)
                {
                    case R.id.EasyButton:
                        editor.putInt("mode", 20);
                        modeSet = "easy";
                        break;
                    case R.id.MediumButton:
                        editor.putInt("mode", 30);
                        modeSet = "medium";
                        break;
                    case R.id.HardButton:
                        editor.putInt("mode", 50);
                        modeSet = "hard";
                        break;
                    case R.id.RippedButton:
                        editor.putInt("mode", 80);
                        modeSet = "ripped";
                        break;
                    default:
                        editor.putInt("mode", 20);
                        break;
                }
                if(radioButtonID != -1){
                    editor.apply();
                    Toast.makeText(getContext(), "Mode set to " + modeSet ,Toast.LENGTH_LONG).show();
                }
            }
        });
        radioGroup = v.findViewById(R.id.radioGroup);
    }
}