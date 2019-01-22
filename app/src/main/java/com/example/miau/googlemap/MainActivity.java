package com.example.miau.googlemap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


/**
 * <h1>Welcome screen</h1>
 * MainActivity class provides welcome screen
 * and allows to start main application's functionality
 * related with map management
 *
 * @author Michał Kunda
 * @version alpha
 * @since 24.11.2018
 */
public class MainActivity extends AppCompatActivity {

    ImageView imageView;

    /**
     * This method is executed on the class call.
     * It adds concrete view to our activity
     * and allows to change view on button click
     * from actual to the view with map management
     * @param savedInstanceState it gets Bundle object
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.buttonStart);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MapFragment.class);
                startActivity(intent);
            }

        });
    }
}
