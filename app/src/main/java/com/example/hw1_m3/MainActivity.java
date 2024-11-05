package com.example.hw1_m3;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int count = 0;
    private TextView counterTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counterTextView = findViewById(R.id.text_view);
        Button incrementButton = findViewById(R.id.button_plus);
        Button sendButton = findViewById(R.id.send);

        incrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                counterTextView.setText(String.valueOf(count));
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCountToWhatsApp(count);
            }
        });
    }

    private void sendCountToWhatsApp(int count) {
        String phoneNumber = "0508081889";
        String message = "Счетчик: " + count;

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, message);
        sendIntent.setType("text/plain");

        sendIntent.setPackage("com.whatsapp");
        try {
            startActivity(sendIntent);
        } catch (android.content.ActivityNotFoundException ex) {
        }
    }
}