package com.example.training_ex1;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;

/**
 * ViewModel class that connects between Fragment and the Contacts that are in the repository
 */
public class ContactsViewModel extends ViewModel {

    private final ContactsRepository repository;
    final MutableLiveData<Contact> clickedContact;

    /**
     * Constructor. Sets the repository
     */
    public ContactsViewModel(){
        this.repository = new ContactsRepository();
        this.clickedContact = new MutableLiveData<>();
    }

    /**
     * @return the contacts list
     */
    public ArrayList<Contact> getContactsList(){
        return this.repository.getContactsList();
    }

    /**
     * Alerts the ViewModel of a contact that was clicked
     * @param contact contact
     */
    public void contactClicked(Contact contact){
        this.clickedContact.setValue(contact);
    }
}
