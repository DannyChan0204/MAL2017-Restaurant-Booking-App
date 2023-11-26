package com.example.restaurantbookingapp;

import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class Date_Layout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_layout);

        ImageView btnback;

        CalendarView calendarView = findViewById(R.id.calendarID);

        // Set the minimum date to one week from today
        Calendar minDate = Calendar.getInstance();
        minDate.add(Calendar.DAY_OF_MONTH, 7);
        calendarView.setMinDate(minDate.getTimeInMillis());

        // Add a listener to handle date selection
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                // Handle the selected date
                // This method will be called when a user selects a date on the calendar
            }
        });

        btnback = (ImageView) findViewById(R.id.bookingBack);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {finish();
            }
        });

    }


}
