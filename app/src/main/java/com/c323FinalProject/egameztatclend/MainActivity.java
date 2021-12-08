package com.c323FinalProject.egameztatclend;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView r = findViewById(R.id.textview1);
        r.setText("GitHub Test");
        r.setText("GitHub Test 2");
        r.setText("Test 3");
    }
}