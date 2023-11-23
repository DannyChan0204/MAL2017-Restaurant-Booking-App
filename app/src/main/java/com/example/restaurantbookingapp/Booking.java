package com.example.restaurantbookingapp;// BookingActivity.java
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Booking extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        TextView selectedText = findViewById(R.id.breakfasttext);
        ImageView selectedImage = findViewById(R.id.bookingimage1);

        // Get the data from the intent
        String meal = getIntent().getStringExtra("meal");
        int imageResource = getIntent().getIntExtra("imageResource", 0);

        // Update the TextView and ImageView
        selectedText.setText(meal);
        selectedImage.setImageResource(imageResource);
    }
}
