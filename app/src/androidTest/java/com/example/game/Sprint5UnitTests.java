package com.example.myapplication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import android.content.Context;
import android.content.Intent;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.uiautomator.Direction;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.Until;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Before;

public class Sprint5UnitTests {

    private static final String PACKAGE = "com.example.myapplication";
    private static final long LAUNCH_TIMEOUT = 10000;
    private static UiDevice device;

    @Before
    public void startMainActivityFromHomeScreen() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        Context context = InstrumentationRegistry.getInstrumentation().getContext(); // gets the context based on the instrumentation
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(PACKAGE);  // sets the intent to start your app
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);  // clears out any previous task, i.e., make sure it starts on the initial screen
        context.startActivity(intent); // starts the app
        device.wait(Until.hasObject(By.pkg(PACKAGE).depth(0)), LAUNCH_TIMEOUT);

        device.findObject(By.res(PACKAGE, "button_start")).clickAndWait(Until.newWindow(), LAUNCH_TIMEOUT);
        device.findObject(By.res(PACKAGE, "input_name")).setText("Michelle");
        device.findObject(By.res(PACKAGE, "radioButton1")).click();
        device.findObject(By.res(PACKAGE, "imagebutton_buzz")).click();
        device.findObject(By.res(PACKAGE, "button_continue")).clickAndWait(Until.newWindow(), LAUNCH_TIMEOUT);
    }

    @Test
    public void useAppContext() {
        // context of the app under test
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.myapplication", appContext.getPackageName());
    }

    @Test
    public void checkBeforeConditions() {
        assertNotNull(device);
    }

    @Test
    public void checkStartTotalIsZero() {
        assertEquals(0, Player.getPoints());
    }

    @Test
    public void checkPointsAfterFirstCollision() {
        UiObject2 buzz = device.findObject(By.res(PACKAGE, "character_buzz"));
        buzz.fling(Direction.DOWN, 2000);
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertEquals(0, Player.getPoints());
    }

    @Test
    public void checkPointsAfterSecondCollision() {
        // First collision
        UiObject2 buzz = device.findObject(By.res(PACKAGE, "character_buzz"));
        buzz.fling(Direction.DOWN, 2000);
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // Second Collision
        UiObject2 buzz2 = device.findObject(By.res(PACKAGE, "character_buzz"));
        buzz2.fling(Direction.DOWN, 2000);
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertEquals(0, Player.getPoints());
    }

    @Test
    public void checkPointsAfterThirdCollision() {
        // First Collision
        UiObject2 buzz = device.findObject(By.res(PACKAGE, "character_buzz"));
        buzz.fling(Direction.DOWN, 2000);
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // Second Collision
        UiObject2 buzz2 = device.findObject(By.res(PACKAGE, "character_buzz"));
        buzz2.fling(Direction.DOWN, 2000);
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // Third Collision
        UiObject2 buzz3 = device.findObject(By.res(PACKAGE, "character_buzz"));
        buzz3.fling(Direction.DOWN, 2000);
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertEquals(50, Player.getPoints());
    }

    @Test
    public void checkLivesAfterFirstCollision() {
        UiObject2 buzz = device.findObject(By.res(PACKAGE, "character_buzz"));
        buzz.fling(Direction.DOWN, 2000);
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertEquals(2, Player.getNumberOfLives());
    }

    @Test
    public void checkLivesAfterSecondCollision() {
        // First collision
        UiObject2 buzz = device.findObject(By.res(PACKAGE, "character_buzz"));
        buzz.fling(Direction.DOWN, 2000);
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // Second Collision
        UiObject2 buzz2 = device.findObject(By.res(PACKAGE, "character_buzz"));
        buzz2.fling(Direction.DOWN, 2000);
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertEquals(1, Player.getNumberOfLives());
    }

    @Test
    public void checkLivesAfterThirdCollision() {
        // First Collision
        UiObject2 buzz = device.findObject(By.res(PACKAGE, "character_buzz"));
        buzz.fling(Direction.DOWN, 2000);
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // Second Collision
        UiObject2 buzz2 = device.findObject(By.res(PACKAGE, "character_buzz"));
        buzz2.fling(Direction.DOWN, 2000);
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // Third Collision
        UiObject2 buzz3 = device.findObject(By.res(PACKAGE, "character_buzz"));
        buzz3.fling(Direction.DOWN, 2000);
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertEquals(0, Player.getNumberOfLives());

    }

    @Test
    public void gameOverOnFirstCollision() {
        UiObject2 buzz = device.findObject(By.res(PACKAGE, "character_buzz"));
        buzz.fling(Direction.DOWN, 2000);
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertEquals(false, Player.getGameOver());
    }

    @Test
    public void gameOverOnSecondCollision() {
        // First collision
        UiObject2 buzz = device.findObject(By.res(PACKAGE, "character_buzz"));
        buzz.fling(Direction.DOWN, 2000);
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // Second Collision
        UiObject2 buzz2 = device.findObject(By.res(PACKAGE, "character_buzz"));
        buzz2.fling(Direction.DOWN, 2000);
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertEquals(false, Player.getGameOver());
    }

    @Test
    public void gameOverOnThirdCollision() {
        // First Collision
        UiObject2 buzz = device.findObject(By.res(PACKAGE, "character_buzz"));
        buzz.fling(Direction.DOWN, 2000);
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // Second Collision
        UiObject2 buzz2 = device.findObject(By.res(PACKAGE, "character_buzz"));
        buzz2.fling(Direction.DOWN, 2000);
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // Third Collision
        UiObject2 buzz3 = device.findObject(By.res(PACKAGE, "character_buzz"));
        buzz3.fling(Direction.DOWN, 2000);
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        assertEquals(true, Player.getGameOver());

    }

}
