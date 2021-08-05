package com.example.training_ex1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;

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
        TextView firstTextView = findViewById(R.id.textView);
        TextView secondTextView = findViewById(R.id.textView2);
        TextView thirdTextView = findViewById(R.id.textView3);
        firstTextView.setText(name);
        if (mail != null){
            secondTextView.setText(mail);
            thirdTextView.setText(phone);
        }
        else{
            secondTextView.setText(phone);
        }
    }
}