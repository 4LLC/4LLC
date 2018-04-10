package com.fourllc.donate;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.fourllc.donate.NetworkingUtils.JustGiving;

public class paymentReceivedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_received);

        Intent intent = getIntent();
        Uri data = intent.getData();
        String donationId = data.getQueryParameter(JustGiving.DONATION_ID_PARAMETER);
    }
}
