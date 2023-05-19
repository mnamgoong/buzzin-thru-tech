package com.example.myapplication;

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

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class Sprint3UnitTests {

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
        device.findObject(By.res(PACKAGE, "radioButton2")).click();
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
    public void checkUserInputName() {
        if (Player.getName() != null) {
            assertEquals("Michelle", Player.getName());
        } else {
            assertNull(Player.getName());
        }
    }

    @Test
    public void checkUserInputDifficulty() {
        assertEquals("Medium", Player.getDifficulty());
    }

    @Test
    public void checkNumberOfLives() {
        assertEquals(2, Player.getNumberOfLives());
    }

    @Test
    public void checkUserInputSprite() {
        assertEquals("buzz", Player.getSprite());
    }

    @Test
    public void checkInitialPoints() {
        assertEquals(0, Player.getPoints());
    }

    @Test
    public void checkMoveLeftPoints() {
        UiObject2 buzz = device.findObject(By.res(PACKAGE, "character_buzz"));
        buzz.fling(Direction.RIGHT, 2000);
        assertEquals(0, Player.getPoints());
    }

    @Test
    public void checkMoveRightPoints() {
        UiObject2 buzz = device.findObject(By.res(PACKAGE, "character_buzz"));
        buzz.fling(Direction.LEFT, 2000);
        assertEquals(0, Player.getPoints());
    }

    @Test
    public void checkMoveUpPoints() {
        UiObject2 buzz = device.findObject(By.res(PACKAGE, "character_buzz"));
        buzz.fling(Direction.DOWN, 2000);
        assertEquals(50, Player.getPoints());
    }

    @Test
    public void checkMoveDownPoints() {
        UiObject2 buzz = device.findObject(By.res(PACKAGE, "character_buzz"));
        buzz.fling(Direction.DOWN, 2000);
        buzz.fling(Direction.UP, 2000);
        assertEquals(50, Player.getPoints()); // 50 + 0
    }

    @Test
    public void checkScooter1Points() {
        UiObject2 buzz = device.findObject(By.res(PACKAGE, "character_buzz"));
        buzz.fling(Direction.DOWN, 2000);
        buzz.fling(Direction.DOWN, 2000);
        assertEquals(120, Player.getPoints()); // 50 + 70
    }

    @Test
    public void checkReckPoints() {
        UiObject2 buzz = device.findObject(By.res(PACKAGE, "character_buzz"));
        buzz.fling(Direction.DOWN, 2000);
        buzz.fling(Direction.DOWN, 2000);
        buzz.fling(Direction.DOWN, 2000);
        buzz.fling(Direction.DOWN, 2000);
        assertEquals(230, Player.getPoints()); // 50 + 70 + 50 + 60 = 230
    }

    @Test
    public void checkScooter2Points() {
        UiObject2 buzz = device.findObject(By.res(PACKAGE, "character_buzz"));
        buzz.fling(Direction.DOWN, 2000);
        buzz.fling(Direction.DOWN, 2000);
        buzz.fling(Direction.DOWN, 2000);
        buzz.fling(Direction.DOWN, 2000);
        buzz.fling(Direction.DOWN, 2000);
        assertEquals(300, Player.getPoints()); // 50 + 70 + 50 + 60 = 230
    }

}
