package com.example.restaurantbookingapp;// DashboardActivity.java
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurantbookingapp.R;

public class dashboard extends AppCompatActivity {

    private static final int REQUEST_CODE_BOOKING_ACTIVITY = 1;
    private ImageView selectedImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //selectedImageView = findViewById(R.id.selectedImageView);

        ImageButton bkButton = findViewById(R.id.bkbutton);
        ImageButton lunchButton = findViewById(R.id.lunchbutton);
        ImageButton dinnerButton = findViewById(R.id.dinnerbutton);

        View.OnClickListener buttonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Launch the Booking activity
                Intent intent = new Intent(dashboard.this, Booking.class);
                intent.putExtra("buttonId", view.getId());
                startActivityForResult(intent, REQUEST_CODE_BOOKING_ACTIVITY);
            }
        };

        bkButton.setOnClickListener(buttonClickListener);
        lunchButton.setOnClickListener(buttonClickListener);
        dinnerButton.setOnClickListener(buttonClickListener);
    }

    // This method will be called when BookingActivity finishes
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Optional: If you don't need any information from BookingActivity, you can skip this check
        if (requestCode == REQUEST_CODE_BOOKING_ACTIVITY && resultCode == RESULT_OK && data != null) {
            // Retrieve the chosen image resource from the result (not needed in this case)
            // int imageResource = data.getIntExtra("imageResource", 0);

            // No need to update the image in the Dashboard activity
            // selectedImageView.setImageResource(imageResource);
        }
    }
}
