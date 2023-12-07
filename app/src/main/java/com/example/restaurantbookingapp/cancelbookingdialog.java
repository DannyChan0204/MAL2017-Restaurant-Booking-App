package com.example.restaurantbookingapp;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

public class cancelbookingdialog extends Dialog {

    public cancelbookingdialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cancelbookingdialog);

        Button btnConfirmCancel = findViewById(R.id.btnConfirmCancel);
        Button btnCancel = findViewById(R.id.btnCancel);

        btnConfirmCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                // Add logic to navigate to the booking activity
                navigateToBookingActivity();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                // Handle the case when the user decides not to cancel the booking
            }
        });
    }

    private void navigateToBookingActivity() {
        // Add the logic to navigate to the booking activity
    }
}
