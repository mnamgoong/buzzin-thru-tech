package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class StartScreen extends AppCompatActivity {

    private Activity binding;
    private Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Check button", "Entered");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
        b1 = (Button) findViewById(R.id.button_start);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Check button", "Start Clicked");
                Intent i = new Intent(StartScreen.this, UserInputScreen.class);
                startActivity(i);
            }
        });

    }

}