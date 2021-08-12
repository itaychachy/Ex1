package com.example.training_ex1;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Repository class that is responsible of loading and holding the contacts from the devise
 * @author itaychachy
 */
public class ContactsRepository {

    private final ArrayList<Contact> contactsList;

    /**
     * ContactsRepository constructor. Loads all of the contacts from the user's device.
     */
    public ContactsRepository(){
        this.contactsList = loadAndSortsContacts();
    }

    /*
     * A method responsible of loading the contacts. At the end the method sorts the contacts according
     * to their names
     */
    private ArrayList<Contact> loadAndSortsContacts() {
        final ArrayList<Contact> contacts = new ArrayList<>();
        final ContentResolver contentResolver = ContactsApplication.getAppContext().getContentResolver();
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
        Collections.sort(contacts, (c1, c2) -> c1.getName().compareTo(c2.getName()));
        return contacts;
    }

    /*
     * A method for creating a Contact object.
     */
    @SuppressLint("Range")
    private Contact createContactFromData(final Cursor cursor, final ContentResolver contentResolver){
        Bitmap photo = null;
        final String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
        final String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
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
        final Contact contact = new Contact(name, phoneNumber);
        if (mailCursor.moveToNext()){
            final String mail = mailCursor.getString(mailCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS));
            contact.setMail(mail);
        }
        if (photo != null){
            contact.setImage(photo);
        }
        phoneCursor.close();
        mailCursor.close();
        return contact;
    }

    /**
     * @return the contacts list
     */
    public ArrayList<Contact> getContactsList(){
        return contactsList;
    }
}
