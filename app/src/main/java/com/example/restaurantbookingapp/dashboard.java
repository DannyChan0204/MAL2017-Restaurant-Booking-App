// dashboard.java
package com.example.restaurantbookingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        ImageButton bkButton = findViewById(R.id.bkbutton);
        ImageButton lunchButton = findViewById(R.id.lunchbutton);
        ImageButton dinnerButton = findViewById(R.id.dinnerbutton);
        ImageButton profileButton = findViewById(R.id.imageButton);
        ImageButton ReservationButton = findViewById(R.id.imageButton8);

        String selectedDate = getIntent().getStringExtra("SELECTED_DATE");
        String selectedMeal = getIntent().getStringExtra("meal");
        int tableSize = getIntent().getIntExtra("TABLE_SIZE", 0);
        String selectedLocation = getIntent().getStringExtra("location");

        View.OnClickListener buttonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Launch the Booking activity with the corresponding text and image
                Intent intent = new Intent(dashboard.this, Booking.class);

                int buttonId = view.getId();
                String selectedMeal = "";

                if (buttonId == R.id.bkbutton) {
                    selectedMeal = "Breakfast";
                    intent.putExtra("imageResource", R.drawable.breakfastbookingcolumn);
                } else if (buttonId == R.id.lunchbutton) {
                    selectedMeal = "Lunch";
                    intent.putExtra("imageResource", R.drawable.lunchbookingcolumn);
                } else if (buttonId == R.id.dinnerbutton) {
                    selectedMeal = "Dinner";
                    intent.putExtra("imageResource", R.drawable.dinnerbookingcolumn);
                }

                intent.putExtra("meal", selectedMeal);
                startActivity(intent);
            }
        };


        View.OnClickListener profileButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Launch the Profile activity
                Intent intent = new Intent(dashboard.this, Profile.class);
                startActivity(intent);
            }
        };

        View.OnClickListener ReservationClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Launch the reservation activity
                Intent intent = new Intent(dashboard.this, Reservation.class);
                intent.putExtra("customerName", "Danny");
                intent.putExtra("meal", selectedMeal);
                intent.putExtra("location", selectedLocation);
                intent.putExtra("tableSize", tableSize);
                intent.putExtra("selectedDate", selectedDate);
                startActivity(intent);
            }
        };

        bkButton.setOnClickListener(buttonClickListener);
        lunchButton.setOnClickListener(buttonClickListener);
        dinnerButton.setOnClickListener(buttonClickListener);

        // Set OnClickListener for the profileButton
        profileButton.setOnClickListener(profileButtonClickListener);
        ReservationButton.setOnClickListener(ReservationClickListener);
    }

}
