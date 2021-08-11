package com.example.training_ex1;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

public class ContactsRepository {
    private final Context context;
    private final MutableLiveData<ArrayList<Contact>> contactsList;
    private final MutableLiveData<Contact> contact;

    public ContactsRepository(Context context){
        this.context = context;
        this.contactsList = loadAndSortsContacts();
        this.contact = new MutableLiveData<>();
    }

    private MutableLiveData<ArrayList<Contact>> loadAndSortsContacts() {
        final ArrayList<Contact> contacts = new ArrayList<>();
        final ContentResolver contentResolver = context.getContentResolver();
        final Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
        if ((cursor != null ? cursor.getCount() : 0) > 0) {
            while (cursor.moveToNext()) {
                contacts.add(createContactFromData(cursor, contentResolver));
            }
        }
        if(cursor != null){
            cursor.close();
        }
        Collections.sort(contacts, (c1, c2) -> {return c1.getName().compareTo(c2.getName());});
        return new MutableLiveData<>(contacts);
    }
    
    @SuppressLint("Range")
    private Contact createContactFromData(Cursor cursor, ContentResolver contentResolver){
        Contact contact = null;
        String id = null, name = null, mail = null;
        Bitmap photo = null;
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
        contact = new Contact(name, phoneNumber);
        if (mailCursor.moveToNext()){
            mail = mailCursor.getString(mailCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS));
            contact.setMail(mail);
        }
        if (photo != null){
            contact.setImage(photo);
        }
        phoneCursor.close();
        mailCursor.close();
        return contact;
    }

    public LiveData<ArrayList<Contact>> getContactsList(){
        return contactsList;
    }

    public Contact getContact(){
        return contact.getValue();
    }

    public void contactClicked(Contact contact){
        this.contact.setValue(contact);
    }
}
