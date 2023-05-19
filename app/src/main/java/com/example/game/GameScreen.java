package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class GameScreen extends AppCompatActivity {

    private ImageView character;
    private ImageView numberOfLives;
    private float highestPosition;
    private final Timer timer = new Timer();
    private ImageView bus;
    private ObjectAnimator moveBus;
    private ImageView shortLog;
    private ObjectAnimator moveShortLog;
    private ImageView longLog;
    private ObjectAnimator moveLongLog;
    private ImageView scooterforward;
    private ObjectAnimator moveScooter1;
    private ImageView reck;
    private ObjectAnimator moveReck;
    private ImageView scooterbackward;
    private ObjectAnimator moveScooter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        TextView name = (TextView) findViewById(R.id.text_name);
        name.setText("Player: " + Player.getName());
        TextView difficulty = (TextView) findViewById(R.id.text_difficulty);
        difficulty.setText("Difficulty: " + Player.getDifficulty());
        TextView points = (TextView) findViewById(R.id.text_points);
        points.setText("Points: " + Player.getPoints());

        if (Player.getNumberOfLives() == 3) {
            numberOfLives = (ImageView) findViewById(R.id.lives3);
        } else if (Player.getNumberOfLives() == 2) {
            numberOfLives = (ImageView) findViewById(R.id.lives2);
        } else { // Hard
            numberOfLives = (ImageView) findViewById(R.id.lives1);
        }
        numberOfLives.setVisibility(View.VISIBLE);
        numberOfLives.bringToFront();
        if (Player.getSprite().equals("buzz")) {
            character = (ImageView) findViewById(R.id.character_buzz);
        } else if (Player.getSprite().equals("cabrera")) {
            character = (ImageView) findViewById(R.id.character_cabrera);
        } else { // stinky CS kid
            character = (ImageView) findViewById(R.id.character_cs);
        }
        character.setVisibility(View.VISIBLE);
        character.bringToFront();

        scooterforward = findViewById(R.id.scooterforward); // scooter forward
        moveScooter1 = ObjectAnimator.ofFloat(scooterforward, "translationX", 1300);
        moveScooter1.setRepeatCount(ValueAnimator.INFINITE);
        moveScooter1.setDuration(2000);
        moveScooter1.setStartDelay(500);
        moveScooter1.start();
        reck = findViewById(R.id.reck); // reck
        moveReck = ObjectAnimator.ofFloat(reck, "translationX", -1350);
        moveReck.setRepeatCount(ValueAnimator.INFINITE);
        moveReck.setDuration(4000);
        moveReck.setStartDelay(500);
        moveReck.start();
        scooterbackward = findViewById(R.id.scooterbackward); // scooter backward
        moveScooter2 = ObjectAnimator.ofFloat(scooterbackward, "translationX", -1300);
        moveScooter2.setRepeatCount(ValueAnimator.INFINITE);
        moveScooter2.setDuration(2000);
        moveScooter2.setStartDelay(500);
        moveScooter2.start();
        bus = findViewById(R.id.bus); // bus
        moveBus = ObjectAnimator.ofFloat(bus, "translationX", 1350);
        moveBus.setRepeatCount(ValueAnimator.INFINITE);
        moveBus.setDuration(6000);
        moveBus.setStartDelay(500);
        moveBus.start();

        runTimer();
        FrameLayout layout = (FrameLayout) findViewById(R.id.gameScreen);
        highestPosition = 1900; // initialize to the lowest Y position that any sprite can go
        layout.setOnTouchListener(new OnSwipeTouchListener(GameScreen.this) {
            public void onClick(MotionEvent e) {
                if (character.getY() < (Player.getMaxPosition("down") + 1)
                        && character.getY() > (Player.getMaxPosition("up") - 1)) {
                    if (e.getY() < character.getY()) { // tap above character
                        updatePoints(character.getY() - 168);
                        character.setY(character.getY() - 168); // move up
                    } else { // tap below character
                        character.setY(character.getY() + 168); // move down
                    }
                    isEndTile(character.getX(), character.getY());
                    Log.d("x", character.getX() + "");
                    Log.d("y", character.getY() + "");
                }
            }
            public void onSwipeUp() { // swipe up (press and fling one finger)
                float newValue = character.getY() - 168;
                if (newValue > Player.getMaxPosition("up") - 1) {
                    updatePoints(newValue);
                    character.setY(newValue); // move up
                    isEndTile(character.getX(), character.getY());
                    Log.d("x", character.getX() + "");
                    Log.d("y", character.getY() + "");
                }
            }
            public void onSwipeRight() { // swipe right (press and fling one finger)
                float newValue = character.getX() + 168;
                if (newValue < Player.getMaxPosition("right") + 1) {
                    character.setX(newValue); // move right
                    isEndTile(character.getX(), character.getY());
                }
            }
            public void onSwipeLeft() { // swipe left (press and fling one finger)
                float newValue = character.getX() - 168;
                if (newValue > Player.getMaxPosition("left") - 1) {
                    character.setX(newValue); // move left
                    isEndTile(character.getX(), character.getY());
                }
            }
            public void onSwipeDown() { // swipe down (press and fling one finger)
                float newValue = character.getY() + 168;
                if (newValue < Player.getMaxPosition("down") + 1) {
                    character.setY(newValue); // move down
                    isEndTile(character.getX(), character.getY());
                }
            }
            public void updatePoints(float newPosition) {
                if (highestPosition > newPosition) {
                    highestPosition = newPosition;
                    if ((newPosition <= 1505 && newPosition >= 1503)
                            || (newPosition <= 1001 && newPosition >= 999)) { // scooter
                        Player.addPoints(70);
                    } else if (newPosition <= 1169 && newPosition >= 1167) { // reck
                        Player.addPoints(60);
                    } else {
                        Player.addPoints(50);
                    }
                    points.setText("Points: " + Player.getPoints());
                }
            }
        });

        Button endButton = (Button) findViewById(R.id.button_end);
        endButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(GameScreen.this, StartScreen.class);
                startActivity(i);
            }
        });
        FrameLayout gameContainer = findViewById(R.id.game_container);
        TileMapView tilemapView = new TileMapView(this, null);
        gameContainer.addView(tilemapView);
    }

    public void isEndTile(float x, float y) {
        if (Player.getSprite().equals("buzz")) {
            if (y == 329) {
                if (((x >= 105 - 30) && (x <= 105 + 30))
                        || ((x >= 441 - 30) && (x <= 441 + 30))
                        || ((x >= 777 - 30) && (x <= 777 + 30))) {
                    gameWon();
                } else {
                    collisionDetected();
                }
            }
        } else if (Player.getSprite().equals("cabrera")) {
            if (y == 329) {
                if (((x >= 105 - 30) && (x <= 105 + 30))
                        || ((x >= 441 - 30) && (x <= 441 + 30))
                        || ((x >= 777 - 30) && (x <= 777 + 30))) {
                    gameWon();
                } else {
                    collisionDetected();
                }
            }
        } else { // stinky cs kid
            if (y == 329) {
                if (((x >= 110 - 30) && (x <= 110 + 30))
                        || ((x >= 446 - 30) && (x <= 446 + 30))
                        || ((x >= 782 - 30) && (x <= 782 + 30))) {
                    gameWon();
                } else {
                    collisionDetected();
                }
            }
        }
    }

    public void collisionDetected() {
        System.out.println("DONE");
        if (Player.getNumberOfLives() == 3) {
            Intent i = new Intent(GameScreen.this, GameScreen.class);
            startActivity(i);
            Player.setPoints(0);
            Player.setNumberOfLives(2);
            numberOfLives = (ImageView) findViewById(R.id.lives2);
            numberOfLives.setVisibility(View.VISIBLE);
            numberOfLives.bringToFront();
        } else if (Player.getNumberOfLives() == 2) {
            Intent i = new Intent(GameScreen.this, GameScreen.class);
            startActivity(i);
            Player.setPoints(0);
            Player.setNumberOfLives(1);
            numberOfLives = (ImageView) findViewById(R.id.lives1);
            numberOfLives.setVisibility(View.VISIBLE);
            numberOfLives.bringToFront();
        } else if (Player.getNumberOfLives() == 1) {
            Log.d("DONE", "END GAME");
            Player.setNumberOfLives(0);
            Player.setGameOver();
            Intent i = new Intent(GameScreen.this, GameOverScreen.class);
            startActivity(i);
        }
    }

    public void gameWon() {
        Log.d("DONE", "WON GAME");
        Player.setNumberOfLives(0);
        Player.setGameOver();
        Intent i = new Intent(GameScreen.this, GameWonScreen.class);
        startActivity(i);
    }

    public void runTimer() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                float busValue = (float) moveBus.getAnimatedValue(); // bus
                bus.setTranslationX(busValue);
                Rect animatedBus = new Rect();
                animatedBus.top = bus.getTop();
                animatedBus.left = (int) busValue;
                animatedBus.bottom = bus.getBottom();
                animatedBus.right = (int) busValue + bus.getWidth();
                float scooter1Value = (float) moveScooter1.getAnimatedValue(); // scooter forward
                scooterforward.setTranslationX(scooter1Value);
                Rect animatedScooter1 = new Rect();
                animatedScooter1.top = scooterforward.getTop();
                animatedScooter1.left = (int) scooter1Value;
                animatedScooter1.bottom = scooterforward.getBottom();
                animatedScooter1.right = (int) scooter1Value + scooterforward.getWidth();
                float reckValue = (float) moveReck.getAnimatedValue(); // reck
                reck.setTranslationX(reckValue);
                Rect animatedReck = new Rect();
                animatedReck.top = reck.getTop();
                animatedReck.left = (int) reckValue;
                animatedReck.bottom = reck.getBottom();
                animatedReck.right = (int) reckValue + reck.getWidth();
                float scooter2Value = (float) moveScooter2.getAnimatedValue(); // scooter backward
                scooterbackward.setTranslationX(scooter2Value);
                Rect animatedScooter2 = new Rect();
                animatedScooter2.top = scooterbackward.getTop();
                animatedScooter2.left = (int) scooter2Value;
                animatedScooter2.bottom = scooterbackward.getBottom();
                animatedScooter2.right = (int) scooter2Value + scooterbackward.getWidth();

                longLog = findViewById(R.id.longLog);
                float newValue = longLog.getX() + 70;
                longLog.setX(newValue);
                if (newValue > 1000) {
                    newValue = 0;
                    longLog.setX(newValue);
                }

                shortLog = findViewById(R.id.shortLog);
                float newValue2 = shortLog.getX() + 25;
                shortLog.setX(newValue2);
                if (newValue2 > 1000) {
                    newValue2 = 0;
                    shortLog.setX(newValue2);
                }

                Rect rect1 = new Rect();
                character.getHitRect(rect1);
                Rect rect2 = new Rect();
                longLog.getHitRect(rect2);
                Rect rect3 = new Rect();
                shortLog.getHitRect(rect3);
                Rect rect4 = new Rect();

                if (character.getY() <= 665 && character.getY() >= 329) {
                    if (Rect.intersects(rect1, rect2)) {
                        float newValue3 = character.getX() + 70;
                        character.setX(newValue3);
                    } else if (Rect.intersects(rect1, rect3)) {
                        float newValue4 = character.getX() + 25;
                        character.setX(newValue4);
                    } else {
                        System.out.println("FELL IN WATER");
                        timer.cancel();
                        final Handler handler = new Handler(Looper.getMainLooper());
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                collisionDetected();
                            }
                        }, 800);
                    }
                }

                Rect animatedCharacter = new Rect(); // player
                character.getHitRect(animatedCharacter);
                if (animatedBus.intersect(animatedCharacter) // collision detection
                        || animatedScooter1.intersect(animatedCharacter)
                        || animatedReck.intersect(animatedCharacter)
                        || animatedScooter2.intersect(animatedCharacter)) {
                    System.out.println("COLLIDED");
                    timer.cancel();
                    final Handler handler = new Handler(Looper.getMainLooper());
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            collisionDetected();
                        }
                    }, 800);
                }
            }
        }, 0, 500); // 1000 ms = 1 second
    }

}