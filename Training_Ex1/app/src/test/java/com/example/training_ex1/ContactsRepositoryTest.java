package com.example.training_ex1;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static com.google.common.truth.Truth.*;
import androidx.test.ext.junit.runners.AndroidJUnit4;

/**
 * Simple unit test for the ContactRepository class
 * @author itaychachy
 */
@RunWith(AndroidJUnit4.class)
public class ContactsRepositoryTest {

    private ContactsRepository contactsRepository;

    @Before
    public void setUp(){
        this.contactsRepository = new ContactsRepository();
    }

    @Test
    public void testGetContacts_returnEmptyArrayList(){
        assertThat(contactsRepository.getContactsList().isEmpty()).isTrue();
    }
}