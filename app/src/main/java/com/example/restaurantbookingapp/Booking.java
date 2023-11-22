package com.example.restaurantbookingapp;// BookingActivity.java
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class Booking extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        // Get the buttonId from the intent
        int buttonId = getIntent().getIntExtra("buttonId", 0);

        // Determine the image resource based on the buttonId
        int imageResource = 0;

        if (buttonId == R.id.bkbutton) {
            imageResource = R.drawable.breakfastbookingcolumn;
        } else if (buttonId == R.id.lunchbutton) {
            imageResource = R.drawable.lunchbookingcolumn;
        } else if (buttonId == R.id.dinnerbutton) {
            imageResource = R.drawable.dinnerbookingcolumn;
        }

        // Send the chosen image resource back to the Dashboard activity
        setResultAndFinish(imageResource);
    }

    private void setResultAndFinish(int imageResource) {
        // Create an intent to send the result back to the Dashboard activity
        Intent resultIntent = new Intent();
        resultIntent.putExtra("imageResource", imageResource);
        setResult(RESULT_OK, resultIntent);

        // Finish the current activity
        finish();
    }
}
