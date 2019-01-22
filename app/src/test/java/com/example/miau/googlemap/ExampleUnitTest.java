package com.example.miau.googlemap;

import android.location.Address;
import android.location.Geocoder;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.Locale;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest="/MapJava/app/src/main/AndroidManifest.xml")
public class ExampleUnitTest {

    private MapFragment activity;

    @Before
    public void setUp() throws Exception
    {
        activity = Robolectric.buildActivity( MapFragment.class )
                .create()
                .resume()
                .get();
    }

    @Test
    public void addition_isCorrect() {

        assertNotNull( activity );


    }
}