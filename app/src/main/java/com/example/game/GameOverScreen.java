package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOverScreen extends AppCompatActivity {

    private Button b1;
    private Button b2;
    private TextView finalPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over_screen);
        b1 = (Button) findViewById(R.id.yes_end);
        b2 = (Button) findViewById(R.id.no_end);

        finalPoints = (TextView) findViewById(R.id.text_finalpoints);
        finalPoints.setText("" + Player.getPoints());

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GameOverScreen.this, UserInputScreen.class);
                startActivity(i);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GameOverScreen.this, StartScreen.class);
                startActivity(i);
            }
        });
    }
}