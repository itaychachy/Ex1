package com.example.training_ex1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.ItemClickListener {

    public static final String EXTRA_NAME = "com.example.myfirstapp.NAME";
    public static final String EXTRA_MAIL = "com.example.myfirstapp.MAIL";
    public static final String EXTRA_PHONE = "com.example.myfirstapp.PHONE";


    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // data to populate the RecyclerView with
        ArrayList<Contact> names = new ArrayList<>();
        names.add(new Contact("ItaykkkkItaykkkkkkokokokItaykkkkkkokokokItaykkkkkkokokokItaykkkkkkokokokkkokokok", "itaychachy@gmail.com", "+972502673554"));
        names.add(new Contact("Dan", "itaychachy@gmail.com", "+972502673554"));
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


    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(this, ContactActivity.class);
        Contact contact = adapter.getItem(position);
        intent.putExtra(EXTRA_NAME, contact.getName());
        intent.putExtra(EXTRA_MAIL, contact.getMail());
        intent.putExtra(EXTRA_PHONE, contact.getPhoneNumber());

        startActivity(intent);
    }
}