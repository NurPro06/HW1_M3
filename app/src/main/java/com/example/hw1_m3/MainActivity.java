package com.example.hw1_m3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
        Button resetButton = findViewById(R.id.button_reset);

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

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = 0;
                counterTextView.setText(String.valueOf(count));
            }
        });
    }

    private void sendCountToWhatsApp(int count) {
        String phoneNumber = "+995508081889";
        String message = "Счетчик: " + count;

        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
        sendIntent.setData(Uri.parse("whatsapp://send?phone=" + phoneNumber + "&text=" + Uri.encode(message)));

        try {
            startActivity(sendIntent);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "WhatsApp не установлен", Toast.LENGTH_SHORT).show();
        }
    }
}
