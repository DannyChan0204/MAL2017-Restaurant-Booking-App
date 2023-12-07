package com.example.restaurantbookingapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

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

        btnback = findViewById(R.id.bookingBack);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Initialize ImageButtons
        int[] unavailabilityButtons = {
                R.id.imageButton5, R.id.imageButton15, R.id.imageButton20,
                R.id.imageButton21, R.id.imageButton23, R.id.imageButton26
        };

        // Set click listeners for unavailability buttons
        for (int buttonId : unavailabilityButtons) {
            ImageButton button = findViewById(buttonId);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showToast("Table is unavailable at the moment");
                }
            });
        }

        // Initialize ImageButtons for reservation confirmation
        int[] confirmationButtons = {
                R.id.imageButton10, R.id.imageButton11, R.id.imageButton14,
                R.id.imageButton16, R.id.imageButton17, R.id.imageButton18,
                R.id.imageButton19, R.id.imageButton22, R.id.imageButton24,
                R.id.imageButton25, R.id.imageButton27, R.id.imageButton28,
                R.id.imageButton29
        };

        // Set click listeners for reservation confirmation buttons
        for (int buttonId : confirmationButtons) {
            ImageButton button = findViewById(buttonId);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showConfirmationDialog();
                }
            });
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Reservation")
                .setMessage("Confirm reservation?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        showToast("Reservation Confirmed!");
                        // Add your logic for confirmed reservation
                        navigateToDashboard();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Add your logic for canceled reservation or do nothing
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void navigateToDashboard() {
        Intent intent = new Intent(this, dashboard.class);
        startActivity(intent);
        finish(); // Optional: Finish the current activity if you don't want to come back to it
    }
}
