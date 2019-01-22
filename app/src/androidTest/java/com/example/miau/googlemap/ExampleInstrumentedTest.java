package com.example.miau.googlemap;

import android.content.Context;
import android.os.Build;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;

import androidx.test.InstrumentationRegistry;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.action.ViewActions.*;


/**
 * <h1>Espresso test class</h1>
 * Class with espresso test
 *
 * @author Michał Kunda
 * @version alpha
 * @since 17.01.2019
 */
public class ExampleInstrumentedTest {

    UiDevice device;

    /**
     * Method executed before tests that gets device
     */
    @Before
    public void before(){
        device = UiDevice.getInstance(getInstrumentation());
    }

    /**
     * Method that checks user permissions
     */
    private void allowPermissionsIfNeeded() {
        if (Build.VERSION.SDK_INT >= 23) {
            UiObject allowPermissions = device.findObject(new UiSelector().text("Allow"));
            if (allowPermissions.exists()) {
                try {
                    allowPermissions.click();
                } catch (UiObjectNotFoundException e) {
                }
            }
        }
    }

    /**
     * Method that gets applications Activity
     */
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    /**
     * Method that tests process from beginning till adding 2 locations, and checks if warning dialog appeared
     */
    @Test
    public void processTest(){
        onView(withId(R.id.buttonStart)).perform(click());
        allowPermissionsIfNeeded();
        onView(withId(R.id.editText)).perform(clearText());
        onView(withId(R.id.editText)).perform(typeTextIntoFocusedView("Gdynia"));
        onView(withId(R.id.imageView)).perform(click());
        onView(withId(R.id.editText)).perform(clearText());
        onView(withId(R.id.editText)).perform(typeTextIntoFocusedView("Gdansk"));
        onView(withId(R.id.imageView)).perform(click());
        onView(withId(R.id.imageView)).perform(click());
        UiObject button = device.findObject(new UiSelector().text("OK"));
        assertTrue(button.exists());

    }

}
