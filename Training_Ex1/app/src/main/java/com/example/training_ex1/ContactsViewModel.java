package com.example.training_ex1;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;

/**
 * ViewModel class that connects between Fragment and the Contacts that are in the repository
 */
public class ContactsViewModel extends ViewModel {

    private final ContactsRepository repository;
    private final MutableLiveData<Contact> clickedContact;
    private final MutableLiveData<ArrayList<Contact>> contacts;

    /**
     * ContactsViewModel's Constructor
     * @param contactsRepository repository which the ViewModel is attached to
     */
    public ContactsViewModel(final ContactsRepository contactsRepository){
        this.repository = contactsRepository;
        this.clickedContact = new MutableLiveData<>();
        this.contacts = new MutableLiveData<>();
        this.contacts.setValue(null);
    }

    /*
     * Alerts the ViewModel that the ContactMenuFragment is being loaded
     */
    public void LoadingMenu(Context context){
        if (this.contacts.getValue() == null){
            this.contacts.setValue(repository.getContactsList(context));
        }
    }

    /*
     * Alerts the ViewModel of a contact that was clicked
     */
    public void contactClicked(Contact contact){
        this.clickedContact.setValue(contact);
    }

    public LiveData<Contact> observeClickedContact() {
        return clickedContact;
    }

    public LiveData<ArrayList<Contact>> observeContacts() {
        return contacts;
    }
}
