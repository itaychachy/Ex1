package com.example.training_ex1;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class ContactMenuFragment extends Fragment implements RecyclerViewAdapter.ItemClickListener{

    RecyclerViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_menu, container, false);
        ArrayList<Contact> contacts = getContactList();
        // set up the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.rvContact);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new RecyclerViewAdapter(view.getContext(), contacts);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        return view;
    }


    @Override
    public void onContactClick(View view, int position) {
        Contact contact = adapter.getItem(position);
        NavDirections action = ContactMenuFragmentDirections.actionContactMenuFragmentToContactFragment().setContact(contact);
        Navigation.findNavController(view).navigate(action);
    }

    /*
     * Creates an array of Contacts from the.
     */
    @SuppressLint("Range")
    private ArrayList<Contact> getContactList() {
        ArrayList<Contact> contacts = new ArrayList<>();
        ContentResolver contentResolver = requireActivity().getContentResolver();
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
                InputStream inputStream = ContactsContract.Contacts.openContactPhotoInputStream(requireActivity().getContentResolver(),
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
        return contacts;
    }
}