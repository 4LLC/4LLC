package com.fourllc.donate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;


public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 123;
    private static final String USERS = "Users";


    private FirebaseAuth mAuth;


    FirebaseDatabase mBaseRef;
    DatabaseReference mUserRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAuth = FirebaseAuth.getInstance();


        mBaseRef = FirebaseDatabase.getInstance();
        mUserRef = mBaseRef.getReference(USERS);


        startActivityForResult(AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(Arrays.asList(
                        new AuthUI.IdpConfig.EmailBuilder().build(),
                        new AuthUI.IdpConfig.GoogleBuilder().build(),
                        new AuthUI.IdpConfig.FacebookBuilder().build()))
                .build(), RC_SIGN_IN);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // RC_SIGN_IN is the request code you passed into startActivityForResult(...) when starting the sign in flow.
        if (requestCode == RC_SIGN_IN) {


            IdpResponse response = IdpResponse.fromResultIntent(data);

            Log.v("RESPONSE", String.valueOf(response.getErrorCode()));


            // Successfully signed in
            if (resultCode == RESULT_OK) {
                Toast.makeText(getApplicationContext(), "SIGNED IN SUCCESSFULLY", Toast.LENGTH_LONG).show();

                //write user to Firebase
//                if (mAuth.getCurrentUser() != null) {
//                    HashMap<String, Object> map = new HashMap<>();
//                    map.put("TEST USER ", mAuth.getCurrentUser());
//                    mUserRef.push().updateChildren(map);
//                } else {
//                    //not signed in
//                }
                finish();
                return;
            } else {
                // Sign in failed
                if (response == null) {
                    // User pressed back button
                    Toast.makeText(getApplicationContext(), "SIGN IN CANCELLED", Toast.LENGTH_LONG).show();
                    return;
                }

                if (response.getErrorCode() == ErrorCodes.NO_NETWORK) {
                    Toast.makeText(getApplicationContext(), "NO INTERNET CONNECTION", Toast.LENGTH_LONG).show();
                    return;
                }

                if (response.getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
                    Toast.makeText(getApplicationContext(), "UNKNOWN ERROR", Toast.LENGTH_LONG).show();
                    return;
                }
            }

        }
    }

}
