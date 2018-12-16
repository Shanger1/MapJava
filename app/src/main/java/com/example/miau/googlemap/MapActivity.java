package com.example.miau.googlemap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * <h1>Map management</h1>
 * MapActivity class provides methods
 * that allows interaction with map
 *
 * @author Michał Kunda
 * @version alpha
 * @since 16.12.2018
 */
public class MapActivity extends AppCompatActivity {

    /**
     * This method is executed on the class call.
     * It adds concrete view to our activity
     * @param savedInstanceState it gets Bundle object
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
    }

}
