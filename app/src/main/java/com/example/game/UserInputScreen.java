package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class UserInputScreen extends AppCompatActivity {

    private EditText usernameEditText;
    private View view;
    private boolean image1 = false;
    private boolean image2 = false;
    private boolean image3 = false;
    private String finalDiff;
    private String sprite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_input_screen);
        TextView message = (TextView) findViewById(R.id.empty);
        ImageButton b1 = (ImageButton) findViewById(R.id.imagebutton_buzz);
        ImageButton b2 = (ImageButton) findViewById(R.id.imagebutton_cabrera);
        ImageButton b3 = (ImageButton) findViewById(R.id.imagebutton_cs);

        Button continueButton = (Button) findViewById(R.id.button_continue);
        Button backButton = (Button) findViewById(R.id.button_back);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image3 = false;
                image2 = false;
                image1 = true;
                message.setVisibility(View.VISIBLE);
                message.setText("You selected Buzz!");
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image3 = false;
                image2 = true;
                image1 = false;
                message.setVisibility(View.VISIBLE);
                message.setText("You selected Cabrera!");
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image3 = true;
                image2 = false;
                image1 = false;
                message.setVisibility(View.VISIBLE);
                message.setText("You selected Stinky CS Kid!");
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UserInputScreen.this, StartScreen.class);
                startActivity(i);
            }
        });

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                usernameEditText = (EditText) findViewById(R.id.input_name);
                String name = usernameEditText.getText().toString().trim();
                if (name.equals("")) {
                    Toast.makeText(view.getContext(),
                            "You did not enter a name!",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                View radioView1 = findViewById(R.id.radioButton1);
                View radioView2 = findViewById(R.id.radioButton2);
                View radioView3 = findViewById(R.id.radioButton3);

                boolean checked1 = ((RadioButton) radioView1).isChecked();
                boolean checked2 = ((RadioButton) radioView2).isChecked();
                boolean checked3 = ((RadioButton) radioView3).isChecked();

                if (checked1) {
                    finalDiff = "Easy";
                    Toast.makeText(view.getContext(), "Easy!", Toast.LENGTH_SHORT).show();
                } else if (checked2) {
                    finalDiff = "Medium";
                    Toast.makeText(view.getContext(), "Medium!", Toast.LENGTH_SHORT).show();
                } else if (checked3) {
                    finalDiff = "Hard";
                    Toast.makeText(view.getContext(), "Hard!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(view.getContext(),
                            "Please select a difficulty",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if (image1) {
                    sprite = "buzz";
                } else if (image2) {
                    sprite = "cabrera";
                } else if (image3) {
                    sprite = "stinkyCSKid";
                } else if (message.getVisibility() == View.INVISIBLE) {
                    Toast.makeText(view.getContext(), "Invisible", Toast.LENGTH_SHORT).show();
                    return;
                }

                new Player(name, finalDiff, sprite);

                Intent i = new Intent(UserInputScreen.this, GameScreen.class);
                startActivity(i);
            }
        });
    }
    public EditText getName() {
        return usernameEditText;
    }

}