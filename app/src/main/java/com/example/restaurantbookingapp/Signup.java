package com.example.restaurantbookingapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Signup extends AppCompatActivity {
    ImageView btnback;
    Button signup;
    TextView signupemailinput; // Assuming you have an EditText for email input

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        btnback = findViewById(R.id.imageView5);
        signupemailinput = findViewById(R.id.signupemailinput); // Assuming you have an EditText for email input
        signup = findViewById(R.id.Signupbutton);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String customerName = signupemailinput.getText().toString().trim();
                if (!customerName.isEmpty()) {
                    // Execute AsyncTask to send POST request
                    new PostRequestAsyncTask().execute(customerName);
                } else {
                    Toast.makeText(Signup.this, "Please enter an email for customerName", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void back() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private class PostRequestAsyncTask extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            String customerName = params[0];
            String apiUrl = "https://web.socem.plymouth.ac.uk/COMP2000/ReservationApi/api/Reservations";

            try {
                URL url = new URL(apiUrl);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                urlConnection.setDoOutput(true);

                // Create the POST data
                String postData = "customerName=" + customerName;
                byte[] postDataBytes = postData.getBytes(StandardCharsets.UTF_8);

                // Write the POST data to the connection
                try (OutputStream os = urlConnection.getOutputStream()) {
                    os.write(postDataBytes);
                }

                // Check if the request was successful (HTTP status code 200)
                return urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK;

            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (success) {
                Toast.makeText(Signup.this, "Sign-up successful", Toast.LENGTH_SHORT).show();
                // You can navigate to another activity or perform any other actions upon successful sign-up
            } else {
                Toast.makeText(Signup.this, "Failed to sign up", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
