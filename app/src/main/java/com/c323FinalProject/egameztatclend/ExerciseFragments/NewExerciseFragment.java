package com.c323FinalProject.egameztatclend.ExerciseFragments;

import android.os.Bundle;

import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.c323FinalProject.egameztatclend.NavBarActivity;
import com.c323FinalProject.egameztatclend.R;
import com.google.android.material.button.MaterialButton;

import org.w3c.dom.Text;

public class NewExerciseFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_new_exercise, container, false);
        initializeViews(v);
        return v;
    }

    private void initializeViews(View v) {
        MaterialButton b = v.findViewById(R.id.saveExerciseButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NavBarActivity) getActivity()).replaceFragments(ExercisesFragment.class);
                Toast toast = Toast.makeText(getContext(), "Saved Exercise", Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }

}