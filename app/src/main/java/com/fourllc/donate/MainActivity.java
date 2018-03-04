package com.fourllc.donate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void donateBlood(View view) {
        Intent intent = new Intent(this, BloodPlacesListActivity.class);
        startActivity(intent);
    }
}
