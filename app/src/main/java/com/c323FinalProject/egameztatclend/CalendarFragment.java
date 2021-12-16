package com.c323FinalProject.egameztatclend;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

public class CalendarFragment extends Fragment {
    MaterialCalendarView calendar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_calendar, container, false);
        initializeViews(v);
        return v;
    }

    private void initializeViews(View v) {
        calendar = v.findViewById(R.id.materialCalendar);
//        calendar.setOnClickListener();
    }
}