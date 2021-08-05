package com.example.training_ex1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


/**
 * Activity class to display contact's information
 */
public class ContactActivity extends AppCompatActivity {

    /**
     * On create method. Gets the contact's information from the last Intent that started this
     * Activity and display it's information
     * @param savedInstanceState saved instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        Intent intent = getIntent();
        String name = intent.getStringExtra(MainActivity.EXTRA_NAME);
        String mail = intent.getStringExtra(MainActivity.EXTRA_MAIL);
        String phone = intent.getStringExtra(MainActivity.EXTRA_PHONE);

        TextView firstTextView = findViewById(R.id.textView);
        TextView secondTextView = findViewById(R.id.textView2);
        TextView thirdTextView = findViewById(R.id.textView3);

        firstTextView.setText(name);
        if (mail != null){ // Mail is optional
            secondTextView.setText(mail);
            thirdTextView.setText(phone);
        }
        else{
            secondTextView.setText(phone);
        }
    }
}