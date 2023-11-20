package com.example.restaurantbookingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private TextView textview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opendashboard();
            }
        });
        textview = findViewById(R.id.textView4);
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

    public void opendashboard(){
        Intent intent = new Intent(this, dashboard.class);
        startActivity(intent);
    }

}