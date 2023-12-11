package com.example.restaurantbookingapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Summary extends AppCompatActivity {

    private TextView textView19;
    private TextView textView20;
    private TextView textView26;
    private TextView textView21;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_summary);

        // Initialize the TextViews and ImageView
        textView19 = findViewById(R.id.textView19);
        textView20 = findViewById(R.id.textView20);
        textView26 = findViewById(R.id.textView26);
        textView21 = findViewById(R.id.textView21);

        // Retrieve the selected date, meal, image resource, table size, and location from the intent
        String selectedDate = getIntent().getStringExtra("SELECTED_DATE");
        String selectedMeal = getIntent().getStringExtra("meal");
        int tableSize = getIntent().getIntExtra("TABLE_SIZE", 0);
        String selectedLocation = getIntent().getStringExtra("location");

        if (selectedDate != null) {
            textView19.setText("Date: " + selectedDate);
        }

        if (selectedMeal != null) {
            textView20.setText("Meal: " + selectedMeal);
        }

        // Display the table size in textView26
        textView26.setText("Table Size: " + tableSize);

        // Display the selected location in textViewLocation
        textView21.setText("Location: " + selectedLocation);

        // Save data to SharedPreferences
        saveDataToSharedPreferences(selectedDate, selectedMeal, selectedLocation, tableSize);

        // Initialize the button
        Button button3 = findViewById(R.id.button3);

        // Set a click listener for button3
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Insert data into the API
                insertDataIntoApi(selectedDate, selectedMeal, selectedLocation, tableSize);
            }
        });
    }

    private void insertDataIntoApi(String date, String meal, String location, int tableSize) {
        try {
            // API endpoint URL
            String apiUrl = "https://web.socem.plymouth.ac.uk/COMP2000/ReservationApi/api/Reservations";

            // Create JSON object
            JSONObject jsonParam = new JSONObject();
            jsonParam.put("customerName", "Danny");
            jsonParam.put("customerPhoneNumber", "");
            jsonParam.put("meal", meal);
            jsonParam.put("seatingArea", location);
            jsonParam.put("tableSize", tableSize);
            jsonParam.put("date", date);

            // Create HttpURLConnection
            URL url = new URL(apiUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json; utf-8");
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setDoOutput(true);

            // Write JSON data to the output stream
            try (OutputStream os = urlConnection.getOutputStream()) {
                byte[] input = jsonParam.toString().getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Check response code
            int responseCode = urlConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                showToast("Reservation successful");

                // Launch the Reservation activity with the corresponding data
                Intent intent = new Intent(Summary.this, Reservation.class);
                intent.putExtra("SELECTED_DATE", date);  // Pass the selected date to Reservation activity
                startActivity(intent);
                finish();  // Optional: finish the current activity
            } else {
                showToast("Reservation failed. Response code: " + responseCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
            showToast("Network error. Please try again.");
        }
    }

    private void saveDataToSharedPreferences(String selectedDate, String selectedMeal, String selectedLocation, int tableSize) {
        // Use SharedPreferences to save data
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("selectedDate", selectedDate);
        editor.putString("selectedMeal", selectedMeal);
        editor.putString("selectedLocation", selectedLocation);
        editor.putInt("tableSize", tableSize);

        editor.apply();
    }

    private void showToast(String message) {
        // Display a toast message
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
