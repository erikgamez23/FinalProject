package com.c323FinalProject.egameztatclend.ExerciseFragments;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.c323FinalProject.egameztatclend.NavBarActivity;
import com.c323FinalProject.egameztatclend.R;
import com.google.android.material.button.MaterialButton;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class NewExerciseFragment extends Fragment {

    Bitmap imageBitmap;

    ImageView newExerciseImageView;
    EditText newExerciseName;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_new_exercise, container, false);
        initializeViews(v);
        return v;
    }

    private void initializeViews(View v) {
        newExerciseName = v.findViewById(R.id.newExerciseName);
        MaterialButton b = v.findViewById(R.id.saveExerciseButton);
        newExerciseImageView = v.findViewById(R.id.newExerciseImageView);
        newExerciseImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takeAPicture();
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addExerciseToDB();
                ((NavBarActivity) getActivity()).replaceFragments(ExercisesFragment.class);
                Toast toast = Toast.makeText(getContext(), "Saved Exercise", Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }

    private void addExerciseToDB() {
       if(newExerciseName.getText().equals(""))
       {
           Toast toast = Toast.makeText(getContext(), "Add Name AND Picture", Toast.LENGTH_LONG);
       }
       else {
           Exercise exercise = new Exercise();
           exercise.set_name(newExerciseName.getText().toString());
            //TODO Add Bitmap to Database
           MyExerciseDBHandler dbHandler = new MyExerciseDBHandler(getContext(), null, null, 1);
           dbHandler.addExercise(exercise);
           Log.i("YES","ADDED TO DB");
       }
    }

    /**
     * Method used to take a picture
     */
    private void takeAPicture() {
        if(hasCamera()) {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(takePictureIntent,17);
        }
    }

    /**
     * Simple method to ensure that the device has a camera
     * @return
     */
    private boolean hasCamera() {
        return requireActivity().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==2 && resultCode==RESULT_OK)
        {
            if(data == null){
                return;
            }
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = requireActivity().getContentResolver().openInputStream(imageUri);
                imageBitmap = BitmapFactory.decodeStream(imageStream);
                newExerciseImageView.setImageBitmap(Bitmap.createScaledBitmap(imageBitmap,150,150,false));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_LONG).show();
            } catch (Exception ex){
                ex.printStackTrace();
                Toast.makeText(getActivity(),"Something went wrong", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(requireActivity(), "Failed To Capture Image", Toast.LENGTH_SHORT).show();
        }
    }
}