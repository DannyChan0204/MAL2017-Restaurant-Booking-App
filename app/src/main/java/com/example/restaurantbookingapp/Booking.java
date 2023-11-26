package com.example.restaurantbookingapp;// BookingActivity.java
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
        TextView selectedText2 = findViewById(R.id.breakfasttext2);
        ImageView selectedImage = findViewById(R.id.bookingimage1);
        ImageView selectedImage2 = findViewById(R.id.bookingimage);

        // Get the data from the intent
        String meal = getIntent().getStringExtra("meal");
        int imageResource = getIntent().getIntExtra("imageResource", 0);
        Button submitButton;
        ImageView btnback;

        // Update the TextView and ImageView
        selectedText.setText(meal);
        selectedImage.setImageResource(imageResource);

        ImageButton indoorbutton = findViewById(R.id.indoorbutton);
        ImageButton seasidebutton = findViewById(R.id.seasidebutton);
        ImageButton gardensidebutton = findViewById(R.id.gardensidebutton);
        Button ReserveButton = findViewById(R.id.ReserveButton);

        indoorbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Change the image in the ImageView to the desired resource
                selectedText2.setText("Indoor");
                selectedImage2.setImageResource(R.drawable.indoorupdate);
                ReserveButton.setVisibility(View.VISIBLE);
            }
        });

        seasidebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Change the image in the ImageView to the desired resource
                selectedText2.setText("Seaside");
                selectedImage2.setImageResource(R.drawable.seasideupdate);
                ReserveButton.setVisibility(View.VISIBLE);
            }
        });

        gardensidebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Change the image in the ImageView to the desired resource
                selectedText2.setText("Gardenside");
                selectedImage2.setImageResource(R.drawable.gardensideupdate);
                ReserveButton.setVisibility(View.VISIBLE);
            }
    });

        btnback = (ImageView) findViewById(R.id.BookingBack);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {finish();
            }
        });

        ReserveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to a new screen (Replace NewScreenActivity.class with your desired activity)
                Intent intent = new Intent(Booking.this, Date_Layout.class);
                startActivity(intent);
            }
        });

}
}
