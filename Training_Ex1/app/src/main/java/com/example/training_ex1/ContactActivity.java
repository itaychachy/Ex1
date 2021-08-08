package com.example.training_ex1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        Intent intent = getIntent();
        String name = intent.getStringExtra(MainActivity.EXTRA_NAME);
        String mail = intent.getStringExtra(MainActivity.EXTRA_MAIL);
        String phone = intent.getStringExtra(MainActivity.EXTRA_PHONE);
        byte[] byteArrayPhoto = intent.getByteArrayExtra(MainActivity.EXTRA_PHOTO);

        TextView firstTextView = findViewById(R.id.textView);
        TextView secondTextView = findViewById(R.id.textView2);
        TextView thirdTextView = findViewById(R.id.textView3);
        ImageView imageView = findViewById(R.id.imageView);

        firstTextView.setText(name);
        if (mail != null){ // Mail is optional
            secondTextView.setText(mail);
            thirdTextView.setText(phone);
        }
        else{
            secondTextView.setText(phone);
        }
        if (byteArrayPhoto != null){
            Bitmap bitmapPhoto = BitmapFactory.decodeByteArray(byteArrayPhoto, 0, byteArrayPhoto.length);
            imageView.setImageBitmap(bitmapPhoto);
        }
    }
}