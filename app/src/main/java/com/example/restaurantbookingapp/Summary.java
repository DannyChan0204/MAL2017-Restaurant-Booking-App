// Summary.java
package com.example.restaurantbookingapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Summary extends AppCompatActivity {

    private TextView textView19;
    private TextView textView20;
    private TextView textView26;
    private TextView textView21; // Add this line

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        // Initialize the TextViews and ImageView
        textView19 = findViewById(R.id.textView19);
        textView20 = findViewById(R.id.textView20);
        textView26 = findViewById(R.id.textView26);
        textView21 = findViewById(R.id.textView21); // Add this line

        // Retrieve the selected date, meal, image resource, table size, and location from the intent
        String selectedDate = getIntent().getStringExtra("SELECTED_DATE");
        String selectedMeal = getIntent().getStringExtra("meal");
        int imageResource = getIntent().getIntExtra("imageResource", 0);
        int tableSize = getIntent().getIntExtra("TABLE_SIZE", 0);
        String selectedLocation = getIntent().getStringExtra("location"); // Add this line

        if (selectedDate != null) {
            textView19.setText("Date: " + selectedDate);
        }

        if (selectedMeal != null) {
            textView20.setText("Meal: " + selectedMeal);
        }

        // Display the table size in textView26
        textView26.setText("Table Size: " + tableSize);

        // Display the selected location in textViewLocation
        textView21.setText("Location: " + selectedLocation); // Add this line
    }
}
