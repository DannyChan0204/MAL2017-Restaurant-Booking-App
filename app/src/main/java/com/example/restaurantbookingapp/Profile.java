package com.example.restaurantbookingapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Profile extends AppCompatActivity {
    private ImageButton imageButton4;
    private ImageButton imageButton12;
    private ImageButton imageButton13;
    private ImageButton imageButton3;
    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageButton profileImageButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        imageButton4 = findViewById(R.id.imageButton4);
        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendashboard();
            }
        });

        imageButton12 = findViewById(R.id.imageButton12);
        imageButton12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRatingButtonClick();
            }
        });

        imageButton3 = findViewById(R.id.imageButton3);
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openreservation();
            }
        });

        imageButton13 = findViewById(R.id.imageButton13);
        imageButton13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });


        profileImageButton = findViewById(R.id.profileImageButton);
    }

    private void logout() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void openreservation() {
        Intent intent = new Intent(this, Reservation.class);
        startActivity(intent);
    }

    public void onProfileImageClick(View view) {
        // Open the image gallery when the ImageButton is clicked
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            // Handle the selected image
            Uri selectedImageUri = data.getData();
            profileImageButton.setImageURI(selectedImageUri);
        }
    }

    public void opendashboard() {
        Intent intent = new Intent(this, dashboard.class);
        startActivity(intent);
    }

    // Adding the new method for handling the rating button click
    public void onRatingButtonClick() {
        // Create a dialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Inflate the custom layout for the dialog
        View dialogView = getLayoutInflater().inflate(R.layout.activity_rating_review_dialog, null);

        // Find views in the custom layout
        RatingBar ratingBar = dialogView.findViewById(R.id.ratingBar);
        EditText reviewEditText = dialogView.findViewById(R.id.reviewEditText);
        Button submitReviewButton = dialogView.findViewById(R.id.submitReviewButton);

        // Set the custom layout to the dialog
        builder.setView(dialogView);
        // Create the dialog
        AlertDialog alertDialog = builder.create();
        // Show the dialog
        alertDialog.show();

        // Handle the click event for the "Submit Review" button in the dialog
        submitReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the review submission
                float rating = ratingBar.getRating();
                String reviewText = reviewEditText.getText().toString();

                // You can perform actions based on the rating and review text
                // For now, let's just display a Toast message
                String message = "Rating: " + rating + "\nReview: " + reviewText;
                Toast.makeText(Profile.this, message, Toast.LENGTH_SHORT).show();

                // Once done, you can dismiss the dialog
                alertDialog.dismiss();
            }
        });
    }
}
