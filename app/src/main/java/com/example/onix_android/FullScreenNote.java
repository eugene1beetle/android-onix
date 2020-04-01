package com.example.onix_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class FullScreenNote extends AppCompatActivity {

    TextView bigTitle, bigText, bigDate, bigTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_note);

        bigTitle = findViewById(R.id.big_title);
        bigText = findViewById(R.id.big_text);
        bigTag = findViewById(R.id.big_tag);
        bigDate = findViewById(R.id.big_created);

        Intent intent = getIntent();

        String title = intent.getStringExtra("title");
        String tag = intent.getStringExtra("tag");
        String text = intent.getStringExtra("text");
        String date = intent.getStringExtra("date");

        bigTitle.setText(title);
        bigText.setText(text);
        bigTag.setText(String.format("Tag: %s", tag));
        bigDate.setText(String.format("Created: %s", date));
    }
}
