// dashboard.java
package com.example.restaurantbookingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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


        View.OnClickListener buttonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Launch the Booking activity with the corresponding text and image
                Intent intent = new Intent(dashboard.this, Booking.class);

                int buttonId = view.getId();
                if (buttonId == R.id.bkbutton) {
                    intent.putExtra("meal", "Breakfast");
                    intent.putExtra("imageResource", R.drawable.breakfastbookingcolumn);
                } else if (buttonId == R.id.lunchbutton) {
                    intent.putExtra("meal", "Lunch");
                    intent.putExtra("imageResource", R.drawable.lunchbookingcolumn);
                } else if (buttonId == R.id.dinnerbutton) {
                    intent.putExtra("meal", "Dinner");
                    intent.putExtra("imageResource", R.drawable.dinnerbookingcolumn);
                }

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
                // Launch the Profile activity
                Intent intent = new Intent(dashboard.this, Reservation.class);
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
