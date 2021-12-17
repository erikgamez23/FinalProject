package com.c323FinalProject.egameztatclend.DailyTrainingFragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.c323FinalProject.egameztatclend.ExerciseFragments.Exercise;
import com.c323FinalProject.egameztatclend.NavBarActivity;
import com.c323FinalProject.egameztatclend.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProgressBarFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class ProgressBarFragment extends Fragment {

    ProgressBar progressBar;
    int progress;
    int maxProgress;
    TextView textViewName;
    ImageView imageView;
    Button button;

    Thread thread;
    boolean running;

    private Handler handler;


    private ArrayList<Exercise> exercises;
    private int index;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ProgressBarFragment.
     */
    public static ProgressBarFragment newInstance(ArrayList<Exercise> exercises, int index) {
        ProgressBarFragment fragment = new ProgressBarFragment();
        Bundle args = new Bundle();
        args.putSerializable("exercises", exercises);
        args.putInt("index", index);
        fragment.setArguments(args);
        return fragment;
    }

    public ProgressBarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            exercises = (ArrayList<Exercise>) getArguments().getSerializable("exercises");
            index = getArguments().getInt("index");
        }
        progress = 0;

        //TODO get current difficulty from db. Change 20 to whatever current difficulty is.
        SharedPreferences myPrefs = requireActivity().getPreferences(Context.MODE_PRIVATE);
        maxProgress = myPrefs.getInt("mode",20);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_progress_bar, container, false);

        progressBar = view.findViewById(R.id.progressBar);
        button = view.findViewById(R.id.buttonDailyExerciseDone);
        textViewName = view.findViewById(R.id.textViewDailyExerciseName);
        imageView = view.findViewById(R.id.imageViewDailyExercise);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(index == exercises.size() - 1){
                    Toast.makeText(getContext(),"Finished your daily challenge", Toast.LENGTH_SHORT).show();
                    running = false;
                    ((NavBarActivity) requireActivity()).removeFragment(ProgressBarFragment.this);
                } else {
                    running = false;
                    ((NavBarActivity) requireActivity()).replaceRestFragment(exercises, index);
                }
            }
        });

        Bitmap bitmap;

        if(exercises.get(index).get_bitmap() != null){
            bitmap = decodeBase64(exercises.get(index).get_bitmap());
        } else {
            bitmap = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.muskel);
        }

        imageView.setImageBitmap(Bitmap.createScaledBitmap(bitmap,300,300,false));

        textViewName.setText(exercises.get(index).get_name());


        progressBar.setMax(maxProgress);

        handler = new Handler();

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (progress <= maxProgress) {
                    progress++;

                    if(!running) return;

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progress);
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (index == exercises.size() - 1) {   //if this isn't the last exercise...
                    try {
                        Toast.makeText(getContext(), "Finished your daily challenge", Toast.LENGTH_SHORT).show();

                        //TODO add data to DataBase

                        ((NavBarActivity) requireActivity()).removeFragment(ProgressBarFragment.this);
                    } catch(Exception e){
                        e.printStackTrace();
                    }
                } else {                            //if this is the last exercise...
                    try {
                        ((NavBarActivity) requireActivity()).replaceRestFragment(exercises, index);
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });

        running = true;

        thread.start();

        return view;
    }

    /**
     * Method used to decode base64 from SharedPrefs into bitmap.
     * @param input
     * @return
     */
    public Bitmap decodeBase64(String input) {
        byte[] decodedByte = Base64.decode(input, 0);
        return BitmapFactory
                .decodeByteArray(decodedByte, 0, decodedByte.length);
    }
}