package com.avarsolutions.meetings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_home);

        ImageView imageView = (ImageView) findViewById(R.id.img);
        TextView textView = (TextView) findViewById(R.id.tv);
        TextView textView1 = (TextView) findViewById(R.id.tv1);
        Button btn = (Button) findViewById(R.id.btn);
        EditText editText = (EditText) findViewById(R.id.secretCodeBox);

        URL serverURL;


        try {
            serverURL = new URL("https://meet.jit.si");
            JitsiMeetConferenceOptions defaultOptions =
                    new JitsiMeetConferenceOptions.Builder()
                            .setServerURL(serverURL)
                            .setWelcomePageEnabled(false)
                            .build();
            JitsiMeet.setDefaultConferenceOptions(defaultOptions);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                        .setRoom(editText.getText().toString())
                        .setWelcomePageEnabled(false)
                        .build();

                JitsiMeetActivity.launch(Home.this, options);
            }
        });

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            Uri personPhoto = acct.getPhotoUrl();

            textView.setText(personName);
            textView1.setText(personEmail);
            Glide.with(this).load(String.valueOf(personPhoto)).into(imageView);
        }


    }

    public void logout(View view) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }

    public void policy(View view) {
        startActivity(new Intent(getApplicationContext(), Policy.class));
    }
}