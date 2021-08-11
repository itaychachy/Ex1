package com.example.training_ex1;

import static com.google.common.truth.Truth.assertThat;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ContactsViewModelTest {

    private ContactsViewModel contactsViewModel;

    @Before
    public void setUp(){
        this.contactsViewModel = new ContactsViewModel();
    }


    @Test
    public void testContactClicked_equalsGetContact(){
        Contact contact1 = new Contact("test", "1");
        Contact contact2 = new Contact("test", "2");
        Contact contact3 = new Contact("test", "3");

        contactsViewModel.contactClicked(null);
        assertThat(contactsViewModel.getClickedContact()).isNull();

        contactsViewModel.contactClicked(contact1);
        assertThat(contactsViewModel.getClickedContact()).isNotNull();

        contactsViewModel.contactClicked(contact2);
        assertThat(contactsViewModel.getClickedContact()).isEqualTo(contact2);

        contactsViewModel.contactClicked(contact3);
        assertThat(contactsViewModel.getClickedContact().getName()).isEqualTo(contact3.getName());
        assertThat(contactsViewModel.getClickedContact().getPhoneNumber()).isEqualTo(contact3.getPhoneNumber());
        assertThat(contactsViewModel.getClickedContact().getImage()).isEqualTo(contact3.getImage());
        assertThat(contactsViewModel.getClickedContact().getMail()).isEqualTo(contact3.getMail());

        contactsViewModel.contactClicked(contact1);
        contactsViewModel.contactClicked(contact2);
        assertThat(contactsViewModel.getClickedContact()).isNotEqualTo(contact1);
    }
}