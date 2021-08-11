package com.example.training_ex1;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static com.google.common.truth.Truth.*;
import androidx.test.ext.junit.runners.AndroidJUnit4;

@RunWith(AndroidJUnit4.class)
public class ContactsRepositoryTest {

    private ContactsRepository contactsRepository;

    @Before
    public void setUp(){
        this.contactsRepository = new ContactsRepository();
    }

    @Test
    public void testContactClicked_equalsGetContact(){
        Contact contact1 = new Contact("test", "1");
        Contact contact2 = new Contact("test", "2");
        Contact contact3 = new Contact("test", "3");

        contactsRepository.contactClicked(null);
        assertThat(contactsRepository.getClickedContact()).isNull();

        contactsRepository.contactClicked(contact1);
        assertThat(contactsRepository.getClickedContact()).isNotNull();

        contactsRepository.contactClicked(contact2);
        assertThat(contactsRepository.getClickedContact()).isEqualTo(contact2);

        contactsRepository.contactClicked(contact3);
        assertThat(contactsRepository.getClickedContact().getName()).isEqualTo(contact3.getName());
        assertThat(contactsRepository.getClickedContact().getPhoneNumber()).isEqualTo(contact3.getPhoneNumber());
        assertThat(contactsRepository.getClickedContact().getImage()).isEqualTo(contact3.getImage());
        assertThat(contactsRepository.getClickedContact().getMail()).isEqualTo(contact3.getMail());

        contactsRepository.contactClicked(contact1);
        contactsRepository.contactClicked(contact2);
        assertThat(contactsRepository.getClickedContact()).isNotEqualTo(contact1);
    }
}