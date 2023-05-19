package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameWonScreen extends AppCompatActivity {

    private Button b1;
    private Button b2;
    private TextView finalPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_won_screen);
        b1 = (Button) findViewById(R.id.end_yes);
        b2 = (Button) findViewById(R.id.end_no);

        finalPoints = (TextView) findViewById(R.id.text_finalpts);
        finalPoints.setText("" + Player.getPoints());

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GameWonScreen.this, UserInputScreen.class);
                startActivity(i);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GameWonScreen.this, StartScreen.class);
                startActivity(i);
            }
        });
    }
}