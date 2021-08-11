package com.example.training_ex1;

import androidx.lifecycle.ViewModel;
import java.util.ArrayList;

/**
 * ViewModel class that connects between the Fragments and the Contacts that are in the repository
 */
public class ContactsViewModel extends ViewModel {

    private final ContactsRepository repository;

    /**
     * Constructor. Sets the repository
     */
    public ContactsViewModel(){
        this.repository = new ContactsRepository();
    }

    /**
     * @return the contacts list
     */
    public ArrayList<Contact> getContactsList(){
        return this.repository.getContactsList();
    }

    /**
     * Alert the ViewModel of a contact that was clicked
     * @param contact contact
     */
    public void contactClicked(Contact contact){
        this.repository.contactClicked(contact);
    }

    /**
     * @return the contact that was clicked
     */
    public Contact getClickedContact(){
        return this.repository.getClickedContact();
    }
}
