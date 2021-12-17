package com.c323FinalProject.egameztatclend;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    ImageView imageViewProfilePic;

    EditText editTextUserName, editTextEmail;

    Bitmap imageBitmap;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //accessing sharedPreferences to find user info
        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);


        //if the user is still logged in, immediately take them to the next activity
        if(sharedPreferences.getBoolean("loggedIn",false)){
            startActivity(new Intent(MainActivity.this, NavBarActivity.class));
        }


        imageViewProfilePic = findViewById(R.id.imageViewProfilePic);

        imageViewProfilePic.setOnClickListener(view -> {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Choose Option")
                    .setMessage("Enable access so you can take photos")
                    .setPositiveButton("Choose from Gallery", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                            photoPickerIntent.setType("image/*");
                            startActivityForResult(photoPickerIntent, 1);
                        }
                    })
                    .setNegativeButton("Camera", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            takeAPicture();
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        });

        editTextUserName = findViewById(R.id.editTextUserName);
        editTextEmail = findViewById(R.id.editTextUserEmail);

        Button buttonNext = findViewById(R.id.buttonNext);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editTextEmail.getText().toString().equals("") || editTextUserName.getText().toString().equals("") || imageViewProfilePic.getDrawable()==null){
                    Toast.makeText(MainActivity.this, "You must enter a username, email, and image before continuing", Toast.LENGTH_SHORT).show();
                } else {

                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString("userName", editTextUserName.getText().toString());
                    editor.putString("email", editTextEmail.getText().toString());

                    if(imageBitmap == null){
                        imageBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.default_profile_pic);
                    }

                    editor.putString("imageBitMap",encodeTobase64(imageBitmap));
                    editor.putBoolean("loggedIn", true);

                    editor.apply();

                    startActivity(new Intent(MainActivity.this, NavBarActivity.class));
                }
            }
        });
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

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);


        if (resultCode == RESULT_OK) {
            if(reqCode == 1) {
                try {
                    final Uri imageUri = data.getData();
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    imageBitmap = BitmapFactory.decodeStream(imageStream);
                    imageViewProfilePic.setImageBitmap(Bitmap.createScaledBitmap(imageBitmap,imageViewProfilePic.getWidth(),imageViewProfilePic.getHeight(),false));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
                }
            } else if( reqCode == 17){
                Bundle extra = data.getExtras();
                imageBitmap= (Bitmap) extra.get("data");
                ImageView imageView = findViewById(R.id.imageViewProfilePic);
                imageViewProfilePic.setImageBitmap(Bitmap.createScaledBitmap(imageBitmap,imageViewProfilePic.getWidth(),imageViewProfilePic.getHeight(),false));
            }
        } else {
            Toast.makeText(this, "You haven't picked Image",Toast.LENGTH_LONG).show();
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
     *
     * @return
     */
    private boolean hasCamera() {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }
}