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
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.c323FinalProject.egameztatclend.DailyTrainingFragments.DailyTrainingFragment;
import com.c323FinalProject.egameztatclend.ExerciseFragments.ExercisesFragment;
import com.google.android.material.navigation.NavigationView;

public class NavBarActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_bar);
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
     * TODO: Checks if user has a pfp: does nothing if null
     */
    private void checkforUserPhoto() {
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
}