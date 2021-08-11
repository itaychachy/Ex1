package com.example.training_ex1;
import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class ContactsViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Contact>> contactsList = null;
    private final MutableLiveData<Contact> contact = new MutableLiveData<>();
    private ContactsRepository repository = null;

    public LiveData<ArrayList<Contact>> getContactsList(Context context){
        if (contactsList == null){
            contactsList = new MutableLiveData<ArrayList<Contact>>();
            loadContacts(context);
        }
        return contactsList;
    }

    private void loadContacts(Context context) {
        if (this.repository == null){
            this.repository = new ContactsRepository(context);
        }
        this.contactsList = this.repository.loadContacts();
    }

    public void setContact(Contact contact){
        this.contact.setValue(contact);
    }

    public Contact getContact(){
        return contact.getValue();
    }
}
