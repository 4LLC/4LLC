package com.fourllc.donate;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fourllc.donate.databinding.ActivityPaymentReceivedBinding;
import com.fourllc.donate.model.justGivingModels.Donation;
import com.fourllc.donate.remote.GoogleApiUtils;
import com.fourllc.donate.remote.JustGivingApiUtils;
import com.fourllc.donate.remote.JustGivingService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentReceivedActivity extends AppCompatActivity {
    private JustGivingService justGivingService;
    private ActivityPaymentReceivedBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_payment_received);

        justGivingService = JustGivingApiUtils.getJustGivingService();
        retrieveData();
    }



    private void retrieveData() {
        Intent intent = getIntent();
        Uri data = intent.getData();
        String donationId = data.getQueryParameter(JustGivingApiUtils.DONATION_ID_PARAMETER);
        justGivingService.getDonationById(donationId).enqueue(new Callback<Donation>() {
            @Override
            public void onResponse(Call<Donation> call, Response<Donation> response) {
                if(response.isSuccessful()){
                    setUpUiWithDonation(response.body());
                }else {
                    int statusCode = response.code();
                    Log.i("API ERROR", "onResponse: error code is " +statusCode);
                }
            }

            @Override
            public void onFailure(Call<Donation> call, Throwable t) {
                Log.i("REQUEST FAILURE", "onFailure: request failed for the donation id");
            }
        });
    }

//    private void setUpUiWithDonation(Donation donation) {
//        StyleSpan styleSpan = new StyleSpan(R.style.donationInformationSpan);
//        SpannableStringBuilder builder = new SpannableStringBuilder(donation.getAmount());
//        builder.setSpan(styleSpan,0,builder.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        String htmlString = "<b>"+ donation.getAmount() + "</b>";
//        binding.donationReceivedAmount.append(Html.fromHtml(htmlString));
//        htmlString = "<b>" + donation.getDonorDisplayName() + "</b>";
//        binding.donationReceivedName.append(Html.fromHtml(htmlString));
//        htmlString = "<b>" + donation.getDonationRef() + "</b>";
//        binding.donationReceivedReference.append(Html.fromHtml(htmlString));
//        htmlString = "<b>" + donation.getDonationDate() + "</b>";
//        binding.donationReceivedDate.append(Html.fromHtml(htmlString));
//    }

    private void setUpUiWithDonation(Donation donation) {
        StyleSpan styleSpan = new StyleSpan(R.style.donationInformationSpan);
        SpannableStringBuilder builder = new SpannableStringBuilder(donation.getAmount());
        builder.setSpan(styleSpan,0,builder.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        binding.donationReceivedAmount.append(builder);
        builder = new SpannableStringBuilder(donation.getDonorDisplayName());
        builder.setSpan(styleSpan,0,builder.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        binding.donationReceivedName.append(builder);
        builder = new SpannableStringBuilder(donation.getDonationRef());
        builder.setSpan(styleSpan,0,builder.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        binding.donationReceivedReference.append(builder);
        builder = new SpannableStringBuilder(donation.getDonationDate());
        builder.setSpan(styleSpan,0,builder.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        binding.donationReceivedDate.append(builder);
    }
}
