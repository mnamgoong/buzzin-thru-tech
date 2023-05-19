package com.example.game;

public class Player {

    private static String name;
    private static String difficulty;
    private static int numberOfLives;
    private static int points;
    private static String sprite;

    private static boolean gameOver;

    public Player(String playerName, String chosenDifficulty, String chosenSprite) {
        name = playerName;
        difficulty = chosenDifficulty;
        if (difficulty.equals("Easy")) {
            numberOfLives = 3;
        } else if (difficulty.equals("Medium")) {
            numberOfLives = 2;
        } else { // Hard
            numberOfLives = 1;
        }
        points = 0;
        sprite = chosenSprite;
    }


    // MAX COORDINATES FOR ALL CHARACTERS
    public static int getMaxPosition(String direction) {
        if (sprite.equals("buzz")) {
            if (direction.equals("up")) {
                return 329;
            } else if (direction.equals("down")) {
                return 1841;
            } else if (direction.equals("left")) {
                return 105;
            } else { // right
                return 777;
            }
        } else if (sprite.equals("cabrera")) {
            if (direction.equals("up")) {
                return 329;
            } else if (direction.equals("down")) {
                return 1841;
            } else if (direction.equals("left")) {
                return 105;
            } else { // right
                return 777;
            }
        } else { // stinky cs kid
            if (direction.equals("up")) {
                return 329;
            } else if (direction.equals("down")) {
                return 1841;
            } else if (direction.equals("left")) {
                return 110;
            } else { // right
                return 782;
            }
        }
    }

    public static void addPoints(int pointsAdded) {
        points += pointsAdded;
    }

    public static String getName() {
        return name;
    }

    public static String getDifficulty() {
        return difficulty;
    }

    public static int getNumberOfLives() {
        return numberOfLives;
    }
    public static void setNumberOfLives(int nol) {
        numberOfLives = nol;
    }

    public static int getPoints() {
        return points;
    }

    public static void setPoints(int nop) {
        points = nop;
    }

    public static String getSprite() {
        return sprite;
    }

    public static void setGameOver() {
        gameOver = true;
    }

    public static boolean getGameOver() {
        return gameOver;
    }

}
