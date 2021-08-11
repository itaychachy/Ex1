package com.example.training_ex1;
import android.content.Context;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import java.util.ArrayList;

public class ContactsViewModel extends ViewModel {

    private ContactsRepository repository = null;

    public LiveData<ArrayList<Contact>> getContactsList(Context context){
        if (this.repository == null){
            this.repository = new ContactsRepository(context);
        }
        return this.repository.getContactsList();
    }

    public void contactClicked(Contact contact, View view){
        this.repository.contactClicked(contact);
        final NavDirections action = ContactsMenuFragmentDirections.actionContactMenuFragmentToContactFragment().setContact(contact);
        Navigation.findNavController(view).navigate(action);
    }

    public Contact getContact(){
        return this.repository.getContact();
    }
}
