package com.example.training_ex1;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Toast;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
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
    public static final String EXTRA_PHOTO = "com.example.myfirstapp.PHOTO";

    private static final int CONTACTS_REQUEST_CODE = 1;
    private static final String PERMISSION_GRANTED = "Permission to contacts granted";
    private static final String PERMISSION_DENIED = "Permission denied.\n The Application must have access to Contacts, please change permissions on your settings.";

    RecyclerViewAdapter adapter;

    /**
     * On create method. Set the home page and the adapter for the RecyclerView of this Activity
     * @param savedInstanceState saved instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (getApplicationContext().checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[] {Manifest.permission.READ_CONTACTS}, CONTACTS_REQUEST_CODE);
            }
            else{
                CreateActivityWithPermission();
            }
        }
        else {
            Toast.makeText(MainActivity.this, PERMISSION_GRANTED, Toast.LENGTH_SHORT).show();
            CreateActivityWithPermission();
        }

    }

    /*
     * Set the home page and the adapter for the RecyclerView of this Activity.
     * Called after permission granted.
     */
    private void CreateActivityWithPermission(){
        ArrayList<Contact> contacts = getContactList();

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvContact);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewAdapter(this, contacts);
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
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        Bitmap photo = contact.getPhoto();
        if (photo != null){
            photo.compress(Bitmap.CompressFormat.JPEG, 100, bs);
            intent.putExtra(EXTRA_PHOTO, bs.toByteArray());
        }
        else{
            intent.putExtra(EXTRA_PHOTO, photo);
        }
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CONTACTS_REQUEST_CODE){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(MainActivity.this, PERMISSION_GRANTED, Toast.LENGTH_SHORT).show();
                CreateActivityWithPermission();
            }
            else{
                Toast.makeText(MainActivity.this, PERMISSION_DENIED, Toast.LENGTH_SHORT).show();
            }
        }
    }

    /*
     * Creates an array of Contacts from the.
     */
    @SuppressLint("Range")
    private ArrayList<Contact> getContactList() {
        ArrayList<Contact> contacts = new ArrayList<>();
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
        Contact currentContact = null;
        String id = null, name = null, mail = null;
        Bitmap photo = null;
        if ((cursor != null ? cursor.getCount() : 0) > 0) {
            while (cursor.moveToNext()) {
                id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                Cursor phoneCursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                        new String[]{id}, null);
                phoneCursor.moveToNext();
                String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                Cursor mailCursor = contentResolver.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                        null, ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?",
                        new String[]{id}, null);
                InputStream inputStream = ContactsContract.Contacts.openContactPhotoInputStream(getContentResolver(),
                        ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, Long.parseLong(id)));

                if (inputStream != null) {
                    photo = BitmapFactory.decodeStream(inputStream);
                }
                currentContact = new Contact(name, phoneNumber);
                if (mailCursor.moveToNext()){
                    mail = mailCursor.getString(mailCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS));
                    currentContact.setMail(mail);
                    mail = null;
                }
                if (photo != null){
                    currentContact.setPhoto(photo);
                    photo = null;
                }
                contacts.add(currentContact);
                phoneCursor.close();
                mailCursor.close();
            }
        }
        if(cursor!=null){
            cursor.close();
        }
        return contacts;
    }
}