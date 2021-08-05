package com.example.training_ex1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import java.util.ArrayList;

/**
 * Main Activity class to display contact's application home page
 * This activity holds a RecyclerView for each contact.
 */
public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.ItemClickListener {

    // For Intent object usage later
    public static final String EXTRA_NAME = "com.example.myfirstapp.NAME";
    public static final String EXTRA_MAIL = "com.example.myfirstapp.MAIL";
    public static final String EXTRA_PHONE = "com.example.myfirstapp.PHONE";

    RecyclerViewAdapter adapter;

    /**
     * On create method. Set the home page and the adapter for the RecyclerView of this Activity
     * @param savedInstanceState saved instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // data to populate the RecyclerView with
        ArrayList<Contact> names = new ArrayList<>();
        names.add(new Contact("ItaykkkkItaykkkkkkokokokItaykkkkkkokokokItaykkkkkkokokokItaykkkkkkokokokkkokokokItaykkkkItaykkkkkkokokokItaykkkkkkokokokItaykkkkkkokokokItaykkkkkkokokokkkokokokItaykkkkItaykkkkkkokokokItaykkkkkkokokokItaykkkkkkokokokItaykkkkkkokokokkkokokokItaykkkkItaykkkkkkokokokItaykkkkkkokokokItaykkkkkkokokokItaykkkkkkokokokkkokokok", "itaychachy@gmail.com", "+972502673554"));
        names.add(new Contact("Dan", "+972502673554"));
        names.add(new Contact("Shahar", "itaychachy@gmail.com", "+972502673554"));
        names.add(new Contact("Yaron", "itaychachy@gmail.com", "+972502673554"));
        names.add(new Contact("Elad", "itaychachy@gmail.com", "+972502673554"));
        names.add(new Contact("Itay", "itaychachy@gmail.com", "+972502673554"));
        names.add(new Contact("Dan", "itaychachy@gmail.com", "+972502673554"));
        names.add(new Contact("Shahar", "itaychachy@gmail.com", "+972502673554"));
        names.add(new Contact("Yaron", "itaychachy@gmail.com", "+972502673554"));
        names.add(new Contact("Elad", "itaychachy@gmail.com", "+972502673554"));
        names.add(new Contact("Itay", "itaychachy@gmail.com", "+972502673554"));
        names.add(new Contact("Dan", "itaychachy@gmail.com", "+972502673554"));
        names.add(new Contact("Shahar", "itaychachy@gmail.com", "+972502673554"));
        names.add(new Contact("Yaron", "itaychachy@gmail.com", "+972502673554"));

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvContact);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewAdapter(this, names);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    /**
     * Activates the ContactActivity with the contact's information that was pressed
     * @param view current view
     * @param position the view's position in the RecyclerView count
     */
    @Override
    public void onContactClick(View view, int position) {
        Intent intent = new Intent(this, ContactActivity.class);
        Contact contact = adapter.getItem(position);
        intent.putExtra(EXTRA_NAME, contact.getName());
        intent.putExtra(EXTRA_MAIL, contact.getMail());
        intent.putExtra(EXTRA_PHONE, contact.getPhoneNumber());
        startActivity(intent);
    }
}