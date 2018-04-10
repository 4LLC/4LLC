package com.fourllc.donate;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.fourllc.donate.NetworkingUtils.JustGiving;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        Button donateBloodButton = (Button) findViewById(R.id.donateBloodButton);
        Button donateMoneyButton = (Button) findViewById(R.id.donateMoneyButton);

        donateMoneyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(JustGiving.DONATION_URL);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        donateBloodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send user to the blood pledge page
            }
        });
    }
}
