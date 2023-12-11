package com.example.restaurantbookingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private TextView textview;
    private EditText customerNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        textview = findViewById(R.id.textView4);
        customerNameEditText = findViewById(R.id.editTextText2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String customerName = customerNameEditText.getText().toString().trim();
                if (!customerName.isEmpty()) {
                    checkCustomerExistence(customerName);
                } else {
                    Toast.makeText(MainActivity.this, "Username and Password required!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opensignup();
            }
        });
    }

    private void opensignup() {
        Intent intent = new Intent(this, Signup.class);
        startActivity(intent);
    }

    private void opendashboard(String username) {
        Intent intent = new Intent(this, dashboard.class);
        intent.putExtra("USERNAME", username); // Use the same key "USERNAME"
        startActivity(intent);
    }

    private void checkCustomerExistence(String customerName) {
        // Replace with your API endpoint
        String apiUrl = "https://web.socem.plymouth.ac.uk/COMP2000/ReservationApi/api/Reservations?customerName=" + customerName;

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(apiUrl)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(() -> Toast.makeText(MainActivity.this, "Failed to connect to the server", Toast.LENGTH_SHORT).show());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    // Customer exists, open dashboard
                    runOnUiThread(() -> opendashboard(customerName));
                } else {
                    // Check if the customer was not found (HTTP status code 404)
                    if (response.code() == 404) {
                        runOnUiThread(() -> Toast.makeText(MainActivity.this, "Customer not found!", Toast.LENGTH_SHORT).show());
                    } else {
                        // Handle other error cases
                        runOnUiThread(() -> Toast.makeText(MainActivity.this, "Error: " + response.message(), Toast.LENGTH_SHORT).show());
                    }
                }
            }
        });
    }
}
