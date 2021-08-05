package com.example.training_ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String name = intent.getStringExtra(MainActivity.EXTRA_NAME);
        String mail = intent.getStringExtra(MainActivity.EXTRA_MAIL);
        String phone = intent.getStringExtra(MainActivity.EXTRA_PHONE);

        // Capture the layout's TextView and set the string as its text
        TextView nameTextView = findViewById(R.id.textView);
        TextView mailTextView = findViewById(R.id.textView2);
        TextView phoneTextView = findViewById(R.id.textView3);
        nameTextView.setText(name);
        mailTextView.setText(mail);
        phoneTextView.setText(phone);
    }
}