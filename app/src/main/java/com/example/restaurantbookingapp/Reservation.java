package com.example.restaurantbookingapp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Reservation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        TextView cancelTextView = findViewById(R.id.textView13);
        TextView Edit = findViewById(R.id.textView14);
        cancelTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Show the cancel booking confirmation dialog
                showCancelBookingDialog();
            }
        });

        View.OnClickListener EditClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Launch the Profile activity
                Intent intent = new Intent(Reservation.this, Date_Layout.class);
                startActivity(intent);
            }

        };
        Edit.setOnClickListener(EditClickListener);

    }

    private void showCancelBookingDialog() {
        // Create a custom dialog
        Dialog cancelDialog = new Dialog(this);
        cancelDialog.setContentView(R.layout.cancelbookingdialog);

        // Initialize dialog views
        TextView dialogTitle = cancelDialog.findViewById(R.id.dialogTitle);
        TextView dialogMessage = cancelDialog.findViewById(R.id.dialogMessage);
        Button btnConfirmCancel = cancelDialog.findViewById(R.id.btnConfirmCancel);
        Button btnCancel = cancelDialog.findViewById(R.id.btnCancel);

        // Customize dialog content
        dialogTitle.setText("Cancel Booking");
        dialogMessage.setText("Are you sure you want to cancel this booking?");

        // Set click listeners for buttons
        btnConfirmCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle confirmed cancel action
                // You can implement the cancel logic here
                // For example, show a toast message or perform the cancellation
                cancelDialog.dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle cancellation of cancel action
                cancelDialog.dismiss();
            }
        });

        // Show the dialog
        cancelDialog.show();
    }

}
