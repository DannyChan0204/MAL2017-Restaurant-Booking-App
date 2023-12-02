package com.example.restaurantbookingapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Profile extends AppCompatActivity {
    private ImageButton imageButton4;
    private static final int PICK_IMAGE_REQUEST = 1;

    private ImageButton profileImageButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        imageButton4 = (ImageButton) findViewById(R.id.imageButton4);
        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendashboard();
            }
        });

        profileImageButton = findViewById(R.id.profileImageButton);
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
    public void opendashboard(){
        Intent intent = new Intent(this, dashboard.class);
        startActivity(intent);
    }

}
