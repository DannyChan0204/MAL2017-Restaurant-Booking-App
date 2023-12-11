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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Date_Layout extends AppCompatActivity {

    // Variable to store the selected date
    private String selectedDate;
    // Variable to store the selected location
    private String selectedLocation;

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
                Calendar selectedDateCalendar = Calendar.getInstance();
                selectedDateCalendar.set(year, month, dayOfMonth);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                selectedDate = sdf.format(selectedDateCalendar.getTime());
            }
        });

        btnback = findViewById(R.id.bookingBack);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Initialize ImageButtons for reservation confirmation
        int[] confirmationButtons = {
                R.id.imageButton6,
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
                    // Determine the table size based on the clicked button
                    int tableSize;
                    if (buttonId == R.id.imageButton6 ||
                            buttonId == R.id.imageButton10 ||
                            buttonId == R.id.imageButton14 ||
                            buttonId == R.id.imageButton16 ||
                            buttonId == R.id.imageButton25 ||
                            buttonId == R.id.imageButton28 ||
                            buttonId == R.id.imageButton29) {
                        tableSize = 4;
                    } else if (buttonId == R.id.imageButton11) {
                        tableSize = 2;
                    } else if (buttonId == R.id.imageButton22 ||
                            buttonId == R.id.imageButton24 ||
                            buttonId == R.id.imageButton27) {
                        tableSize = 6;
                    } else if (buttonId == R.id.imageButton19) {
                        tableSize = 8;
                    } else if (buttonId == R.id.imageButton17 ||
                            buttonId == R.id.imageButton18) {
                        tableSize = 10;
                    } else {
                        tableSize = 0; // Handle the default case
                    }

                    showConfirmationDialog(tableSize);
                }
            });
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void showConfirmationDialog(int tableSize) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Reservation")
                .setMessage("Confirm reservation?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (selectedDate != null) {
                            showToast("Proceeding to Reservation Summary");
                            // Add your logic for confirmed reservation
                            navigateToSummary(selectedDate, tableSize);
                        } else {
                            showToast("Please select a date");
                        }
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

    private void navigateToSummary(String selectedDate, int tableSize) {
        // Retrieve the selected location from the intent
        selectedLocation = getIntent().getStringExtra("location");

        Intent intent = new Intent(this, Summary.class);
        intent.putExtra("SELECTED_DATE", selectedDate);
        intent.putExtra("TABLE_SIZE", tableSize);
        intent.putExtra("meal", getIntent().getStringExtra("meal"));
        intent.putExtra("location", selectedLocation);
        startActivity(intent);
        finish();
    }
}
