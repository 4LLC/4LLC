package com.fourllc.donate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * This class is used to create a navigation (for now) to the
 * donate blood list screen
 *
 * Note:- This is a temporary screen
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button donateBloodButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        donateBloodButton = (Button) findViewById(R.id.donate_blood_btn);

        donateBloodButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, DonateBloodLocationActivity.class);
        startActivity(intent);
    }
}
