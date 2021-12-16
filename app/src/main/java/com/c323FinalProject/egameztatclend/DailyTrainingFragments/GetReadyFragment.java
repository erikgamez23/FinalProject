package com.c323FinalProject.egameztatclend.DailyTrainingFragments;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.c323FinalProject.egameztatclend.ExerciseFragments.Exercise;
import com.c323FinalProject.egameztatclend.ExerciseFragments.MyExerciseDBHandler;
import com.c323FinalProject.egameztatclend.NavBarActivity;
import com.c323FinalProject.egameztatclend.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class GetReadyFragment extends Fragment {
    public int counter = 5;
    CountDownTimer countDownTimer;

    private ArrayList<Exercise> exercises;
    MyExerciseDBHandler dbHandler;
    private int index;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_get_ready, container, false);
        index = 0;
        exercises = new ArrayList<>();
        String s = encodeTobase64(BitmapFactory.decodeResource(getContext().getResources(),R.drawable.muskel));

        exercises.add(new Exercise("Push Ups",s));
        exercises.add(new Exercise("Pull Ups", s));
        exercises.add(new Exercise("Sit Ups",s));

        addExercisesDB();

        TextView countdownTextView = v.findViewById(R.id.getReadyTimer);
        countDownTimer = new CountDownTimer(5000, 1000){
            public void onTick(long millisUntilFinished){
                countdownTextView.setText(String.valueOf(counter));
                counter--;
            }

            public void onFinish(){
                ((NavBarActivity) requireActivity()).replaceProgressBarFragment(exercises,index);
            }
        }.start();
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        countDownTimer.cancel();
    }

    /**
     * Method to encode an image bitmap to be able to store it in SharedPreferences
     * along with the user's profile.
     * @param image
     * @return
     */
    public String encodeTobase64(Bitmap image) {
        Bitmap immage = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

        Log.d("Image Log:", imageEncoded);
        return imageEncoded;
    }

    private void addExercisesDB() {
        dbHandler = new MyExerciseDBHandler(getActivity(), null, null, 1);
        Cursor res = dbHandler.getAllData();
        if (res.getCount() == 0) {
        } else {
            while (res.moveToNext()) {
                Exercise temp = new Exercise();
                temp.set_name(res.getString(0));
                exercises.add(temp);
            }
        }
    }
}