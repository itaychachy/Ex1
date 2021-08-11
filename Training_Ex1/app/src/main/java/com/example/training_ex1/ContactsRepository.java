package com.example.training_ex1;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract;

import androidx.lifecycle.MutableLiveData;

import java.io.InputStream;
import java.util.ArrayList;

public class ContactsRepository {
    private final Context context;

    public ContactsRepository(Context context){
        this.context = context;
    }


    @SuppressLint("Range")
    public MutableLiveData<ArrayList<Contact>> loadContacts() {
        final ArrayList<Contact> contacts = new ArrayList<>();
        final ContentResolver contentResolver = context.getContentResolver();
        final Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,
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
                InputStream inputStream = ContactsContract.Contacts.openContactPhotoInputStream(contentResolver,
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
                    currentContact.setImage(photo);
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
        return new MutableLiveData<>(contacts);
    }
}
