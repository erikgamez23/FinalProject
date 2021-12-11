package com.c323FinalProject.egameztatclend.ExerciseFragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.c323FinalProject.egameztatclend.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class ExercisesFragment extends Fragment {
    ArrayList<Exercise> exerciseList;
    ArrayAdapter<Exercise> ExerciseArrayAdapter;

    ListView exerciseListView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_exercises, container, false);
        initializeViews(v);

        return v;
    }

    private void initializeViews(View v) {
        FloatingActionButton m = v.findViewById(R.id.floatingActionButton);
        m.setBackgroundDrawable(getContext().getResources().getDrawable(android.R.drawable.btn_plus));

        m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //switch too add new AddExerciseFragment
                Log.i("BUTTON", "ADD_EXERCISE");
            }
        });
        exerciseListView = v.findViewById(R.id.exerciseListView);
        exerciseList = new ArrayList<>();
        exerciseList.add(new Exercise("Push Ups","ph.com"));
        exerciseList.add(new Exercise("Sit Ups","xv.com"));
        exerciseList.add(new Exercise("Pull Ups","xn.com"));
        ExerciseArrayAdapter = new ExerciseArrayAdapter();
        exerciseListView.setAdapter(ExerciseArrayAdapter);
    }
    /**
     * Adapter for ListView holding movies and inflating movie_layout views
     */
    private class ExerciseArrayAdapter extends ArrayAdapter<Exercise> {
        public ExerciseArrayAdapter() {
            super(getActivity(), R.layout.exercise_list_layout, exerciseList);
        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View view = convertView;
            if(view == null) {
                view = getLayoutInflater().inflate(R.layout.exercise_list_layout, parent, false);
            }
            Exercise exercise = exerciseList.get(position);
            //setting imageview
            ImageView image = view.findViewById(R.id.exercise_list_imageView);
            TextView name = view.findViewById(R.id.exerciseNameTextView);
            name.setText(exercise.get_name());
            //TODO set Image to whatver image is used for exercise
            image.setImageResource(R.drawable.muskel);
            //TODO set OnClickListener to Exercise List Fragment
            return view;
        }
    }
}