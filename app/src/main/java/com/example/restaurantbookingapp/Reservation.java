package com.example.restaurantbookingapp;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

public class Reservation extends AppCompatActivity {

    private ImageButton imageButton30;
    private TextView textView22;
    private TextView textView9;
    private TextView textView10;
    private TextView cancelTextView;
    private TextView Edit;
    private TextView textView25;
    private TextView textView7;
    private TextView textView23;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        // Initialize TextViews
        textView22 = findViewById(R.id.textView22);
        textView9 = findViewById(R.id.textView9);
        textView10 = findViewById(R.id.textView10);
        cancelTextView = findViewById(R.id.textView13);
        Edit = findViewById(R.id.textView14);
        textView25 = findViewById(R.id.textView25);
        textView7 = findViewById(R.id.textView7);
        textView23 = findViewById(R.id.textView23);

        imageButton30 = findViewById(R.id.imageButton30);
        imageButton30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });

        // Set click listener for textView13
        cancelTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCancelConfirmationDialog();
            }
        });

        // Retrieve data from SharedPreferences
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        String selectedMeal = preferences.getString("selectedMeal", "");
        String selectedLocation = preferences.getString("selectedLocation", "");
        int tableSize = preferences.getInt("tableSize", 0);

        // Display data in TextViews
        textView22.setText("Meal: " + selectedMeal);
        textView9.setText("Table Size: " + tableSize);
        textView10.setText("Location: " + selectedLocation);

        // Check if values for textView22, textView9, and textView10 are null
        if (TextUtils.isEmpty(selectedMeal) && tableSize == 0 && TextUtils.isEmpty(selectedLocation)) {
            // Hide cancelTextView and Edit
            cancelTextView.setVisibility(View.GONE);
            Edit.setVisibility(View.GONE);
        }
    }

    private void back() {
        finish(); // Finish the current activity and go back to the previous one
    }

    private void showCancelConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cancel Reservation");
        builder.setMessage("Are you sure you want to cancel this reservation?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Move values to new TextViews
                textView25.setText(textView22.getText());
                textView7.setText(textView9.getText());
                textView23.setText(textView10.getText());

                // Clear values in original TextViews
                textView22.setText("");
                textView9.setText("");
                textView10.setText("");
            }
        });
        builder.setNegativeButton("No", null);
        builder.show();
    }
}
