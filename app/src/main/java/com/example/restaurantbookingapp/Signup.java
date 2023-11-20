package com.example.restaurantbookingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Signup extends AppCompatActivity {
    ImageView btnback;
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        btnback = (ImageView) findViewById(R.id.imageView5);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {back();
            }
        });

        signup = (Button) findViewById(R.id.Signupbutton);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {SignUp();
            }
        });


    }
    public void back(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void SignUp(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}