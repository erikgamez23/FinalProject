package com.c323FinalProject.egameztatclend;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.c323FinalProject.egameztatclend.DailyTrainingFragments.DailyTrainingFragment;
import com.c323FinalProject.egameztatclend.ExerciseFragments.ExercisesFragment;
import com.google.android.material.navigation.NavigationView;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class NavBarActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    FrameLayout frameLayout;

    Bitmap imageBitMap;     //for user pfp

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_bar);

        //TODO getting a reference to the sharedPreferences to get access to the user pfp and initializing bitmap.

        sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        imageBitMap = decodeBase64(sharedPreferences.getString("imageBitMap",""));

        initializeViews();
        initializeDefaultFragment(savedInstanceState, 0);
        toggleDrawer();
        checkforUserPhoto();
    }

    public void replaceFragments(Class fragmentClass) {
        Fragment fragment = null;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.framelayout_id, fragment)
                .commit();
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

    /*
     * TODO: Checks if user has a pfp: does nothing if null
     */
    private void checkforUserPhoto() {

        /*
         * TODO I added an instance variable called imageBitMap that will hold the user's pfp,
         * TODO and so in this method you can just check if imageBitMap != null. Commenting it out
         * TODO because I don't know for sure what you want this method to do.
         */

        //if(imageBitMap != null){
        //  do something
        //}
    }

    /**
     * Pretty self-explanatory lol
     */
    private void initializeViews() {
        navigationView = findViewById(R.id.navigation_view_id);
        drawerLayout = findViewById(R.id.drawer_layout_id);
        toolbar = findViewById(R.id.toolbar_id);
        setSupportActionBar(toolbar);
        frameLayout = findViewById(R.id.framelayout_id);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        ImageView navBarImageView = headerView.findViewById(R.id.navBarImageView);
        navBarImageView.setImageBitmap(Bitmap.createScaledBitmap(imageBitMap,75,75,false));
    }

    /**
     * Checks if the savedInstanceState is null - onCreate() is ran
     * If so, display fragment of navigation drawer menu at position itemIndex and
     * set checked status as true
     * @param savedInstanceState
     * @param itemIndex
     */
    private void initializeDefaultFragment(Bundle savedInstanceState, int itemIndex){
        if (savedInstanceState == null){
            MenuItem menuItem = navigationView.getMenu().getItem(itemIndex).setChecked(true);
            onNavigationItemSelected(menuItem);
        }
    }

    /**
     * Creates an instance of the ActionBarDrawerToggle class:
     * 1) Handles opening and closing the navigation drawer
     * 2) Creates a hamburger icon in the toolbar
     * 3) Attaches listener to open/close drawer on icon clicked and rotates the icon
     */
    private void toggleDrawer() {
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        //Checks if the navigation drawer is open -- If so, close it
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        // If drawer is already close -- Do not override original functionality
        else {
            super.onBackPressed();
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.Exercise:
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_id, new ExercisesFragment())
                        .commit();
                closeDrawer();
                break;
            case R.id.ModeOfTraining:
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_id, new ModeOfTrainingFragment())
                        .commit();
                closeDrawer();
                break;
            case R.id.DailyTraining:
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_id, new DailyTrainingFragment())
                        .commit();
                closeDrawer();
                break;
            case R.id.CalendarView:
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_id, new CalendarFragment())
                        .commit();
                closeDrawer();
                break;

        }
        deSelectCheckedState();
        return true;
    }
    /**
     *  Checks if the navigation drawer is open - if so, close it
     */
    private void closeDrawer(){
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    /**
     * Iterates through all the items in the navigation menu and deselects them:
     * removes the selection color
     */
    private void deSelectCheckedState(){
        int noOfItems = navigationView.getMenu().size();
        for (int i=0; i<noOfItems;i++){
            navigationView.getMenu().getItem(i).setChecked(false);
        }
    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        Bitmap newExerciseBitmap;
        ImageView newExerciseImageView = findViewById(R.id.newExerciseImageView);
        if (resultCode == RESULT_OK) {
            if(reqCode == 1) {
                try {
                    final Uri imageUri = data.getData();
                    final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                    newExerciseBitmap = BitmapFactory.decodeStream(imageStream);
                    newExerciseImageView.setImageBitmap(Bitmap.createScaledBitmap(newExerciseBitmap, newExerciseImageView.getWidth(), newExerciseImageView.getHeight(),false));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
                }
            } else if( reqCode == 17){
                Bundle extra = data.getExtras();
                newExerciseBitmap= (Bitmap) extra.get("data");
                newExerciseImageView.setImageBitmap(Bitmap.createScaledBitmap(newExerciseBitmap, newExerciseImageView.getWidth(), newExerciseImageView.getHeight(),false));
            }
        } else {
            Toast.makeText(this, "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }
}